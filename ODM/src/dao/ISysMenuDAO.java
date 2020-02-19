package dao;

import java.util.List;

import model.SysMenu;

public interface ISysMenuDAO {

	//property constants
	public static final String SYS_MENU_NAME = "sysMenuName";
	public static final String SYS_MENU_LEVEL = "sysMenuLevel";
	public static final String SYS_MENU_PARENTID = "sysMenuParentid";
	public static final String SYS_MENU_ICON = "sysMenuIcon";
	public static final String SYS_MENU_IMAGE = "sysMenuImage";
	public static final String SYS_MENU_ADDRESS = "sysMenuAddress";
	public static final String SYS_MENU_CLICK = "sysMenuClick";
	public static final String SYS_MENU_SPARE1 = "sysMenuSpare1";
	public static final String SYS_MENU_SPARE2 = "sysMenuSpare2";
	public static final String SYS_MENU_SPARE3 = "sysMenuSpare3";

	public abstract void save(SysMenu transientInstance);

	public abstract void delete(SysMenu persistentInstance);

	public abstract SysMenu findById(java.lang.Integer id);

	public abstract List findByExample(SysMenu instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findBySysMenuName(Object sysMenuName);

	public abstract List findBySysMenuLevel(Object sysMenuLevel);

	public abstract List findBySysMenuParentid(Object sysMenuParentid);

	public abstract List findBySysMenuIcon(Object sysMenuIcon);

	public abstract List findBySysMenuImage(Object sysMenuImage);

	public abstract List findBySysMenuAddress(Object sysMenuAddress);

	public abstract List findBySysMenuClick(Object sysMenuClick);

	public abstract List findBySysMenuSpare1(Object sysMenuSpare1);

	public abstract List findBySysMenuSpare2(Object sysMenuSpare2);

	public abstract List findBySysMenuSpare3(Object sysMenuSpare3);

	public abstract List findAll();

	public abstract SysMenu merge(SysMenu detachedInstance);

	public abstract void attachDirty(SysMenu instance);

	public abstract void attachClean(SysMenu instance);

}