package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.UniteMesure;

/**
 * Home object for domain model class UniteMesure.
 * @see com.ril.daoHibernate.UniteMesure
 * @author Hibernate Tools
 */
@Stateless
public class UniteMesureHome {

	private static final Log log = LogFactory.getLog(UniteMesureHome.class);

	public void persist(UniteMesure transientInstance) {
		log.debug("persisting UniteMesure instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(UniteMesure persistentInstance) {
		log.debug("removing UniteMesure instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public UniteMesure merge(UniteMesure detachedInstance) {
		log.debug("merging UniteMesure instance");
		try {
			UniteMesure result = (UniteMesure) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public UniteMesure findById(Integer id) {
		log.debug("getting UniteMesure instance with id: " + id);
		try {
			UniteMesure instance = HibernateUtil.getSession().find(UniteMesure.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
