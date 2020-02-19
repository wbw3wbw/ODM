package dao;

import java.util.List;

import model.OdAtt;

public interface IOdAttDAO {

	//property constants
	public static final String OD_ATT_ODRID = "odAttOdrid";
	public static final String OD_ATT_FILENAME = "odAttFilename";
	public static final String OD_ATT_PATH = "odAttPath";
	public static final String OD_ATT_SUFFIX = "odAttSuffix";
	public static final String OD_ATT_REMARK = "odAttRemark";

	public abstract void save(OdAtt transientInstance);

	public abstract void delete(OdAtt persistentInstance);

	public abstract OdAtt findById(java.lang.Integer id);

	public abstract List findByExample(OdAtt instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByOdAttOdrid(Object odAttOdrid);

	public abstract List findByOdAttFilename(Object odAttFilename);

	public abstract List findByOdAttPath(Object odAttPath);

	public abstract List findByOdAttSuffix(Object odAttSuffix);

	public abstract List findByOdAttRemark(Object odAttRemark);

	public abstract List findAll();

	public abstract OdAtt merge(OdAtt detachedInstance);

	public abstract void attachDirty(OdAtt instance);

	public abstract void attachClean(OdAtt instance);

}