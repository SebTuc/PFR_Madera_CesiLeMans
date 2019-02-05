package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Fournisseur;

/**
 * Home object for domain model class Fournisseur.
 * @see com.ril.daoHibernate.Fournisseur
 * @author Hibernate Tools
 */
@Stateless
public class FournisseurHome {

	private static Logger log = Logger.getLogger(FournisseurHome.class.toString());

	public void persist(Fournisseur transientInstance) {
		log.debug("persisting Fournisseur instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Fournisseur persistentInstance) {
		log.debug("removing Fournisseur instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Fournisseur merge(Fournisseur detachedInstance) {
		log.debug("merging Fournisseur instance");
		try {
			Fournisseur result = (Fournisseur) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Fournisseur findById(Integer id) {
		log.debug("getting Fournisseur instance with id: " + id);
		try {
			Fournisseur instance = HibernateUtil.getSession().find(Fournisseur.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
