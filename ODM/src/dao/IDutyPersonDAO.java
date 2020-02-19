package dao;

import java.util.List;

import model.DutyPerson;

public interface IDutyPersonDAO {

	// property constants
	public static final String DUTY_PERSON_NAME = "dutyPersonName";
	public static final String DUTY_PERSON_PHONE = "dutyPersonPhone";
	public static final String DUTY_PERSON_SEX = "dutyPersonSex";
	public static final String DUTY_PERSON_MAIL = "dutyPersonMail";
	public static final String DUTY_PERSON_STATUS = "dutyPersonStatus";
	public static final String DUTY_PERSON_REMARK = "dutyPersonRemark";

	public abstract void save(DutyPerson transientInstance);

	public abstract void delete(DutyPerson persistentInstance);

	public abstract DutyPerson findById(java.lang.Integer id);

	public abstract List findByExample(DutyPerson instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByDutyPersonName(Object dutyPersonName);

	public abstract List findByDutyPersonPhone(Object dutyPersonPhone);

	public abstract List findByDutyPersonSex(Object dutyPersonSex);

	public abstract List findByDutyPersonMail(Object dutyPersonMail);

	public abstract List findByDutyPersonStatus(Object dutyPersonStatus);

	public abstract List findByDutyPersonRemark(Object dutyPersonRemark);

	public abstract List findAll();

	public abstract DutyPerson merge(DutyPerson detachedInstance);

	public abstract void attachDirty(DutyPerson instance);

	public abstract void attachClean(DutyPerson instance);

}