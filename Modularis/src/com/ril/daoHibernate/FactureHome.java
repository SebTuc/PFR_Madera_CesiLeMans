package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Facture;

/**
 * Home object for domain model class Facture.
 * @see com.ril.daoHibernate.Facture
 * @author Hibernate Tools
 */
@Stateless
public class FactureHome {

	private static final Log log = LogFactory.getLog(FactureHome.class);

	public void persist(Facture transientInstance) {
		log.debug("persisting Facture instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Facture persistentInstance) {
		log.debug("removing Facture instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Facture merge(Facture detachedInstance) {
		log.debug("merging Facture instance");
		try {
			Facture result = (Facture) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Facture findById(Integer id) {
		log.debug("getting Facture instance with id: " + id);
		try {
			Facture instance = HibernateUtil.getSession().find(Facture.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
