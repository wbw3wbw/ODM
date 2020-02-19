package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import service.IOdAttService;
import service.IOdRecordService;
import logic.ExcelReader;
import logic.Page;
import logic.Tools;
import model.AdminInfo;
import model.OdAtt;
import model.OdRecord;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ODManageAction extends ActionSupport {
	
	private OdRecord odRecord = new OdRecord();
	private List<OdRecord> odRecords = new ArrayList<OdRecord>();
	private static IOdRecordService odRecordService = null;
	
	private static IOdAttService odAttService = null;
	private OdAtt odAtt = new OdAtt();
	private List<OdAtt> odAtts = new ArrayList<OdAtt>();
	
	private String message = "";
	private Page page;
	private int pageIndex = 1;// 当前第 几页
	private AdminInfo adminInfo = new AdminInfo();
	
    private File ajaxUpload;
    private String ajaxUploadFileName;
    private String ajaxUploadRelated;
    private String fileName;
    private InputStream downloadStream = null;
    private String atts = "";
    private String odRecordDate1 = "";
    private String odRecordDate2 = "";
    private String odRecordCometime1 = "";
    private String odRecordCometime2 = "";
	
	
	/**
	 * 列出所有收文记录
	 */
	public String  listAllOdRecords(){
		setOdRecords(getOdRecordService().findAll());
		return "success";
	}
	
	/**
	 * 列出所有符合查询条件的收文记录
	 */
	public String  queryOdRecords(){
		String hql = "from OdRecord as model";
		String where = "";
//		System.out.println("开始查询，当前pageIndex:"+pageIndex);
		if(!("".equals(odRecord.getOdRecordId())) && (null != odRecord.getOdRecordId())){
			where = " where model.odRecordId="+odRecord.getOdRecordId();
		}else{
			where = " where 1=1";
			if(!("".equals(odRecord.getOdRecordName())) && (null != odRecord.getOdRecordName())){
				where += " and model.odRecordName like '%"+odRecord.getOdRecordName()+"%'";
			}
			if(!("".equals(odRecord.getOdRecordSerial())) && (null != odRecord.getOdRecordSerial())){
				where += " and model.odRecordSerial like '%"+odRecord.getOdRecordSerial()+"%'";
			}
			if(!("".equals(odRecord.getOdRecordOrg())) && (null != odRecord.getOdRecordOrg())){
				where += " and model.odRecordOrg like '%"+odRecord.getOdRecordOrg()+"%'";
			}
			if(!("".equals(odRecord.getOdRecordKeywords())) && (null != odRecord.getOdRecordKeywords())){
				where += " and model.odRecordKeywords like '%"+odRecord.getOdRecordKeywords()+"%'";
			}
			if(!("".equals(odRecord.getOdRecordYear())) && (null != odRecord.getOdRecordYear())){
				where += " and model.odRecordYear like '%"+odRecord.getOdRecordYear()+"%'";
			}
			if(!("".equals(odRecord.getOdRecordLimit())) && (null != odRecord.getOdRecordLimit())){
				where += " and model.odRecordLimit like '%"+odRecord.getOdRecordLimit()+"%'";
			}
			if(!("".equals(odRecord.getOdRecordRemark())) && (null != odRecord.getOdRecordRemark())){
				where += " and model.odRecordRemark like '%"+odRecord.getOdRecordRemark()+"%'";
			}
			if(!("".equals(this.getOdRecordDate1())) && (null != this.getOdRecordDate1())){
				where += " and model.odRecordDate >= '"+this.getOdRecordDate1()+"'";
			}
			if(!("".equals(this.getOdRecordDate2())) && (null != this.getOdRecordDate2())){
				where += " and model.odRecordDate <= '"+this.getOdRecordDate2()+"'";
			}
			if(!("".equals(this.getOdRecordCometime1())) && (null != this.getOdRecordCometime1())){
				where += " and model.odRecordCometime >= '"+this.getOdRecordCometime1()+"'";
			}
			if(!("".equals(this.getOdRecordCometime2())) && (null != this.getOdRecordCometime2())){
				where += " and model.odRecordCometime <= '"+this.getOdRecordCometime2()+"'";
			}
			if(!("".equals(odRecord.getOdRecordNum())) && (null != odRecord.getOdRecordNum())){
				where += " and model.odRecordNum = '"+odRecord.getOdRecordNum()+"'";
			}
		}
		hql += where;
		hql += " order by model.odRecordId desc";
		//System.out.println(hql);
		page = getOdRecordService().queryForPage(10, pageIndex,hql);
		setOdRecords((List<OdRecord>) page.getList());
		return "success";
	}

	
	/**
	 * 收文登记（包含修改）
	 * @不存在记录-新增，已存在记录-修改
	 * @throws Exception
	 */
	public String saveOdRecord() throws Exception{
		try{
			//从session中读取当前登录用户的信息
			ActionContext context = ActionContext.getContext();
			adminInfo = (AdminInfo) context.getSession().get("LOGINADMIN");
			if(null == odRecord.getOdRecordId()){
				odRecord.setOdRecordAdminid(adminInfo.getAdminName());
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				odRecord.setOdRecordInstime(timestamp);
			}
			//保存收文记录
			getOdRecordService().addOdRecord(odRecord);
			//System.out.println("准备上传的附件ID为："+atts);
			String[] strArray = atts.split("-=-");
			for(int i=0; i<strArray.length; i++){
				odAtt = getOdAttService().findByID(Integer.parseInt(strArray[i]));
				odAtt.setOdAttOdrid(odRecord.getOdRecordId());
				getOdAttService().updateOdAtt(odAtt);
			}

			this.setMessage("保存成功");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("输入有误，请检查后重新输入！");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * 收文查看
	 * @return
	 * @throws Exception
	 */
	public String viewOdRecord() throws Exception{
		try{
			setOdRecord( getOdRecordService().findByID(odRecord.getOdRecordId()));
			setOdAtts(getOdAttService().findByOdRecordId(odRecord.getOdRecordId()));
			setMessage("view");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("输入有误，请检查后重新输入！");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * 收文查看并进入修改模式
	 * @return
	 * @throws Exception
	 */
	public String editOdRecord() throws Exception{
		try{
			setOdRecord( getOdRecordService().findByID(odRecord.getOdRecordId()));
			setOdAtts(getOdAttService().findByOdRecordId(odRecord.getOdRecordId()));
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("输入有误，请检查后重新输入！");
			return "falure";
		}
		return "success";
	}
	
	
	/**
	 * 收文删除
	 * @return
	 * @throws Exception
	 */
	public String deleteOdRecord() throws Exception{
		try{
			setOdRecord( getOdRecordService().findByID(odRecord.getOdRecordId()));
			getOdRecordService().delOdRecord(odRecord);
			this.setMessage("成功");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("输入有误，请检查后重新输入！");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * 收文附件删除
	 * @return
	 * @throws Exception
	 */
	public String deleteOdAtt() throws Exception{
		try{
			setOdAtt( getOdAttService().findByID(odAtt.getOdAttId()));
			getOdAttService().delOdAtt(odAtt);
			this.setMessage("成功");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("输入有误，请检查后重新输入！");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * ajax上传文件并入库
	 * @return
	 */
	public String ajaxUploadOdAtt(){
		//从session中读取当前登录用户的信息
		ActionContext context = ActionContext.getContext();
		adminInfo = (AdminInfo) context.getSession().get("LOGINADMIN");
		
		String path="";
		String newFileName = "";
		int count = 0;
        // 用一个Map做返回
        Map<String, String> map = new HashMap<String, String>();  
		//如果没有文件需要上传
		if(ajaxUpload==null){
	        map.put("result", "未选择文件");  
	        // 将要返回的map对象进行json处理  
	        JSONObject jo = JSONObject.fromObject(map);  
	        // 调用json对象的toString方法转换为字符串然后赋值给result  
	        this.message = jo.toString();  
			return "success";
		}
		try {	
	//		System.out.println("创建目录开始");
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			path = "D:\\ODMupload\\OdAtts\\"+sdf2.format(date); 
			// 写到指定的路径中  
			File file = new File(path);  
		    // 如果指定的路径没有就创建  
			if (!file.exists()) {
				file.mkdirs();  
		    }
			//对文件重命名
			newFileName = sdf1.format(date)+ajaxUploadFileName;
			//把文件copy到指定目录
	       FileUtils.copyFile(ajaxUpload, new File(file, newFileName));  
	       System.out.println("成功上传文件："+path+"\\"+newFileName+"，文件大小："+(double)(ajaxUpload.length()/1024)+"K");
			String suffix = "";//获取文件后缀名
			suffix = ajaxUploadFileName.substring(ajaxUploadFileName.lastIndexOf(".")+1);
			suffix = suffix.toLowerCase();
	       odAtt.setOdAttFilename(ajaxUploadFileName);
	       odAtt.setOdAttRemark(newFileName);
	       odAtt.setOdAttPath(path);
	       odAtt.setOdAttSuffix(suffix);
	       //把附件记录存放到数据库中
	       getOdAttService().addOdAtt(odAtt);
		} catch (Exception e) {
			e.printStackTrace();  
		}
        map.put("result", "成功");  
        map.put("name", ajaxUploadFileName);
        map.put("attid", odAtt.getOdAttId().toString());
        // 将要返回的map对象进行json处理  
        JSONObject jo = JSONObject.fromObject(map);  
        // 调用json对象的toString方法转换为字符串然后赋值给result  
        this.message = jo.toString();  
		return "success";
	}
	
	/**
	 * 读取收文excel记录并入库
	 * @return
	 */
	public String impOdRecords(){
		//执行文件批量上传
		String path="";
		String newFileName = "";
		int count = 0;
        // 用一个Map做返回
        Map<String, String> map = new HashMap<String, String>();  
		try {	    
	//		System.out.println("创建目录开始");
			path = "D:\\ODMupload\\impOdRecords\\"; 
			// 写到指定的路径中  
			File file = new File(path);  
		    // 如果指定的路径没有就创建  
			if (!file.exists()) {
				file.mkdirs();  
		    }
			//对文件重命名
			newFileName = ajaxUploadFileName;
	       //list集合通过get(i)的方式来获取索引 
	       FileUtils.copyFile(ajaxUpload, new File(file, newFileName));  
		} catch (Exception e) {
			e.printStackTrace();  
		}
		
		System.out.println("开始入库");
		ActionContext context = ActionContext.getContext();
		adminInfo = (AdminInfo)context.getSession().get("LOGINADMIN");
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> strList = null;
		String suffix = "";
		suffix = ajaxUploadFileName.substring(ajaxUploadFileName.lastIndexOf(".")+1);
		suffix = suffix.toLowerCase();
		//只读取excel文件
		if(!"xls".equals(suffix) && !"xlsx".equals(suffix) ){
	        map.put("result", "失败");  
	        map.put("reason", "仅支持excel文件");
	        JSONObject jo = JSONObject.fromObject(map);  
	        this.message = jo.toString();  
			return "success";
		}
		try{
			if("xls".equals(suffix)){
				strList = excelReader.readExcel2003(path+ajaxUploadFileName);
			}else if("xlsx".equals(suffix)){
				strList = excelReader.readExcel2007(path+ajaxUploadFileName);
			}
			System.out.println("——开始从Excel导入收文记录——");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			odRecord.setOdRecordAdminid(adminInfo.getAdminName());
			//从第二行数据开始写入数据库
			for(int j=1; j<strList.size(); j++){
				System.out.println(strList.get(j));
				String[] a=strList.get(j).split("-#-");
				odRecord.setOdRecordNum(a[0]);
				odRecord.setOdRecordYear(a[1]);
				odRecord.setOdRecordLimit(a[2]);
				odRecord.setOdRecordOrg(a[3]);
				odRecord.setOdRecordSerial(a[4]);
				odRecord.setOdRecordName(a[5]);
				odRecord.setOdRecordDate(a[6]);
				odRecord.setOdRecordRemark(a[7]);
				odRecord.setOdRecordInstime(timestamp);
				if(a[5].length() < 3){continue;}
				getOdRecordService().addOdRecord(odRecord);
				count++;
			}
		}catch(Exception e){
			e.printStackTrace();
	        map.put("result", "失败");  
	        map.put("reason", "系统错误，请联系技术人员");
	        JSONObject jo = JSONObject.fromObject(map);  
	        this.message = jo.toString();  
			return "success";
		}
        map.put("result", "成功");  
        map.put("name", ajaxUploadFileName+"，共导入"+count+"条记录");
        JSONObject jo = JSONObject.fromObject(map);  
        this.message = jo.toString();  
		return "success";
	}
	
	
	/**
	 * 导入模板下载
	 * @return
	 */
	public String downloadTemplet() throws FileNotFoundException,UnsupportedEncodingException {
		// 如果下载文件名为中文，进行字符编码转换
		//ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		fileName = "templet1.xlsx";
		//fileName = new String(fileName.getBytes(), "ISO8859-1");
		File file = new File("D:\\ODMupload\\templet\\"+fileName);
		downloadStream = new FileInputStream(file);
		return "success";
	}
	
	
	/**
	 * 文件下载
	 * @return
	 */
	public String downloadFile() throws FileNotFoundException,UnsupportedEncodingException {
		// 如果下载文件名为中文，进行字符编码转换
		//ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		odAtt = getOdAttService().findByID(odAtt.getOdAttId());
		fileName = odAtt.getOdAttFilename();
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		File file = new File(odAtt.getOdAttPath()+"\\"+odAtt.getOdAttRemark());
		downloadStream = new FileInputStream(file);
		return "success";
	}
	/**
	 * 打包下载单个收文下的的所有文件
	 * 2019-04-03创建
	 */
	public String zipDownload(){
		odRecord = getOdRecordService().findByID(odRecord.getOdRecordId());
		odAtts = getOdAttService().findByOdRecordId(odRecord.getOdRecordId());
        fileName = odRecord.getOdRecordName()+"--附件.zip";
        String filePath = "D:\\ODMupload\\zipFiles";
        byte[] buffer = new byte[1024]; 
        String zipPath = filePath +"\\"+ fileName;
        File dirFile = new File(filePath);
        Tools tools = new Tools();
        try {
			if (!dirFile.exists()) {
				dirFile.mkdirs();  
		    }
	        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));    
	        //循环读取用户上传的文件
            for (int i = 0; i < odAtts.size(); i++) {    
            	File file = new File(odAtts.get(i).getOdAttPath()+"\\"+odAtts.get(i).getOdAttRemark());
                FileInputStream fis = new FileInputStream(file);    
                out.putNextEntry(new ZipEntry(odAtts.get(i).getOdAttFilename()));    
                //设置压缩文件内的字符编码，不然会变成乱码    
                out.setEncoding("GBK");    
                // 读入需要下载的文件的内容，打包到zip文件   
                int len;     
                while ((len = fis.read(buffer)) > 0) {
                    out.write(buffer, 0, len);    
                }
                out.closeEntry();    
                fis.close();    
            }
            out.close(); 
            fileName = new String(fileName.getBytes(), "ISO8859-1");
			downloadStream = new FileInputStream(zipPath);
        } catch (Exception e) {    
            e.printStackTrace();
        }
		return "success";
	}
	
	public OdRecord getOdRecord() {
		return odRecord;
	}

	public void setOdRecord(OdRecord odRecord) {
		this.odRecord = odRecord;
	}

	public List<OdRecord> getOdRecords() {
		return odRecords;
	}

	public void setOdRecords(List<OdRecord> odRecords) {
		this.odRecords = odRecords;
	}

	public static IOdRecordService getOdRecordService() {
		if (odRecordService == null) {
			ServletContext sc = ServletActionContext.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
			odRecordService = (IOdRecordService) ac.getBean("OdRecordService");
		}
		return odRecordService;
	}

	public void setOdRecordService(IOdRecordService odRecordService) {
		ODManageAction.odRecordService = odRecordService;
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

	public AdminInfo getAdminInfo() {
		return adminInfo;
	}

	public void setAdminInfo(AdminInfo adminInfo) {
		this.adminInfo = adminInfo;
	}

	public File getAjaxUpload() {
		return ajaxUpload;
	}

	public void setAjaxUpload(File ajaxUpload) {
		this.ajaxUpload = ajaxUpload;
	}

	public String getAjaxUploadFileName() {
		return ajaxUploadFileName;
	}

	public void setAjaxUploadFileName(String ajaxUploadFileName) {
		this.ajaxUploadFileName = ajaxUploadFileName;
	}

	public String getAjaxUploadRelated() {
		return ajaxUploadRelated;
	}

	public void setAjaxUploadRelated(String ajaxUploadRelated) {
		this.ajaxUploadRelated = ajaxUploadRelated;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}

	public static IOdAttService getOdAttService() {
		if (odAttService == null) {
			ServletContext sc = ServletActionContext.getServletContext();
			ApplicationContext ac = WebApplicationContextUtils
					.getRequiredWebApplicationContext(sc);
			odAttService = (IOdAttService) ac.getBean("OdAttService");
		}
		return odAttService;
	}

	public void setOdAttService(IOdAttService odAttService) {
		ODManageAction.odAttService = odAttService;
	}

	public OdAtt getOdAtt() {
		return odAtt;
	}

	public void setOdAtt(OdAtt odAtt) {
		this.odAtt = odAtt;
	}

	public List<OdAtt> getOdAtts() {
		return odAtts;
	}

	public void setOdAtts(List<OdAtt> odAtts) {
		this.odAtts = odAtts;
	}

	public String getAtts() {
		return atts;
	}

	public void setAtts(String atts) {
		this.atts = atts;
	}

	public String getOdRecordDate1() {
		return odRecordDate1;
	}

	public void setOdRecordDate1(String odRecordDate1) {
		this.odRecordDate1 = odRecordDate1;
	}

	public String getOdRecordDate2() {
		return odRecordDate2;
	}

	public void setOdRecordDate2(String odRecordDate2) {
		this.odRecordDate2 = odRecordDate2;
	}

	public String getOdRecordCometime1() {
		return odRecordCometime1;
	}

	public void setOdRecordCometime1(String odRecordCometime1) {
		this.odRecordCometime1 = odRecordCometime1;
	}

	public String getOdRecordCometime2() {
		return odRecordCometime2;
	}

	public void setOdRecordCometime2(String odRecordCometime2) {
		this.odRecordCometime2 = odRecordCometime2;
	}

}
