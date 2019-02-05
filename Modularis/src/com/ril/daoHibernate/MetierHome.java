package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.Metier;

/**
 * Home object for domain model class Metier.
 * @see com.ril.daoHibernate.Metier
 * @author Hibernate Tools
 */
@Stateless
public class MetierHome {

	private static final Log log = LogFactory.getLog(MetierHome.class);

	public void persist(Metier transientInstance) {
		log.debug("persisting Metier instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Metier persistentInstance) {
		log.debug("removing Metier instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Metier merge(Metier detachedInstance) {
		log.debug("merging Metier instance");
		try {
			Metier result = (Metier) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Metier findById(Integer id) {
		log.debug("getting Metier instance with id: " + id);
		try {
			Metier instance = HibernateUtil.getSession().find(Metier.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Metier> findAll() {
		log.debug("getting all UniteMesure");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Metier> crit = builder.createQuery(Metier.class);
			
			Root<Metier> TypesRoot = crit.from(Metier.class);
			
			crit.select(TypesRoot);
			
			List<Metier> types = session.createQuery(crit).getResultList();
			
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
