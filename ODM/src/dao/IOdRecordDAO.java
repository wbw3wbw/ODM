package dao;

import java.util.List;

import model.OdRecord;

public interface IOdRecordDAO {

	//property constants
	public static final String OD_RECORD_ADMINID = "odRecordAdminid";
	public static final String OD_RECORD_NAME = "odRecordName";
	public static final String OD_RECORD_NUM = "odRecordNum";
	public static final String OD_RECORD_SERIAL = "odRecordSerial";
	public static final String OD_RECORD_YEAR = "odRecordYear";
	public static final String OD_RECORD_LIMIT = "odRecordLimit";
	public static final String OD_RECORD_ORG = "odRecordOrg";
	public static final String OD_RECORD_FROM = "odRecordFrom";
	public static final String OD_RECORD_KEYWORDS = "odRecordKeywords";
	public static final String OD_RECORD_REMARK = "odRecordRemark";

	public abstract void save(OdRecord transientInstance);

	public abstract void delete(OdRecord persistentInstance);

	public abstract OdRecord findById(java.lang.Integer id);

	public abstract List findByExample(OdRecord instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByOdRecordAdminid(Object odRecordAdminid);

	public abstract List findByOdRecordName(Object odRecordName);

	public abstract List findByOdRecordNum(Object odRecordNum);

	public abstract List findByOdRecordSerial(Object odRecordSerial);

	public abstract List findByOdRecordYear(Object odRecordYear);

	public abstract List findByOdRecordLimit(Object odRecordLimit);

	public abstract List findByOdRecordOrg(Object odRecordOrg);

	public abstract List findByOdRecordFrom(Object odRecordFrom);

	public abstract List findByOdRecordKeywords(Object odRecordKeywords);

	public abstract List findByOdRecordRemark(Object odRecordRemark);

	public abstract List findAll();

	public abstract OdRecord merge(OdRecord detachedInstance);

	public abstract void attachDirty(OdRecord instance);

	public abstract void attachClean(OdRecord instance);

	/**  
	   * 分页查询 
	   * @param hql 查询的条件 
	   * @param offset 开始记录 
	   * @param length 一次查询几条记录 
	   * @return 
	   */ 
	public List queryForPage(final String hql,final int offset,final int length); 
	/** 
	 * 查询所有记录数 
	 * @param hql 查询的条件 
	 * @return 总记录数 
	 */ 
	public int getAllRowCount(String hql); 
}