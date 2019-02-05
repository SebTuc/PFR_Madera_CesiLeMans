package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.ModuleXComposant;
import com.ril.model.ModuleXComposantId;

/**
 * Home object for domain model class ModuleXComposant.
 * @see com.ril.daoHibernate.ModuleXComposant
 * @author Hibernate Tools
 */
@Stateless
public class ModuleXComposantHome {

	private static final Log log = LogFactory.getLog(ModuleXComposantHome.class);


	public void persist(ModuleXComposant transientInstance) {
		log.debug("persisting ModuleXComposant instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ModuleXComposant persistentInstance) {
		log.debug("removing ModuleXComposant instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ModuleXComposant merge(ModuleXComposant detachedInstance) {
		log.debug("merging ModuleXComposant instance");
		try {
			ModuleXComposant result = (ModuleXComposant) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ModuleXComposant findById(ModuleXComposantId id) {
		log.debug("getting ModuleXComposant instance with id: " + id);
		try {
			ModuleXComposant instance = HibernateUtil.getSession().find(ModuleXComposant.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
