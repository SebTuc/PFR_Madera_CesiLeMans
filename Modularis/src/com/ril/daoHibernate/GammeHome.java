package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Gamme;

/**
 * Home object for domain model class Gamme.
 * @see com.ril.daoHibernate.Gamme
 * @author Hibernate Tools
 */
@Stateless
public class GammeHome {

	private static Logger log = Logger.getLogger(GammeHome.class.toString());

	public void persist(Gamme transientInstance) {
		log.debug("persisting Gamme instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Gamme persistentInstance) {
		log.debug("removing Gamme instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Gamme merge(Gamme detachedInstance) {
		log.debug("merging Gamme instance");
		try {
			Gamme result = (Gamme) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Gamme findById(Integer id) {
		log.debug("getting Gamme instance with id: " + id);
		try {
			Gamme instance = HibernateUtil.getSession().find(Gamme.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Gamme> findAll() {
		log.debug("getting all Gamme");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Gamme> crit = builder.createQuery(Gamme.class);
			
			Root<Gamme> TypesRoot = crit.from(Gamme.class);
			
			crit.select(TypesRoot);
			
			List<Gamme> types = session.createQuery(crit).getResultList();
			
			if(types.isEmpty()) {
				log.debug("get successful, no instance found");
				return null;
				
			}else {
				log.debug("get successful");
				return types;
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
