package dao;

import java.util.List;

import model.DutyInfo;

public interface IDutyInfoDAO {

	//property constants
	public static final String DUTY_INFO_PERSONID = "dutyInfoPersonid";
	public static final String DUTY_INFO_PERSONNAME = "dutyInfoPersonname";
	public static final String DUTY_INFO_DATE = "dutyInfoDate";
	public static final String DUTY_INFO_ORDER = "dutyInfoOrder";
	public static final String DUTY_INFO_ISREMIND = "dutyInfoIsremind";

	public abstract void save(DutyInfo transientInstance);

	public abstract void delete(DutyInfo persistentInstance);

	public abstract DutyInfo findById(java.lang.Integer id);

	public abstract List findByExample(DutyInfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByDutyInfoPersonid(Object dutyInfoPersonid);

	public abstract List findByDutyInfoPersonname(Object dutyInfoPersonname);

	public abstract List findByDutyInfoDate(Object dutyInfoDate);

	public abstract List findByDutyInfoOrder(Object dutyInfoOrder);

	public abstract List findByDutyInfoIsremind(Object dutyInfoIsremind);

	public abstract List findAll();

	public abstract DutyInfo merge(DutyInfo detachedInstance);

	public abstract void attachDirty(DutyInfo instance);

	public abstract void attachClean(DutyInfo instance);
	/**  
	   * ��ҳ��ѯ 
	   * @param hql ��ѯ������ 
	   * @param offset ��ʼ��¼ 
	   * @param length һ�β�ѯ������¼ 
	   * @return 
	   */ 
	public List queryForPage(final String hql,final int offset,final int length); 
	/** 
	 * ��ѯ���м�¼�� 
	 * @param hql ��ѯ������ 
	 * @return �ܼ�¼�� 
	 */ 
	public int getAllRowCount(String hql); 
}