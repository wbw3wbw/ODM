package dao;
// default package

import java.util.List;

import model.SysMenu;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 	* A data access object (DAO) providing persistence and search support for sysMenu entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see .sysMenu
  * @author MyEclipse Persistence Tools 
 */
public class SysMenuDAO extends HibernateDaoSupport implements ISysMenuDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SysMenuDAO.class);
		protected void initDao() {
		//do nothing
	}
    
    /* （非 Javadoc）
	 * @see dao.ISysMenuDAO#save(model.SysMenu)
	 */
    @Override
	public void save(SysMenu transientInstance) {
        log.debug("saving sysMenu instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#delete(model.SysMenu)
	 */
	@Override
	public void delete(SysMenu persistentInstance) {
        log.debug("deleting sysMenu instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findById(java.lang.Integer)
	 */
    @Override
	public SysMenu findById( java.lang.Integer id) {
        log.debug("getting sysMenu instance with id: " + id);
        try {
            SysMenu instance = (SysMenu) getHibernateTemplate()
                    .get("model.SysMenu", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    /* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findByExample(model.SysMenu)
	 */
    @Override
	public List findByExample(SysMenu instance) {
        log.debug("finding sysMenu instance by example");
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
	 * @see dao.ISysMenuDAO#findByProperty(java.lang.String, java.lang.Object)
	 */
    @Override
	public List findByProperty(String propertyName, Object value) {
      log.debug("finding sysMenu instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SysMenu as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuName(java.lang.Object)
	 */
	@Override
	public List findBySysMenuName(Object sysMenuName
	) {
		return findByProperty(SYS_MENU_NAME, sysMenuName
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuLevel(java.lang.Object)
	 */
	@Override
	public List findBySysMenuLevel(Object sysMenuLevel
	) {
		return findByProperty(SYS_MENU_LEVEL, sysMenuLevel
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuParentid(java.lang.Object)
	 */
	@Override
	public List findBySysMenuParentid(Object sysMenuParentid
	) {
		return findByProperty(SYS_MENU_PARENTID, sysMenuParentid
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuIcon(java.lang.Object)
	 */
	@Override
	public List findBySysMenuIcon(Object sysMenuIcon
	) {
		return findByProperty(SYS_MENU_ICON, sysMenuIcon
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuImage(java.lang.Object)
	 */
	@Override
	public List findBySysMenuImage(Object sysMenuImage
	) {
		return findByProperty(SYS_MENU_IMAGE, sysMenuImage
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuAddress(java.lang.Object)
	 */
	@Override
	public List findBySysMenuAddress(Object sysMenuAddress
	) {
		return findByProperty(SYS_MENU_ADDRESS, sysMenuAddress
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuClick(java.lang.Object)
	 */
	@Override
	public List findBySysMenuClick(Object sysMenuClick
	) {
		return findByProperty(SYS_MENU_CLICK, sysMenuClick
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuSpare1(java.lang.Object)
	 */
	@Override
	public List findBySysMenuSpare1(Object sysMenuSpare1
	) {
		return findByProperty(SYS_MENU_SPARE1, sysMenuSpare1
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuSpare2(java.lang.Object)
	 */
	@Override
	public List findBySysMenuSpare2(Object sysMenuSpare2
	) {
		return findByProperty(SYS_MENU_SPARE2, sysMenuSpare2
		);
	}
	
	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findBySysMenuSpare3(java.lang.Object)
	 */
	@Override
	public List findBySysMenuSpare3(Object sysMenuSpare3
	) {
		return findByProperty(SYS_MENU_SPARE3, sysMenuSpare3
		);
	}
	

	/* （非 Javadoc）
	 * @see dao.ISysMenuDAO#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all sysMenu instances");
		try {
			String queryString = "from SysMenu";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    /* （非 Javadoc）
	 * @see dao.ISysMenuDAO#merge(model.SysMenu)
	 */
    @Override
	public SysMenu merge(SysMenu detachedInstance) {
        log.debug("merging sysMenu instance");
        try {
            SysMenu result = (SysMenu) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    /* （非 Javadoc）
	 * @see dao.ISysMenuDAO#attachDirty(model.SysMenu)
	 */
    @Override
	public void attachDirty(SysMenu instance) {
        log.debug("attaching dirty sysMenu instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    /* （非 Javadoc）
	 * @see dao.ISysMenuDAO#attachClean(model.SysMenu)
	 */
    @Override
	public void attachClean(SysMenu instance) {
        log.debug("attaching clean sysMenu instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ISysMenuDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ISysMenuDAO) ctx.getBean("sysMenuDAO");
	}
}