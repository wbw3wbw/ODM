package action;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import model.AdminInfo;
import model.SysMenu;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.IAdminInfoService;
import service.ISysMenuService;
import logic.Md5Tool;
import logic.Tools;

import com.ctc.wstx.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;

import dtree.*;

public class LoginAction extends ActionSupport {
	
	private static final Logger log = LoggerFactory.getLogger(LoginAction.class);
	
	private AdminInfo adminInfo = new AdminInfo();
	private List<AdminInfo> adminInfoList = new ArrayList<AdminInfo>();
	private static IAdminInfoService adminInfoService = null;
	private SysMenu sysMenu = new SysMenu();
	private List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
	private static ISysMenuService sysMenuService = null;
	private String message = "";
	    
	private String newAdminPwd = "";

	

	/**
	 * ��¼�������������֤����ϵ��ʽ���߾���ȷʱ����
	 * @return
	 * @throws Exception
	 */
	public String login() throws Exception{
		try {
			System.out.println("uname:"+adminInfo.getAdminId()+','+adminInfo.getAdminPwd()+",trylogin......");
			AdminInfo loginAdminInfo = new AdminInfo();
			adminInfo.setAdminPwd(Md5Tool.string2MD5(adminInfo.getAdminPwd()));
			loginAdminInfo = getAdminInfoService().login(adminInfo);
			if(null == loginAdminInfo){
				this.setMessage("���û������ڣ���ȷ���Ƿ���ע�ᣡ");
				return "success";
			}

//			System.out.println("passwd1--"+loginFgUser.getUserPwd()+",passwd2--"+fgUser.getUserPwd());
			if(!loginAdminInfo.getAdminPwd().equals(adminInfo.getAdminPwd())){
				this.setMessage("�û����벻��ȷ�������ԣ�");
				return "success";
			}
			//�걨��Ϣ����session
			ActionContext context = ActionContext.getContext();
			context.getSession().put("LOGINADMIN", loginAdminInfo);
			this.setMessage("ͨ����֤");
			System.out.println("uname:"+loginAdminInfo.getAdminName()+",Welcome!");
//			log.info("uname:"+fgUser.getName()+",Welcome!");
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("��������������������룡");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * ��ѯ�������û���Ϣ
	 */
	public String queryAdminInfos(){
		setAdminInfoList(getAdminInfoService().findAll());
		for(int i = adminInfoList.size()-1; i>=0; i--){//�Ƴ���������Ա
			if("0".equals(adminInfoList.get(i).getAdminLevel())){
				adminInfoList.remove(i);
			}
		}
		setSysMenuList(getSysMenuService().findAll());
		//��ʼ��Ȩ���ֶν��з���
		try{
			for(AdminInfo a: adminInfoList){
				String adminPowerString = "";
				if(!("".equals(a.getAdminPower())) && (null != a.getAdminPower())){
					String[] powerStrings = a.getAdminPower().split(",");
					for(String p: powerStrings){
						for(SysMenu s: sysMenuList){
							if(Integer.valueOf(p).equals(s.getSysMenuId())){
								adminPowerString += s.getSysMenuName();
								adminPowerString += "��";
							}
						}
					}
				}
				if(adminPowerString.length() > 24){
					adminPowerString = adminPowerString.substring(0, 20) + "......";
				}
				a.setAdminPower(adminPowerString);
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("Ȩ���������飡");
			return "success";
		}
		return "success";
	}

	/**
	 * ����ID��ѯһ���û�
	 */
	public String findOneAdminInfo(){
		adminInfo = getAdminInfoService().findByID(adminInfo.getAdminId());
        JSONObject jo = JSONObject.fromObject(adminInfo);  
        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result  
        this.message = jo.toString();  
		return "success";
	}
	
	/**
	 * ���ӻ��޸�һ���û���Ϣ
	 * Ϊ�����û�����Ĭ�������Ĭ�ϼ���
	 */
	public String addAdminInfo(){
		if(("".equals(adminInfo.getAdminPwd())) || (null == adminInfo.getAdminPwd())){
			adminInfo.setAdminPwd(Md5Tool.string2MD5("xxzx123456"));
		}
		adminInfo.setAdminLevel("1");
		getAdminInfoService().addAdminInfo(adminInfo);
		this.setMessage("�ɹ�");
		return "success";
	}

	/**
	 * ɾ��һ���û���Ϣ
	 */
	public String deleteAdminInfo(){
		setAdminInfo(getAdminInfoService().findByID(adminInfo.getAdminId()));
		getAdminInfoService().delAdminInfo(adminInfo);
		this.setMessage("�ɹ�");
		return "success";
	}
	
	/**
	 * ����ָ���û�������
	 */
	public String resetAdminPwd(){
		setAdminInfo(getAdminInfoService().findByID(adminInfo.getAdminId()));
		adminInfo.setAdminPwd(Md5Tool.string2MD5("xxzx123456"));
		getAdminInfoService().addAdminInfo(adminInfo);
		this.setMessage("�ɹ�");
		return "success";
	}
	
	/**
	 * �޸��û�����
	 * @return
	 */
	public String modAdminPwd(){
		AdminInfo loginAdminInfo = new AdminInfo();
		AdminInfo newAdminInfo = new AdminInfo();
		//��session�ж�ȡ�걨��Ϣ
		ActionContext context = ActionContext.getContext();
		loginAdminInfo = ((AdminInfo)context.getSession().get("LOGINADMIN"));
		if(null == loginAdminInfo){
			message = "ҳ���ѳ�ʱ�������µ�¼�����ύ!";
			return "success";
		}
		newAdminInfo = getAdminInfoService().findByID(loginAdminInfo.getAdminId());
		adminInfo.setAdminPwd(Md5Tool.string2MD5(adminInfo.getAdminPwd()));
		if(!newAdminInfo.getAdminPwd().equals(adminInfo.getAdminPwd())){
			this.setMessage("���벻��ȷ�������ԣ�");
			return "success";
		}
		System.out.println("������"+newAdminPwd);
		newAdminInfo.setAdminPwd(Md5Tool.string2MD5(newAdminPwd));
		getAdminInfoService().updateAdminInfo(newAdminInfo);
		this.setMessage("�ɹ�");
		return "success";	
	}
	
	/**
	 * ��session��ȡ�û���Ϣ����ѯ�û�Ȩ�ޣ����ھ�����ʾ��Щ�˵�
	 * @return
	 */
	public String showSysMenu() {
		//��session�ж�ȡ��Ϣ
		ActionContext context = ActionContext.getContext();
		adminInfo = (AdminInfo) context.getSession().get("LOGINADMIN");
		adminInfo = getAdminInfoService().findByID(adminInfo.getAdminId());
		if("0".equals(adminInfo.getAdminLevel())){
			setSysMenuList(getSysMenuService().findAll());
			return "success";
		}
		try{
			String[] menuIdStrings = adminInfo.getAdminPower().split(",");
			for(String m: menuIdStrings){
				sysMenuList.add(getSysMenuService().findByID(Integer.valueOf(m)));
			}
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("Ȩ���������飡");
			return "success";
		}
		return "success";
	}
	
	/**
	 * ��ȡ���в˵���Ϣ
	 * @return ��json����dtree��ʽ�Ĳ˵�����
	 */
	public String getMenuTree(){
		setSysMenuList(getSysMenuService().findAll());
		DTreeResponse resp = new DTreeResponse();
		Status status = new Status();
		status.setCode(200);
		status.setMessage("�����ɹ�");
		List<DTree> dtrees = new ArrayList<DTree>();
		  // ��������
		  DTree d = null;
		  for(SysMenu sm : sysMenuList){
			 if(!"Y".equals(sm.getSysMenuClick())){//���س���Ȩ�޲˵�
				 continue;
			 }
		    d = new DTree();
		    d.setId(sm.getSysMenuId().toString());
		    d.setTitle(sm.getSysMenuName());
		    d.setParentId(sm.getSysMenuParentid().toString());
		    if("1".equals(sm.getSysMenuLevel())){
		    	d.setLast(false);
		    	d.setSpread(true);
		    }
		    if("2".equals(sm.getSysMenuLevel())){
		    	d.setLast(true);
		    }
		    //... ����ת�������Ž�������
		    dtrees.add(d);
		  }
		  
		resp.setStatus(status); 
		resp.setData(dtrees);
		//JSONArray ja = JSONArray.fromObject(dtrees);
        JSONObject jo = JSONObject.fromObject(resp);  
        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result  
        setMessage(jo.toString());  
        //System.out.println(message);
		return "success";
	}
	
	/**
	 **����һ���˵�
	 */
	public String saveSysMenu(){
		System.out.println(1);
		System.out.println(sysMenu.getSysMenuParentid());
		System.out.println(sysMenu.getSysMenuId());
		//getSysMenuService().addSysMenu(sysMenu);
		this.setMessage("�ɹ�");
		return "success";
	}
	
	/**
	 * ɾ��һ���˵�
	 */
	public String deleteSysMenu(){
		System.out.println(sysMenu.getSysMenuId());
		setSysMenu(getSysMenuService().findByID(sysMenu.getSysMenuId()));
		getSysMenuService().delSysMenu(sysMenu);
		this.setMessage("�ɹ�");
		return "success";
	}
	

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public IAdminInfoService getAdminInfoService() {
		if (adminInfoService == null) {
			ServletContext sc = ServletActionContext.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
			adminInfoService = (IAdminInfoService) ac.getBean("AdminInfoService");
		}
		return adminInfoService;
	}
	public void setAdminInfoService(IAdminInfoService adminInfoService) {
		LoginAction.adminInfoService = adminInfoService;
	}
	
	public ISysMenuService getSysMenuService() {
		if (sysMenuService == null) {
			ServletContext sc = ServletActionContext.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
			sysMenuService = (ISysMenuService) ac.getBean("SysMenuService");
		}
		return sysMenuService;
	}
	
	public void setSysMenuService(ISysMenuService sysMenuService) {
		LoginAction.sysMenuService = sysMenuService;
	}


	public SysMenu getSysMenu() {
		return sysMenu;
	}

	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
	}

	public List<SysMenu> getSysMenuList() {
		return sysMenuList;
	}

	public void setSysMenuList(List<SysMenu> sysMenuList) {
		this.sysMenuList = sysMenuList;
	}



	public AdminInfo getAdminInfo() {
		return adminInfo;
	}



	public void setAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
	}



	public List<AdminInfo> getAdminInfoList() {
		return adminInfoList;
	}



	public void setAdminInfoList(List<AdminInfo> adminInfoList) {
		this.adminInfoList = adminInfoList;
	}



	public String getNewAdminPwd() {
		return newAdminPwd;
	}



	public void setNewAdminPwd(String newAdminPwd) {
		this.newAdminPwd = newAdminPwd;
	}



	public static Logger getLog() {
		return log;
	}

}
