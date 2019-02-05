package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Plan;

/**
 * Home object for domain model class Plan.
 * @see com.ril.daoHibernate.Plan
 * @author Hibernate Tools
 */
@Stateless
public class PlanHome {

	private static final Log log = LogFactory.getLog(PlanHome.class);

	public void persist(Plan transientInstance) {
		log.debug("persisting Plan instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Plan persistentInstance) {
		log.debug("removing Plan instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Plan merge(Plan detachedInstance) {
		log.debug("merging Plan instance");
		try {
			Plan result = (Plan) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Plan findById(Integer id) {
		log.debug("getting Plan instance with id: " + id);
		try {
			Plan instance = HibernateUtil.getSession().find(Plan.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
