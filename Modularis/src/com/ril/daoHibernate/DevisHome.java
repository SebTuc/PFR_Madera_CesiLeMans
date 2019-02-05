package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Devis;

/**
 * Home object for domain model class Devis.
 * @see com.ril.daoHibernate.Devis
 * @author Hibernate Tools
 */
@Stateless
public class DevisHome {

	private static Logger log = Logger.getLogger(DevisHome.class.toString());

	public void persist(Devis transientInstance) {
		log.debug("persisting Devis instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Devis persistentInstance) {
		log.debug("removing Devis instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Devis merge(Devis detachedInstance) {
		log.debug("merging Devis instance");
		try {
			Devis result = (Devis) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Devis findById(Integer id) {
		log.debug("getting Devis instance with id: " + id);
		try {
			Devis instance = HibernateUtil.getSession().find(Devis.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
