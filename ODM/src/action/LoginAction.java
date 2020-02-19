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
	 * 登录，当姓名、身份证、联系方式三者均正确时登入
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
				this.setMessage("此用户不存在，请确认是否已注册！");
				return "success";
			}

//			System.out.println("passwd1--"+loginFgUser.getUserPwd()+",passwd2--"+fgUser.getUserPwd());
			if(!loginAdminInfo.getAdminPwd().equals(adminInfo.getAdminPwd())){
				this.setMessage("用户密码不正确，请重试！");
				return "success";
			}
			//申报信息放入session
			ActionContext context = ActionContext.getContext();
			context.getSession().put("LOGINADMIN", loginAdminInfo);
			this.setMessage("通过验证");
			System.out.println("uname:"+loginAdminInfo.getAdminName()+",Welcome!");
//			log.info("uname:"+fgUser.getName()+",Welcome!");
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage("输入有误，请检查后重新输入！");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * 查询出所有用户信息
	 */
	public String queryAdminInfos(){
		setAdminInfoList(getAdminInfoService().findAll());
		for(int i = adminInfoList.size()-1; i>=0; i--){//移除超级管理员
			if("0".equals(adminInfoList.get(i).getAdminLevel())){
				adminInfoList.remove(i);
			}
		}
		setSysMenuList(getSysMenuService().findAll());
		//开始对权限字段进行翻译
		try{
			for(AdminInfo a: adminInfoList){
				String adminPowerString = "";
				if(!("".equals(a.getAdminPower())) && (null != a.getAdminPower())){
					String[] powerStrings = a.getAdminPower().split(",");
					for(String p: powerStrings){
						for(SysMenu s: sysMenuList){
							if(Integer.valueOf(p).equals(s.getSysMenuId())){
								adminPowerString += s.getSysMenuName();
								adminPowerString += "，";
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
			this.setMessage("权限有误，请检查！");
			return "success";
		}
		return "success";
	}

	/**
	 * 根据ID查询一个用户
	 */
	public String findOneAdminInfo(){
		adminInfo = getAdminInfoService().findByID(adminInfo.getAdminId());
        JSONObject jo = JSONObject.fromObject(adminInfo);  
        // 调用json对象的toString方法转换为字符串然后赋值给result  
        this.message = jo.toString();  
		return "success";
	}
	
	/**
	 * 增加或修改一条用户信息
	 * 为新增用户设置默认密码和默认级别
	 */
	public String addAdminInfo(){
		if(("".equals(adminInfo.getAdminPwd())) || (null == adminInfo.getAdminPwd())){
			adminInfo.setAdminPwd(Md5Tool.string2MD5("xxzx123456"));
		}
		adminInfo.setAdminLevel("1");
		getAdminInfoService().addAdminInfo(adminInfo);
		this.setMessage("成功");
		return "success";
	}

	/**
	 * 删除一条用户信息
	 */
	public String deleteAdminInfo(){
		setAdminInfo(getAdminInfoService().findByID(adminInfo.getAdminId()));
		getAdminInfoService().delAdminInfo(adminInfo);
		this.setMessage("成功");
		return "success";
	}
	
	/**
	 * 重置指定用户的密码
	 */
	public String resetAdminPwd(){
		setAdminInfo(getAdminInfoService().findByID(adminInfo.getAdminId()));
		adminInfo.setAdminPwd(Md5Tool.string2MD5("xxzx123456"));
		getAdminInfoService().addAdminInfo(adminInfo);
		this.setMessage("成功");
		return "success";
	}
	
	/**
	 * 修改用户密码
	 * @return
	 */
	public String modAdminPwd(){
		AdminInfo loginAdminInfo = new AdminInfo();
		AdminInfo newAdminInfo = new AdminInfo();
		//从session中读取申报信息
		ActionContext context = ActionContext.getContext();
		loginAdminInfo = ((AdminInfo)context.getSession().get("LOGINADMIN"));
		if(null == loginAdminInfo){
			message = "页面已超时，请重新登录后再提交!";
			return "success";
		}
		newAdminInfo = getAdminInfoService().findByID(loginAdminInfo.getAdminId());
		adminInfo.setAdminPwd(Md5Tool.string2MD5(adminInfo.getAdminPwd()));
		if(!newAdminInfo.getAdminPwd().equals(adminInfo.getAdminPwd())){
			this.setMessage("密码不正确，请重试！");
			return "success";
		}
		System.out.println("新密码"+newAdminPwd);
		newAdminInfo.setAdminPwd(Md5Tool.string2MD5(newAdminPwd));
		getAdminInfoService().updateAdminInfo(newAdminInfo);
		this.setMessage("成功");
		return "success";	
	}
	
	/**
	 * 从session提取用户信息，查询用户权限，用于决定显示哪些菜单
	 * @return
	 */
	public String showSysMenu() {
		//从session中读取信息
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
			this.setMessage("权限有误，请检查！");
			return "success";
		}
		return "success";
	}
	
	/**
	 * 获取所有菜单信息
	 * @return 以json返回dtree格式的菜单数据
	 */
	public String getMenuTree(){
		setSysMenuList(getSysMenuService().findAll());
		DTreeResponse resp = new DTreeResponse();
		Status status = new Status();
		status.setCode(200);
		status.setMessage("操作成功");
		List<DTree> dtrees = new ArrayList<DTree>();
		  // 遍历数据
		  DTree d = null;
		  for(SysMenu sm : sysMenuList){
			 if(!"Y".equals(sm.getSysMenuClick())){//隐藏超管权限菜单
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
		    //... 做出转换，最后放进集合中
		    dtrees.add(d);
		  }
		  
		resp.setStatus(status); 
		resp.setData(dtrees);
		//JSONArray ja = JSONArray.fromObject(dtrees);
        JSONObject jo = JSONObject.fromObject(resp);  
        // 调用json对象的toString方法转换为字符串然后赋值给result  
        setMessage(jo.toString());  
        //System.out.println(message);
		return "success";
	}
	
	/**
	 **保存一条菜单
	 */
	public String saveSysMenu(){
		System.out.println(1);
		System.out.println(sysMenu.getSysMenuParentid());
		System.out.println(sysMenu.getSysMenuId());
		//getSysMenuService().addSysMenu(sysMenu);
		this.setMessage("成功");
		return "success";
	}
	
	/**
	 * 删除一条菜单
	 */
	public String deleteSysMenu(){
		System.out.println(sysMenu.getSysMenuId());
		setSysMenu(getSysMenuService().findByID(sysMenu.getSysMenuId()));
		getSysMenuService().delSysMenu(sysMenu);
		this.setMessage("成功");
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
