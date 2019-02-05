package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Etat;

/**
 * Home object for domain model class Etat.
 * @see com.ril.daoHibernate.Etat
 * @author Hibernate Tools
 */
@Stateless
public class EtatHome {

	private static Logger log = Logger.getLogger(EtatHome.class.toString());

	public void persist(Etat transientInstance) {
		log.debug("persisting Etat instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Etat persistentInstance) {
		log.debug("removing Etat instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Etat merge(Etat detachedInstance) {
		log.debug("merging Etat instance");
		try {
			Etat result = (Etat) HibernateUtil.getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Etat findById(Integer id) {
		log.debug("getting Etat instance with id: " + id);
		try {
			Etat instance = HibernateUtil.getSession().find(Etat.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
