package dao;

import java.util.List;

import model.AdminInfo;

public interface IAdminInfoDAO {

	// property constants
	public static final String ADMIN_PWD = "adminPwd";
	public static final String ADMIN_NAME = "adminName";
	public static final String ADMIN_TELPHONE = "adminTelphone";
	public static final String ADMIN_MAIL = "adminMail";
	public static final String ADMIN_LEVEL = "adminLevel";
	public static final String ADMIN_POWER = "adminPower";

	public abstract void save(AdminInfo transientInstance);

	public abstract void delete(AdminInfo persistentInstance);

	public abstract AdminInfo findById(java.lang.String id);

	public abstract List findByExample(AdminInfo instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByAdminPwd(Object adminPwd);

	public abstract List findByAdminName(Object adminName);

	public abstract List findByAdminTelphone(Object adminTelphone);

	public abstract List findByAdminMail(Object adminMail);

	public abstract List findByAdminLevel(Object adminLevel);

	public abstract List findByAdminPower(Object adminPower);

	public abstract List findAll();

	public abstract AdminInfo merge(AdminInfo detachedInstance);

	public abstract void attachDirty(AdminInfo instance);

	public abstract void attachClean(AdminInfo instance);

}