package dao;
// default package

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import model.OdRecord;

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
 	* A data access object (DAO) providing persistence and search support for OdRecord entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .OdRecord
  * @author MyEclipse Persistence Tools 
 */
public class OdRecordDAO extends HibernateDaoSupport implements IOdRecordDAO  {
	     private static final Logger log = LoggerFactory.getLogger(OdRecordDAO.class);
		protected void initDao() {
		//do nothing
	}
    
    /* （非 Javadoc）
	 * @see dao.IOdRecordDAO#save(model.OdRecord)
	 */
    @Override
	public void save(OdRecord transientInstance) {
        log.debug("saving OdRecord instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#delete(model.OdRecord)
	 */
	@Override
	public void delete(OdRecord persistentInstance) {
        log.debug("deleting OdRecord instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findById(java.lang.Integer)
	 */
    @Override
	public OdRecord findById( java.lang.Integer id) {
        log.debug("getting OdRecord instance with id: " + id);
        try {
            OdRecord instance = (OdRecord) getHibernateTemplate()
                    .get("model.OdRecord", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByExample(model.OdRecord)
	 */
    @Override
	public List findByExample(OdRecord instance) {
        log.debug("finding OdRecord instance by example");
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
	 * @see dao.IOdRecordDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding OdRecord instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from OdRecord as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordAdminid(java.lang.Object)
	 */
	@Override
	public List findByOdRecordAdminid(Object odRecordAdminid
	) {
		return findByProperty(OD_RECORD_ADMINID, odRecordAdminid
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordName(java.lang.Object)
	 */
	@Override
	public List findByOdRecordName(Object odRecordName
	) {
		return findByProperty(OD_RECORD_NAME, odRecordName
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordNum(java.lang.Object)
	 */
	@Override
	public List findByOdRecordNum(Object odRecordNum
	) {
		return findByProperty(OD_RECORD_NUM, odRecordNum
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordSerial(java.lang.Object)
	 */
	@Override
	public List findByOdRecordSerial(Object odRecordSerial
	) {
		return findByProperty(OD_RECORD_SERIAL, odRecordSerial
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordYear(java.lang.Object)
	 */
	@Override
	public List findByOdRecordYear(Object odRecordYear
	) {
		return findByProperty(OD_RECORD_YEAR, odRecordYear
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordLimit(java.lang.Object)
	 */
	@Override
	public List findByOdRecordLimit(Object odRecordLimit
	) {
		return findByProperty(OD_RECORD_LIMIT, odRecordLimit
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordOrg(java.lang.Object)
	 */
	@Override
	public List findByOdRecordOrg(Object odRecordOrg
	) {
		return findByProperty(OD_RECORD_ORG, odRecordOrg
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordFrom(java.lang.Object)
	 */
	@Override
	public List findByOdRecordFrom(Object odRecordFrom
	) {
		return findByProperty(OD_RECORD_FROM, odRecordFrom
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordKeywords(java.lang.Object)
	 */
	@Override
	public List findByOdRecordKeywords(Object odRecordKeywords
	) {
		return findByProperty(OD_RECORD_KEYWORDS, odRecordKeywords
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findByOdRecordRemark(java.lang.Object)
	 */
	@Override
	public List findByOdRecordRemark(Object odRecordRemark
	) {
		return findByProperty(OD_RECORD_REMARK, odRecordRemark
		);
	}
	

	/* （非 Javadoc）
	 * @see dao.IOdRecordDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all OdRecord instances");
		try {
			String queryString = "from OdRecord order by odRecordId desc";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* （非 Javadoc）
	 * @see dao.IOdRecordDAO#merge(model.OdRecord)
	 */
    @Override
	public OdRecord merge(OdRecord detachedInstance) {
        log.debug("merging OdRecord instance");
        try {
            OdRecord result = (OdRecord) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* （非 Javadoc）
	 * @see dao.IOdRecordDAO#attachDirty(model.OdRecord)
	 */
    @Override
	public void attachDirty(OdRecord instance) {
        log.debug("attaching dirty OdRecord instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.IOdRecordDAO#attachClean(model.OdRecord)
	 */
    @Override
	public void attachClean(OdRecord instance) {
        log.debug("attaching clean OdRecord instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static IOdRecordDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (IOdRecordDAO) ctx.getBean("OdRecordDAO");
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