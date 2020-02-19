/**
 * ��ʱ�������ڶ�������ֵ����Ա��������2020-01-17
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
    		Date tomorrow = c.getTime();//����
    		c.setTime(today);
    		c.add(Calendar.DAY_OF_MONTH, -1);
    		Date yesterday = c.getTime();//����

    		DutyInfo dutyInfo1 = new DutyInfo();
    		dutyInfo1.setDutyInfoOrder(order);
    		dutyInfo1.setDutyInfoDate(dateFormat.format(today));
        	List<DutyInfo> dutyInfos1 = new ArrayList<DutyInfo>();//��ǰ���ֵ����Ϣ
        	List<DutyInfo> dutyInfos2 = new ArrayList<DutyInfo>();//ǰһ���ֵ����Ϣ
        	List<DutyInfo> dutyInfos3 = new ArrayList<DutyInfo>();//��һ���ֵ����Ϣ
        	dutyInfos1 = getDutyInfoService().findByExample(dutyInfo1);//��ȡ��ֵ��ΰ���Ϣ
        	//��ʼ��ȡǰһ�ͺ�һ�����Ϣ
        	String prePersonName = "";
        	String afterPersonName = "";
        	if("3" .equals(order)){//���
        		dutyInfo1.setDutyInfoOrder("2");
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(tomorrow));
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos3.size()-1; i>=0; i--){//ɸѡ����һ���е�ȫ���������
        			if(("2".equals(dutyInfos3.get(i).getDutyInfoOrder())) || ("3".equals(dutyInfos3.get(i).getDutyInfoOrder()))){
        				dutyInfos3.remove(i);
        			}
        		}
        	}
        	if("2" .equals(order)){//�����
        		dutyInfo1.setDutyInfoOrder("1");
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		dutyInfo1.setDutyInfoOrder("3");
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        	}
        	if(("1" .equals(order))){//�����
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(yesterday));
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos2.size()-1; i>=0; i--){//ɸѡ��ǰһ���е�ȫ�������
        			if(("1".equals(dutyInfos2.get(i).getDutyInfoOrder())) || ("2".equals(dutyInfos2.get(i).getDutyInfoOrder()))){
        				dutyInfos2.remove(dutyInfos2.get(i));
        			}
        		}
        		dutyInfo1.setDutyInfoOrder("2");
        		dutyInfo1.setDutyInfoDate(dateFormat.format(today));
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        	}
        	if(("0" .equals(order))){//ȫ���
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(yesterday));
        		dutyInfos2 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos2.size()-1; i>=0; i--){//ɸѡ��ǰһ���е�ȫ�������
        			if(("1".equals(dutyInfos2.get(i).getDutyInfoOrder())) || ("2".equals(dutyInfos2.get(i).getDutyInfoOrder()))){
        				dutyInfos2.remove(dutyInfos2.get(i));
        			}
        		}
        		dutyInfo1.setDutyInfoOrder(null);
        		dutyInfo1.setDutyInfoDate(dateFormat.format(tomorrow));
        		dutyInfos3 =  getDutyInfoService().findByExample(dutyInfo1);
        		for(int i = dutyInfos3.size()-1; i>=0; i--){//ɸѡ����һ���е�ȫ���������
        			if(("2".equals(dutyInfos3.get(i).getDutyInfoOrder())) || ("3".equals(dutyInfos3.get(i).getDutyInfoOrder()))){
        				dutyInfos3.remove(i);
        			}
        		}
        	}
        	//�ó�ǰһֵ��������
        	for(DutyInfo d : dutyInfos2){
        		dutyPerson = getDutyPersonService().findByID(d.getDutyInfoPersonid());
        		prePersonName += dutyPerson.getDutyPersonName();
        		prePersonName += "��";
        	}
        	//�ó���һֵ��������
        	for(DutyInfo d : dutyInfos3){
        		dutyPerson = getDutyPersonService().findByID(d.getDutyInfoPersonid());
        		afterPersonName += dutyPerson.getDutyPersonName();
        		afterPersonName += "��";
        	}
    		//���Ͷ���
    		sendMsg.SendMsg sendMsg = new sendMsg.SendMsg();
    		switch (order) {
				case "0":	order = "ȫ���"; 	break;
				case "1":	order = "�����"; 	break;
				case "2":	order = "�����"; 	break;
				case "3":	order = "���"; 	break;
				default:	 	break;
    		}

    		System.out.println("��ʼ���Ͷ������ѣ�"+order);
    		String msg  = "ֵ�����ѣ���������ֵ"+order+"����ע�⽻�Ӱ༰ֵ��绰����ת�ƣ�"
    								+"\nǰһ���ֵ������ " + prePersonName+"\n��һ���ֵ������ " + afterPersonName;
        	for(DutyInfo d : dutyInfos1){
        		dutyPerson = getDutyPersonService().findByID(d.getDutyInfoPersonid());
        		String tel = "tel:"+dutyPerson.getDutyPersonPhone();
        		System.out.println(msg);
        		if(("Y".equals(dutyPerson.getDutyPersonStatus())) && ("Y".equals(d.getDutyInfoIsremind()))){
            		System.out.println(tel);
            		sendMsg.sendSms(msg , tel);
            		System.out.println("���ŷ������");
        		}
        	}	

        } catch (Exception e) {
        	e.printStackTrace();
           System.out.println("-------------�����쳣--------------");
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