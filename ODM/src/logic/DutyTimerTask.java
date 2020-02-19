/**
 * 定时任务，用于短信提醒值班人员，增加于2020-01-17
 */
package logic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import model.DutyInfo;
import model.DutyPerson;
import service.DutyInfoService;
import service.DutyPersonService;
import service.IDutyInfoService;
import service.IDutyPersonService;

public class DutyTimerTask extends TimerTask {
	private String order;
	public DutyTimerTask(String order){
		this.order = order;
	}
	
	public DutyTimerTask(){
	}
	private DutyInfo dutyInfo = new DutyInfo();
	private List<DutyInfo> dutyInfos = new ArrayList<DutyInfo>();
	private static IDutyInfoService dutyInfoService = null;
	
	private static IDutyPersonService dutyPersonService = null;
	private DutyPerson dutyPerson = new DutyPerson();
	private List<DutyPerson> dutyPersons = new ArrayList<DutyPerson>();
	
    @Override
    public void run() {
        try {
    		Date today=new Date();
    		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    		Calendar c = Calendar.getInstance();
    		c.setTime(today);
    		c.add(Calendar.DAY_OF_MONTH,1);
    		Date tomorrow = c.getTime();//明天
    		c.setTime(today);
    		c.add(Calendar.DAY_OF_MONTH, -1);
    		Date yesterday = c.getTime();//昨天

    		DutyInfo dutyInfo1 = new DutyInfo();
    		dutyInfo1.setDutyInfoOrder(order);
    		dutyInfo1.setDutyInfoDate(dateFormat.format(today));
        	List<DutyInfo> dutyInfos1 = new ArrayList<DutyInfo>();//当前班次值班信息
        	List<DutyInfo> dutyInfos2 = new ArrayList<DutyInfo>();//前一班次值班信息
        	List<DutyInfo> dutyInfos3 = new ArrayList<DutyInfo>();//后一班次值班信息
        	dutyInfos1 = getDutyInfoService().findByExample(dutyInfo1);//获取该值班次班信息
        	//开始获取前一和后一班次信息
        	String prePersonName = "";
        	String afterPersonName = "";
        	if("3" .equals(order)){//晚班
        		dutyInfo1.setDutyInfoOrder("2");
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(tomorrow));
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos3.size()-1; i>=0; i--){//筛选出后一天中的全天班和上午班
        			if(("2".equals(dutyInfos3.get(i).getDutyInfoOrder())) || ("3".equals(dutyInfos3.get(i).getDutyInfoOrder()))){
        				dutyInfos3.remove(i);
        			}
        		}
        	}
        	if("2" .equals(order)){//下午班
        		dutyInfo1.setDutyInfoOrder("1");
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		dutyInfo1.setDutyInfoOrder("3");
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        	}
        	if(("1" .equals(order))){//上午班
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(yesterday));
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos2.size()-1; i>=0; i--){//筛选出前一天中的全天班和晚班
        			if(("1".equals(dutyInfos2.get(i).getDutyInfoOrder())) || ("2".equals(dutyInfos2.get(i).getDutyInfoOrder()))){
        				dutyInfos2.remove(dutyInfos2.get(i));
        			}
        		}
        		dutyInfo1.setDutyInfoOrder("2");
        		dutyInfo1.setDutyInfoDate(dateFormat.format(today));
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        	}
        	if(("0" .equals(order))){//全天班
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(yesterday));
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos2.size()-1; i>=0; i--){//筛选出前一天中的全天班和晚班
        			if(("1".equals(dutyInfos2.get(i).getDutyInfoOrder())) || ("2".equals(dutyInfos2.get(i).getDutyInfoOrder()))){
        				dutyInfos2.remove(dutyInfos2.get(i));
        			}
        		}
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(tomorrow));
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos3.size()-1; i>=0; i--){//筛选出后一天中的全天班和上午班
        			if(("2".equals(dutyInfos3.get(i).getDutyInfoOrder())) || ("3".equals(dutyInfos3.get(i).getDutyInfoOrder()))){
        				dutyInfos3.remove(i);
        			}
        		}
        	}
        	//得出前一值班人姓名
        	for(DutyInfo d : dutyInfos2){
        		dutyPerson = getDutyPersonService().findByID(d.getDutyInfoPersonid());
        		prePersonName += dutyPerson.getDutyPersonName();
        		prePersonName += "。";
        	}
        	//得出后一值班人姓名
        	for(DutyInfo d : dutyInfos3){
        		dutyPerson = getDutyPersonService().findByID(d.getDutyInfoPersonid());
        		afterPersonName += dutyPerson.getDutyPersonName();
        		afterPersonName += "。";
        	}
    		//发送短信
    		sendMsg.SendMsg sendMsg = new sendMsg.SendMsg();
    		switch (order) {
				case "0":	order = "全天班"; 	break;
				case "1":	order = "上午班"; 	break;
				case "2":	order = "下午班"; 	break;
				case "3":	order = "晚班"; 	break;
				default:	 	break;
    		}

    		System.out.println("开始发送短信提醒："+order);
    		String msg  = "值班提醒：您今天轮值"+order+"，请注意交接班及值班电话呼叫转移！"
    								+"\n前一班次值班人是 " + prePersonName+"\n后一班次值班人是 " + afterPersonName;
        	for(DutyInfo d : dutyInfos1){
        		dutyPerson = getDutyPersonService().findByID(d.getDutyInfoPersonid());
        		String tel = "tel:"+dutyPerson.getDutyPersonPhone();
        		System.out.println(msg);
        		if(("Y".equals(dutyPerson.getDutyPersonStatus())) && ("Y".equals(d.getDutyInfoIsremind()))){
            		System.out.println(tel);
            		sendMsg.sendSms(msg , tel);
            		System.out.println("短信发送完成");
        		}
        	}	

        } catch (Exception e) {
        	e.printStackTrace();
           System.out.println("-------------发生异常--------------");
        }
    }
    
	public List<DutyInfo> getDutyInfos() {
		return dutyInfos;
	}

	public void setDutyInfos(List<DutyInfo> dutyInfos) {
		this.dutyInfos = dutyInfos;
	}

	public static IDutyInfoService getDutyInfoService() {
		if (dutyInfoService == null) {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			SessionFactory sessionFactory = (SessionFactory)ac.getBean("sessionFactory");
			Session session =sessionFactory.openSession();
			dutyInfoService = (DutyInfoService) ac.getBean("DutyInfoService");
		}
		return dutyInfoService;
	}

	public void setDutyInfoService(IDutyInfoService dutyInfoService) {
		this.dutyInfoService = dutyInfoService;
	}

	public static IDutyPersonService getDutyPersonService() {
		if (dutyPersonService == null) {
			ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
			SessionFactory sessionFactory = (SessionFactory)ac.getBean("sessionFactory");
			Session session =sessionFactory.openSession();
			dutyPersonService = (DutyPersonService) ac.getBean("DutyPersonService");
		}
		return dutyPersonService;
	}

	public void setDutyPersonService(IDutyPersonService dutyPersonService) {
		this.dutyPersonService = dutyPersonService;
	}

	public DutyPerson getDutyPerson() {
		return dutyPerson;
	}

	public void setDutyPerson(DutyPerson dutyPerson) {
		this.dutyPerson = dutyPerson;
	}

	public List<DutyPerson> getDutyPersons() {
		return dutyPersons;
	}

	public void setDutyPersons(List<DutyPerson> dutyPersons) {
		this.dutyPersons = dutyPersons;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public DutyInfo getDutyInfo() {
		return dutyInfo;
	}

	public void setDutyInfo(DutyInfo dutyInfo) {
		this.dutyInfo = dutyInfo;
	}
	
}