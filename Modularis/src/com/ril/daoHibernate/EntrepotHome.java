package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Entrepot;

/**
 * Home object for domain model class Entrepot.
 * @see com.ril.daoHibernate.Entrepot
 * @author Hibernate Tools
 */
@Stateless
public class EntrepotHome {

	private static final Log log = LogFactory.getLog(EntrepotHome.class);

	public void persist(Entrepot transientInstance) {
		log.debug("persisting Entrepot instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Entrepot persistentInstance) {
		log.debug("removing Entrepot instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Entrepot merge(Entrepot detachedInstance) {
		log.debug("merging Entrepot instance");
		try {
			Entrepot result = (Entrepot) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Entrepot findById(Integer id) {
		log.debug("getting Entrepot instance with id: " + id);
		try {
			Entrepot instance = HibernateUtil.getSession().find(Entrepot.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
