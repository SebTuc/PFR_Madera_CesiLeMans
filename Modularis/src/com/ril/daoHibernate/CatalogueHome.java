package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Catalogue;

/**
 * Home object for domain model class Catalogue.
 * @see com.ril.daoHibernate.Catalogue
 * @author Hibernate Tools
 */
@Stateless
public class CatalogueHome {

	private static final Log log = LogFactory.getLog(CatalogueHome.class);

	public void persist(Catalogue transientInstance) {
		log.debug("persisting Catalogue instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Catalogue persistentInstance) {
		log.debug("removing Catalogue instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Catalogue merge(Catalogue detachedInstance) {
		log.debug("merging Catalogue instance");
		try {
			Catalogue result = (Catalogue) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Catalogue findById(Integer id) {
		log.debug("getting Catalogue instance with id: " + id);
		try {
			Catalogue instance = HibernateUtil.getSession().find(Catalogue.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
