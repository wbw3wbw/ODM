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
	private int pageIndex = 1;// ��ǰ�� ��ҳ
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
	 * �г��������ļ�¼
	 */
	public String  listAllOdRecords(){
		setOdRecords(getOdRecordService().findAll());
		return "success";
	}
	
	/**
	 * �г����з��ϲ�ѯ���������ļ�¼
	 */
	public String  queryOdRecords(){
		String hql = "from OdRecord as model";
		String where = "";
//		System.out.println("��ʼ��ѯ����ǰpageIndex:"+pageIndex);
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
	 * ���ĵǼǣ������޸ģ�
	 * @�����ڼ�¼-�������Ѵ��ڼ�¼-�޸�
	 * @throws Exception
	 */
	public String saveOdRecord() throws Exception{
		try{
			//��session�ж�ȡ��ǰ��¼�û�����Ϣ
			ActionContext context = ActionContext.getContext();
			adminInfo = (AdminInfo) context.getSession().get("LOGINADMIN");
			if(null == odRecord.getOdRecordId()){
				odRecord.setOdRecordAdminid(adminInfo.getAdminName());
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				odRecord.setOdRecordInstime(timestamp);
			}
			//�������ļ�¼
			getOdRecordService().addOdRecord(odRecord);
			//System.out.println("׼���ϴ��ĸ���IDΪ��"+atts);
			String[] strArray = atts.split("-=-");
			for(int i=0; i<strArray.length; i++){
				odAtt = getOdAttService().findByID(Integer.parseInt(strArray[i]));
				odAtt.setOdAttOdrid(odRecord.getOdRecordId());
				getOdAttService().updateOdAtt(odAtt);
			}

			this.setMessage("����ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("��������������������룡");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * ���Ĳ鿴
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
			this.setMessage("��������������������룡");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * ���Ĳ鿴�������޸�ģʽ
	 * @return
	 * @throws Exception
	 */
	public String editOdRecord() throws Exception{
		try{
			setOdRecord( getOdRecordService().findByID(odRecord.getOdRecordId()));
			setOdAtts(getOdAttService().findByOdRecordId(odRecord.getOdRecordId()));
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("��������������������룡");
			return "falure";
		}
		return "success";
	}
	
	
	/**
	 * ����ɾ��
	 * @return
	 * @throws Exception
	 */
	public String deleteOdRecord() throws Exception{
		try{
			setOdRecord( getOdRecordService().findByID(odRecord.getOdRecordId()));
			getOdRecordService().delOdRecord(odRecord);
			this.setMessage("�ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("��������������������룡");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * ���ĸ���ɾ��
	 * @return
	 * @throws Exception
	 */
	public String deleteOdAtt() throws Exception{
		try{
			setOdAtt( getOdAttService().findByID(odAtt.getOdAttId()));
			getOdAttService().delOdAtt(odAtt);
			this.setMessage("�ɹ�");
		}catch (Exception e) {
			e.printStackTrace();
			this.setMessage("��������������������룡");
			return "falure";
		}
		return "success";
	}
	
	/**
	 * ajax�ϴ��ļ������
	 * @return
	 */
	public String ajaxUploadOdAtt(){
		//��session�ж�ȡ��ǰ��¼�û�����Ϣ
		ActionContext context = ActionContext.getContext();
		adminInfo = (AdminInfo) context.getSession().get("LOGINADMIN");
		
		String path="";
		String newFileName = "";
		int count = 0;
        // ��һ��Map������
        Map<String, String> map = new HashMap<String, String>();  
		//���û���ļ���Ҫ�ϴ�
		if(ajaxUpload==null){
	        map.put("result", "δѡ���ļ�");  
	        // ��Ҫ���ص�map�������json����  
	        JSONObject jo = JSONObject.fromObject(map);  
	        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result  
	        this.message = jo.toString();  
			return "success";
		}
		try {	
	//		System.out.println("����Ŀ¼��ʼ");
			Date date = new Date();
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			path = "D:\\ODMupload\\OdAtts\\"+sdf2.format(date); 
			// д��ָ����·����  
			File file = new File(path);  
		    // ���ָ����·��û�оʹ���  
			if (!file.exists()) {
				file.mkdirs();  
		    }
			//���ļ�������
			newFileName = sdf1.format(date)+ajaxUploadFileName;
			//���ļ�copy��ָ��Ŀ¼
	       FileUtils.copyFile(ajaxUpload, new File(file, newFileName));  
	       System.out.println("�ɹ��ϴ��ļ���"+path+"\\"+newFileName+"���ļ���С��"+(double)(ajaxUpload.length()/1024)+"K");
			String suffix = "";//��ȡ�ļ���׺��
			suffix = ajaxUploadFileName.substring(ajaxUploadFileName.lastIndexOf(".")+1);
			suffix = suffix.toLowerCase();
	       odAtt.setOdAttFilename(ajaxUploadFileName);
	       odAtt.setOdAttRemark(newFileName);
	       odAtt.setOdAttPath(path);
	       odAtt.setOdAttSuffix(suffix);
	       //�Ѹ�����¼��ŵ����ݿ���
	       getOdAttService().addOdAtt(odAtt);
		} catch (Exception e) {
			e.printStackTrace();  
		}
        map.put("result", "�ɹ�");  
        map.put("name", ajaxUploadFileName);
        map.put("attid", odAtt.getOdAttId().toString());
        // ��Ҫ���ص�map�������json����  
        JSONObject jo = JSONObject.fromObject(map);  
        // ����json�����toString����ת��Ϊ�ַ���Ȼ��ֵ��result  
        this.message = jo.toString();  
		return "success";
	}
	
	/**
	 * ��ȡ����excel��¼�����
	 * @return
	 */
	public String impOdRecords(){
		//ִ���ļ������ϴ�
		String path="";
		String newFileName = "";
		int count = 0;
        // ��һ��Map������
        Map<String, String> map = new HashMap<String, String>();  
		try {	    
	//		System.out.println("����Ŀ¼��ʼ");
			path = "D:\\ODMupload\\impOdRecords\\"; 
			// д��ָ����·����  
			File file = new File(path);  
		    // ���ָ����·��û�оʹ���  
			if (!file.exists()) {
				file.mkdirs();  
		    }
			//���ļ�������
			newFileName = ajaxUploadFileName;
	       //list����ͨ��get(i)�ķ�ʽ����ȡ���� 
	       FileUtils.copyFile(ajaxUpload, new File(file, newFileName));  
		} catch (Exception e) {
			e.printStackTrace();  
		}
		
		System.out.println("��ʼ���");
		ActionContext context = ActionContext.getContext();
		adminInfo = (AdminInfo)context.getSession().get("LOGINADMIN");
		ExcelReader excelReader = new ExcelReader();
		ArrayList<String> strList = null;
		String suffix = "";
		suffix = ajaxUploadFileName.substring(ajaxUploadFileName.lastIndexOf(".")+1);
		suffix = suffix.toLowerCase();
		//ֻ��ȡexcel�ļ�
		if(!"xls".equals(suffix) && !"xlsx".equals(suffix) ){
	        map.put("result", "ʧ��");  
	        map.put("reason", "��֧��excel�ļ�");
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
			System.out.println("������ʼ��Excel�������ļ�¼����");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			odRecord.setOdRecordAdminid(adminInfo.getAdminName());
			//�ӵڶ������ݿ�ʼд�����ݿ�
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
	        map.put("result", "ʧ��");  
	        map.put("reason", "ϵͳ��������ϵ������Ա");
	        JSONObject jo = JSONObject.fromObject(map);  
	        this.message = jo.toString();  
			return "success";
		}
        map.put("result", "�ɹ�");  
        map.put("name", ajaxUploadFileName+"��������"+count+"����¼");
        JSONObject jo = JSONObject.fromObject(map);  
        this.message = jo.toString();  
		return "success";
	}
	
	
	/**
	 * ����ģ������
	 * @return
	 */
	public String downloadTemplet() throws FileNotFoundException,UnsupportedEncodingException {
		// ��������ļ���Ϊ���ģ������ַ�����ת��
		//ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		fileName = "templet1.xlsx";
		//fileName = new String(fileName.getBytes(), "ISO8859-1");
		File file = new File("D:\\ODMupload\\templet\\"+fileName);
		downloadStream = new FileInputStream(file);
		return "success";
	}
	
	
	/**
	 * �ļ�����
	 * @return
	 */
	public String downloadFile() throws FileNotFoundException,UnsupportedEncodingException {
		// ��������ļ���Ϊ���ģ������ַ�����ת��
		//ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
		odAtt = getOdAttService().findByID(odAtt.getOdAttId());
		fileName = odAtt.getOdAttFilename();
		fileName = new String(fileName.getBytes(), "ISO8859-1");
		File file = new File(odAtt.getOdAttPath()+"\\"+odAtt.getOdAttRemark());
		downloadStream = new FileInputStream(file);
		return "success";
	}
	/**
	 * ������ص��������µĵ������ļ�
	 * 2019-04-03����
	 */
	public String zipDownload(){
		odRecord = getOdRecordService().findByID(odRecord.getOdRecordId());
		odAtts = getOdAttService().findByOdRecordId(odRecord.getOdRecordId());
        fileName = odRecord.getOdRecordName()+"--����.zip";
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
	        //ѭ����ȡ�û��ϴ����ļ�
            for (int i = 0; i < odAtts.size(); i++) {    
            	File file = new File(odAtts.get(i).getOdAttPath()+"\\"+odAtts.get(i).getOdAttRemark());
                FileInputStream fis = new FileInputStream(file);    
                out.putNextEntry(new ZipEntry(odAtts.get(i).getOdAttFilename()));    
                //����ѹ���ļ��ڵ��ַ����룬��Ȼ��������    
                out.setEncoding("GBK");    
                // ������Ҫ���ص��ļ������ݣ������zip�ļ�   
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
