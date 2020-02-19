package dao;

import java.util.List;

import model.DutyPerson;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * A data access object (DAO) providing persistence and search support for
 * DutyPerson entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see model.DutyPerson
 * @author MyEclipse Persistence Tools
 */
public class DutyPersonDAO extends HibernateDaoSupport implements IDutyPersonDAO {
	private static final Logger log = LoggerFactory
			.getLogger(DutyPersonDAO.class);
	protected void initDao() {
		// do nothing
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#save(model.DutyPerson)
	 */
	@Override
	public void save(DutyPerson transientInstance) {
		log.debug("saving DutyPerson instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#delete(model.DutyPerson)
	 */
	@Override
	public void delete(DutyPerson persistentInstance) {
		log.debug("deleting DutyPerson instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findById(java.lang.Integer)
	 */
	@Override
	public DutyPerson findById(java.lang.Integer id) {
		log.debug("getting DutyPerson instance with id: " + id);
		try {
			DutyPerson instance = (DutyPerson) getHibernateTemplate().get(
					"model.DutyPerson", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByExample(model.DutyPerson)
	 */
	@Override
	public List findByExample(DutyPerson instance) {
		log.debug("finding DutyPerson instance by example");
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
	 * @see dao.IDutyPersonDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DutyPerson instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DutyPerson as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByDutyPersonName(java.lang.Object)
	 */
	@Override
	public List findByDutyPersonName(Object dutyPersonName) {
		return findByProperty(DUTY_PERSON_NAME, dutyPersonName);
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByDutyPersonPhone(java.lang.Object)
	 */
	@Override
	public List findByDutyPersonPhone(Object dutyPersonPhone) {
		return findByProperty(DUTY_PERSON_PHONE, dutyPersonPhone);
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByDutyPersonSex(java.lang.Object)
	 */
	@Override
	public List findByDutyPersonSex(Object dutyPersonSex) {
		return findByProperty(DUTY_PERSON_SEX, dutyPersonSex);
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByDutyPersonMail(java.lang.Object)
	 */
	@Override
	public List findByDutyPersonMail(Object dutyPersonMail) {
		return findByProperty(DUTY_PERSON_MAIL, dutyPersonMail);
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByDutyPersonStatus(java.lang.Object)
	 */
	@Override
	public List findByDutyPersonStatus(Object dutyPersonStatus) {
		return findByProperty(DUTY_PERSON_STATUS, dutyPersonStatus);
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findByDutyPersonRemark(java.lang.Object)
	 */
	@Override
	public List findByDutyPersonRemark(Object dutyPersonRemark) {
		return findByProperty(DUTY_PERSON_REMARK, dutyPersonRemark);
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all DutyPerson instances");
		try {
			String queryString = "from DutyPerson";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#merge(model.DutyPerson)
	 */
	@Override
	public DutyPerson merge(DutyPerson detachedInstance) {
		log.debug("merging DutyPerson instance");
		try {
			DutyPerson result = (DutyPerson) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#attachDirty(model.DutyPerson)
	 */
	@Override
	public void attachDirty(DutyPerson instance) {
		log.debug("attaching dirty DutyPerson instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* （非 Javadoc）
	 * @see dao.IDutyPersonDAO#attachClean(model.DutyPerson)
	 */
	@Override
	public void attachClean(DutyPerson instance) {
		log.debug("attaching clean DutyPerson instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IDutyPersonDAO getFromApplicationContext(ApplicationContext ctx) {
		return (IDutyPersonDAO) ctx.getBean("DutyPersonDAO");
	}
}