package dao;

import java.sql.Timestamp;
import java.util.List;

import model.AdminInfo;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * AdminInfo entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see model.AdminInfo
 * @author MyEclipse Persistence Tools
 */
public class AdminInfoDAO extends HibernateDaoSupport implements IAdminInfoDAO {
	private static final Logger log = LoggerFactory
			.getLogger(AdminInfoDAO.class);
	protected void initDao() {
		// do nothing
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#save(model.AdminInfo)
	 */
	@Override
	public void save(AdminInfo transientInstance) {
		log.debug("saving AdminInfo instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#delete(model.AdminInfo)
	 */
	@Override
	public void delete(AdminInfo persistentInstance) {
		log.debug("deleting AdminInfo instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findById(java.lang.String)
	 */
	@Override
	public AdminInfo findById(java.lang.String id) {
		log.debug("getting AdminInfo instance with id: " + id);
		try {
			AdminInfo instance = (AdminInfo) getHibernateTemplate().get(
					"model.AdminInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByExample(model.AdminInfo)
	 */
	@Override
	public List findByExample(AdminInfo instance) {
		log.debug("finding AdminInfo instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding AdminInfo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AdminInfo as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByAdminPwd(java.lang.Object)
	 */
	@Override
	public List findByAdminPwd(Object adminPwd) {
		return findByProperty(ADMIN_PWD, adminPwd);
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByAdminName(java.lang.Object)
	 */
	@Override
	public List findByAdminName(Object adminName) {
		return findByProperty(ADMIN_NAME, adminName);
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByAdminTelphone(java.lang.Object)
	 */
	@Override
	public List findByAdminTelphone(Object adminTelphone) {
		return findByProperty(ADMIN_TELPHONE, adminTelphone);
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByAdminMail(java.lang.Object)
	 */
	@Override
	public List findByAdminMail(Object adminMail) {
		return findByProperty(ADMIN_MAIL, adminMail);
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByAdminLevel(java.lang.Object)
	 */
	@Override
	public List findByAdminLevel(Object adminLevel) {
		return findByProperty(ADMIN_LEVEL, adminLevel);
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findByAdminPower(java.lang.Object)
	 */
	@Override
	public List findByAdminPower(Object adminPower) {
		return findByProperty(ADMIN_POWER, adminPower);
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all AdminInfo instances");
		try {
			String queryString = "from AdminInfo";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#merge(model.AdminInfo)
	 */
	@Override
	public AdminInfo merge(AdminInfo detachedInstance) {
		log.debug("merging AdminInfo instance");
		try {
			AdminInfo result = (AdminInfo) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#attachDirty(model.AdminInfo)
	 */
	@Override
	public void attachDirty(AdminInfo instance) {
		log.debug("attaching dirty AdminInfo instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IAdminInfoDAO#attachClean(model.AdminInfo)
	 */
	@Override
	public void attachClean(AdminInfo instance) {
		log.debug("attaching clean AdminInfo instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IAdminInfoDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IAdminInfoDAO) ctx.getBean("AdminInfoDAO");
	}
}