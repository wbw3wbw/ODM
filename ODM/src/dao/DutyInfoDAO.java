package dao;
// default package

import java.sql.SQLException;
import java.util.List;

import model.DutyInfo;

import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for DutyInfo entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .DutyInfo
  * @author MyEclipse Persistence Tools 
 */
public class DutyInfoDAO extends HibernateDaoSupport implements IDutyInfoDAO  {
	     private static final Logger log = LoggerFactory.getLogger(DutyInfoDAO.class);
		protected void initDao() {
		//do nothing
	}
    
    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#save(model.DutyInfo)
	 */
    @Override
	public void save(DutyInfo transientInstance) {
        log.debug("saving DutyInfo instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#delete(model.DutyInfo)
	 */
	@Override
	public void delete(DutyInfo persistentInstance) {
        log.debug("deleting DutyInfo instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findById(java.lang.Integer)
	 */
    @Override
	public DutyInfo findById( java.lang.Integer id) {
        log.debug("getting DutyInfo instance with id: " + id);
        try {
            DutyInfo instance = (DutyInfo) getHibernateTemplate()
                    .get("model.DutyInfo", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByExample(model.DutyInfo)
	 */
    @Override
	public List findByExample(DutyInfo instance) {
        log.debug("finding DutyInfo instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding DutyInfo instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from DutyInfo as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByDutyInfoPersonid(java.lang.Object)
	 */
	@Override
	public List findByDutyInfoPersonid(Object dutyInfoPersonid
	) {
		return findByProperty(DUTY_INFO_PERSONID, dutyInfoPersonid
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByDutyInfoPersonname(java.lang.Object)
	 */
	@Override
	public List findByDutyInfoPersonname(Object dutyInfoPersonname
	) {
		return findByProperty(DUTY_INFO_PERSONNAME, dutyInfoPersonname
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByDutyInfoDate(java.lang.Object)
	 */
	@Override
	public List findByDutyInfoDate(Object dutyInfoDate
	) {
		return findByProperty(DUTY_INFO_DATE, dutyInfoDate
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByDutyInfoOrder(java.lang.Object)
	 */
	@Override
	public List findByDutyInfoOrder(Object dutyInfoOrder
	) {
		return findByProperty(DUTY_INFO_ORDER, dutyInfoOrder
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findByDutyInfoIsremind(java.lang.Object)
	 */
	@Override
	public List findByDutyInfoIsremind(Object dutyInfoIsremind
	) {
		return findByProperty(DUTY_INFO_ISREMIND, dutyInfoIsremind
		);
	}
	

	/* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all DutyInfo instances");
		try {
			String queryString = "from DutyInfo";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#merge(model.DutyInfo)
	 */
    @Override
	public DutyInfo merge(DutyInfo detachedInstance) {
        log.debug("merging DutyInfo instance");
        try {
            DutyInfo result = (DutyInfo) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#attachDirty(model.DutyInfo)
	 */
    @Override
	public void attachDirty(DutyInfo instance) {
        log.debug("attaching dirty DutyInfo instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.IDutyInfoDAO#attachClean(model.DutyInfo)
	 */
    @Override
	public void attachClean(DutyInfo instance) {
        log.debug("attaching clean DutyInfo instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static IDutyInfoDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (IDutyInfoDAO) ctx.getBean("DutyInfoDAO");
	}
	@Override
	public List queryForPage(final String hql, final int offset, final int length) {
	    List list = getHibernateTemplate().executeFind(new HibernateCallback() 
	    {
	      public Object  doInHibernate(Session session)throws HibernateException,SQLException 
	      { 
	          Query query = session.createQuery(hql); 
	          query.setFirstResult(offset); 
	          query.setMaxResults(length); 
	          List list = query.list(); 
	          return list; 
	       }
	     }); 
	   return list; 
	}

	@Override
	public int getAllRowCount(String hql) {
		return getHibernateTemplate().find(hql).size(); 
	}
}