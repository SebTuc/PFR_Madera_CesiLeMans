package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Composant;

/**
 * Home object for domain model class Composant.
 * @see com.ril.daoHibernate.Composant
 * @author Hibernate Tools
 */
@Stateless
public class ComposantHome {

	private static final Log log = LogFactory.getLog(ComposantHome.class);

	public void persist(Composant transientInstance) {
		log.debug("persisting Composant instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Composant persistentInstance) {
		log.debug("removing Composant instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);			
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Composant merge(Composant detachedInstance) {
		log.debug("merging Composant instance");
		try {
			Composant result = (Composant) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Composant findById(Integer id) {
		log.debug("getting Composant instance with id: " + id);
		try {
			Composant instance = HibernateUtil.getSession().find(Composant.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
