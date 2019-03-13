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
import com.ril.model.Materiaux;

/**
 * Home object for domain model class Materiaux.
 * @see com.ril.daoHibernate.Materiaux
 * @author Hibernate Tools
 */
@Stateless
public class MateriauxHome {

	private static Logger log = Logger.getLogger(MateriauxHome.class.toString());

	public void persist(Materiaux transientInstance) {
		log.debug("persisting Materiaux instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Materiaux persistentInstance) {
		log.debug("removing Materiaux instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Materiaux merge(Materiaux detachedInstance) {
		log.debug("merging Materiaux instance");
		try {
			Materiaux result = (Materiaux) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Materiaux findById(Integer id) {
		log.debug("getting Materiaux instance with id: " + id);
		try {
			Materiaux instance = HibernateUtil.getSession().find(Materiaux.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Materiaux> findAll() {
		log.debug("getting all Metier");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Materiaux> crit = builder.createQuery(Materiaux.class);
			
			Root<Materiaux> TypesRoot = crit.from(Materiaux.class);
			
			crit.select(TypesRoot);
			
			List<Materiaux> types = session.createQuery(crit).getResultList();
			
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
