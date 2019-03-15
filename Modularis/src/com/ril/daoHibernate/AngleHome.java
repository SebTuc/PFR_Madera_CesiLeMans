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
import com.ril.model.Angle;


/**
 * Home object for domain model class Angle.
 * @see com.ril.daoHibernate.Angle
 * @author Hibernate Tools
 */
@Stateless
public class AngleHome {

	private static Logger log = Logger.getLogger(AngleHome.class.toString());

	public void persist(Angle transientInstance) {
		log.debug("persisting Angle instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Angle persistentInstance) {
		log.debug("removing Angle instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Angle merge(Angle detachedInstance) {
		log.debug("merging Angle instance");
		try {
			Angle result = (Angle) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Angle findById(Integer id) {
		log.debug("getting Angle instance with id: " + id);
		try {
			Angle instance = HibernateUtil.getSession().find(Angle.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Angle> findAll() {
		log.debug("getting all Angle");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Angle> crit = builder.createQuery(Angle.class);
			
			Root<Angle> TypesRoot = crit.from(Angle.class);
			
			crit.select(TypesRoot);
			
			List<Angle> types = session.createQuery(crit).getResultList();
			
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
