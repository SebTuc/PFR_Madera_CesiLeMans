package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Module;

/**
 * Home object for domain model class Module.
 * @see com.ril.daoHibernate.Module
 * @author Hibernate Tools
 */
@Stateless
public class ModuleHome {

	private static final Log log = LogFactory.getLog(ModuleHome.class);

	public void persist(Module transientInstance) {
		log.debug("persisting Module instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Module persistentInstance) {
		log.debug("removing Module instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Module merge(Module detachedInstance) {
		log.debug("merging Module instance");
		try {
			Module result = (Module) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Module findById(Integer id) {
		log.debug("getting Module instance with id: " + id);
		try {
			Module instance = HibernateUtil.getSession().find(Module.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
