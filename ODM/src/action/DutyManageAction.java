package action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.DutyInfoService;
import service.DutyPersonService;
import service.IDutyInfoService;
import service.IDutyPersonService;
import logic.Page;
import model.DutyInfo;
import model.DutyPerson;

import com.opensymphony.xwork2.ActionSupport;

public class DutyManageAction extends ActionSupport {
	private DutyInfo dutyInfo = new DutyInfo();
	private List<DutyInfo> dutyInfos = new ArrayList<DutyInfo>();
	private static IDutyInfoService dutyInfoService = null;
	
	private static IDutyPersonService dutyPersonService = null;
	private DutyPerson dutyPerson = new DutyPerson();
	private List<DutyPerson> dutyPersons = new ArrayList<DutyPerson>();
	
	private String message = "";
	private Page page;
	private int pageIndex = 1;// ��ǰ�� ��ҳ
	
	private String startDateString = "";
	private String endDateString = "";
	
	/**
	 * �г����з��ϲ�ѯ�����ļ�¼
	 */
	public String  queryDutyInfos(){
		String hql = "from DutyInfo as model";
		String where = "";
//		System.out.println("��ʼ��ѯ����ǰpageIndex:"+pageIndex);
		if(!("".equals(dutyInfo.getDutyInfoId())) && (null != dutyInfo.getDutyInfoId())){
			where = " where model.dutyInfoId="+dutyInfo.getDutyInfoId();
		}else{
			where = " where 1=1";
			Integer integer = 0;
			if(!(integer.equals(dutyInfo.getDutyInfoPersonid())) &&  (null != dutyInfo.getDutyInfoPersonid())){
				where += " and model.dutyInfoPersonid = "+dutyInfo.getDutyInfoPersonid();
			}
			if(!("".equals(dutyInfo.getDutyInfoOrder())) && (null != dutyInfo.getDutyInfoOrder())){
				where += " and model.dutyInfoOrder= '"+dutyInfo.getDutyInfoOrder()+"'";
			}
			if(!("".equals(this.getStartDateString())) && (null != this.getStartDateString())){
				where += " and model.dutyInfoDate >= '"+this.getStartDateString()+"'";
			}
			if(!("".equals(this.getEndDateString())) && (null != this.getEndDateString())){
				where += " and model.dutyInfoDate <= '"+this.getEndDateString()+"'";
			}
		}
		hql += where;
		hql += " order by model.dutyInfoDate desc, model.dutyInfoOrder desc";
		//System.out.println(hql);
		page = getDutyInfoService().queryForPage(10, pageIndex,hql);
		setDutyInfos((List<DutyInfo>) page.getList());
    	for(DutyInfo d : dutyInfos){
    		switch (d.getDutyInfoOrder()) {
				case "0":	d.setDutyInfoOrder("ȫ���"); 	break;
				case "1":	d.setDutyInfoOrder("�����"); 	break;
				case "2":	d.setDutyInfoOrder("�����"); 	break;
				case "3":	d.setDutyInfoOrder("���"); 	break;
				default:	 	break;
    		}
    	}
		setDutyPersons(getDutyPersonService().findAll());
		return "success";
	}
	
	/**
	 * ����ID��ѯһ��ֵ���¼
	 */
	public String findOneDutyInfo(){
		dutyInfo = getDutyInfoService().findByID(dutyInfo.getDutyInfoId());
        JSONObject jo = JSONObject.fromObject(dutyInfo);  
        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result  
        this.message = jo.toString();  
		return "success";
	}
	
	/**
	 * ����һ��ֵ���¼
	 */
	public String addDutyInfo(){
		getDutyInfoService().addDutyInfo(dutyInfo);
		this.setMessage("�ɹ�");
		return "success";
	}

	/**
	 * ɾ��һ��ֵ���¼
	 */
	public String deleteDutyInfo(){
		setDutyInfo(getDutyInfoService().findByID(dutyInfo.getDutyInfoId()));
		getDutyInfoService().delDutyInfo(dutyInfo);
		this.setMessage("�ɹ�");
		return "success";
	}
	
	/**
	 * �޸�һ��ֵ���¼
	 */
	public String modifyDutyInfo(){
		getDutyInfoService().updateDutyInfo(dutyInfo);
		this.setMessage("�ɹ�");
		return "success";
	}
	
	/**
	 * ��ѯ����ֵ����Ա��Ϣ
	 */
	public String findAllDutyPersons(){
		dutyPersons = getDutyPersonService().findAll();
		return "success";
	}
	
	/**
	 * ����ID��ѯһ��ֵ����
	 */
	public String findOneDutyPerson(){
		dutyPerson = getDutyPersonService().findByID(dutyPerson.getDutyPersonId());
        JSONObject jo = JSONObject.fromObject(dutyPerson);  
        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result  
        this.message = jo.toString();  
		return "success";
	}
	
	/**
	 * ���ӻ��޸�һ��ֵ����Ա��Ϣ
	 */
	public String addDutyPerson(){
		getDutyPersonService().addDutyPerson(dutyPerson);
		this.setMessage("�ɹ�");
		return "success";
	}

	/**
	 * ɾ��һ��ֵ���¼
	 */
	public String deleteDutyPerson(){
		setDutyPerson(getDutyPersonService().findByID(dutyPerson.getDutyPersonId()));
		getDutyPersonService().delDutyPerson(dutyPerson);
		this.setMessage("�ɹ�");
		return "success";
	}
	
	
	public DutyInfo getDutyInfo() {
		return dutyInfo;
	}

	public void setDutyInfo(DutyInfo dutyInfo) {
		this.dutyInfo = dutyInfo;
	}

	public List<DutyInfo> getDutyInfos() {
		return dutyInfos;
	}

	public void setDutyInfos(List<DutyInfo> dutyInfos) {
		this.dutyInfos = dutyInfos;
	}

	public static IDutyInfoService getDutyInfoService() {
		if (dutyInfoService == null) {
			ServletContext sc = ServletActionContext.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
			dutyInfoService = (DutyInfoService) ac.getBean("DutyInfoService");
		}
		return dutyInfoService;
	}

	public void setDutyInfoService(IDutyInfoService dutyInfoService) {
		DutyManageAction.dutyInfoService = dutyInfoService;
	}

	public static IDutyPersonService getDutyPersonService() {
		if (dutyPersonService == null) {
			ServletContext sc = ServletActionContext.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
			dutyPersonService = (DutyPersonService) ac.getBean("DutyPersonService");
		}
		return dutyPersonService;
	}

	public void setDutyPersonService(IDutyPersonService dutyPersonService) {
		DutyManageAction.dutyPersonService = dutyPersonService;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}
	
}
