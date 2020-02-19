package dao;
// default package

import java.util.List;

import model.OdAtt;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for model.OdAtt entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .model.OdAtt
  * @author MyEclipse Persistence Tools 
 */
public class OdAttDAO extends HibernateDaoSupport implements IOdAttDAO  {
	     private static final Logger log = LoggerFactory.getLogger(OdAttDAO.class);
		protected void initDao() {
		//do nothing
	}
    
    /* （非 Javadoc）
	 * @see dao.IOdAttDAO#save(model.model.OdAtt)
	 */
    @Override
	public void save(model.OdAtt transientInstance) {
        log.debug("saving model.OdAtt instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#delete(model.model.OdAtt)
	 */
	@Override
	public void delete(model.OdAtt persistentInstance) {
        log.debug("deleting model.OdAtt instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.IOdAttDAO#findById(java.lang.Integer)
	 */
    @Override
	public model.OdAtt findById( java.lang.Integer id) {
        log.debug("getting model.OdAtt instance with id: " + id);
        try {
            model.OdAtt instance = (model.OdAtt) getHibernateTemplate()
                    .get("model.OdAtt", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* （非 Javadoc）
	 * @see dao.IOdAttDAO#findByExample(model.model.OdAtt)
	 */
    @Override
	public List findByExample(model.OdAtt instance) {
        log.debug("finding model.OdAtt instance by example");
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
	 * @see dao.IOdAttDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding model.OdAtt instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from model.OdAtt as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#findByOdAttOdrid(java.lang.Object)
	 */
	@Override
	public List findByOdAttOdrid(Object odAttOdrid
	) {
		return findByProperty(OD_ATT_ODRID, odAttOdrid
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#findByOdAttFilename(java.lang.Object)
	 */
	@Override
	public List findByOdAttFilename(Object odAttFilename
	) {
		return findByProperty(OD_ATT_FILENAME, odAttFilename
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#findByOdAttPath(java.lang.Object)
	 */
	@Override
	public List findByOdAttPath(Object odAttPath
	) {
		return findByProperty(OD_ATT_PATH, odAttPath
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#findByOdAttSuffix(java.lang.Object)
	 */
	@Override
	public List findByOdAttSuffix(Object odAttSuffix
	) {
		return findByProperty(OD_ATT_SUFFIX, odAttSuffix
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#findByOdAttRemark(java.lang.Object)
	 */
	@Override
	public List findByOdAttRemark(Object odAttRemark
	) {
		return findByProperty(OD_ATT_REMARK, odAttRemark
		);
	}
	

	/* （非 Javadoc）
	 * @see dao.IOdAttDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all model.OdAtt instances");
		try {
			String queryString = "from model.OdAtt";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* （非 Javadoc）
	 * @see dao.IOdAttDAO#merge(model.model.OdAtt)
	 */
    @Override
	public model.OdAtt merge(model.OdAtt detachedInstance) {
        log.debug("merging model.OdAtt instance");
        try {
            model.OdAtt result = (model.OdAtt) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* （非 Javadoc）
	 * @see dao.IOdAttDAO#attachDirty(model.model.OdAtt)
	 */
    @Override
	public void attachDirty(model.OdAtt instance) {
        log.debug("attaching dirty model.OdAtt instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.IOdAttDAO#attachClean(model.model.OdAtt)
	 */
    @Override
	public void attachClean(model.OdAtt instance) {
        log.debug("attaching clean model.OdAtt instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static IOdAttDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (IOdAttDAO) ctx.getBean("OdAttDAO");
	}
}