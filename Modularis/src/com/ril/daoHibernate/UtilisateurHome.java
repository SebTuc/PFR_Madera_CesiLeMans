package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Utilisateur;

/**
 * Home object for domain model class Utilisateur.
 * @see com.ril.daoHibernate.Utilisateur
 * @author Hibernate Tools
 */
@Stateless
public class UtilisateurHome {

	private static Logger log = Logger.getLogger(UtilisateurHome.class.toString());

	public void persist(Utilisateur transientInstance) {
		log.debug("persisting Utilisateur instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Utilisateur persistentInstance) {
		log.debug("removing Utilisateur instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Utilisateur merge(Utilisateur detachedInstance) {
		log.debug("merging Utilisateur instance");
		try {
			Utilisateur result = (Utilisateur) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Utilisateur findById(Integer id) {
		log.debug("getting Utilisateur instance with id: " + id);
		try {
			Utilisateur instance = HibernateUtil.getSession().find(Utilisateur.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
