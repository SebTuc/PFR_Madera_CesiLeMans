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
import com.ril.model.Projet;

/**
 * Home object for domain model class Projet.
 * @see com.ril.daoHibernate.Projet
 * @author Hibernate Tools
 */
@Stateless
public class ProjetHome {

	private static Logger log = Logger.getLogger(ProjetHome.class.toString());

	public void persist(Projet transientInstance) {
		log.debug("persisting Projet instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Projet persistentInstance) {
		log.debug("removing Projet instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Projet merge(Projet detachedInstance) {
		log.debug("merging Projet instance");
		try {
			Projet result = (Projet) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Projet findById(Integer id) {
		log.debug("getting Projet instance with id: " + id);
		try {
			Projet instance = HibernateUtil.getSession().find(Projet.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Projet> findAll() {
		log.debug("getting all Metier");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Projet> crit = builder.createQuery(Projet.class);
			
			Root<Projet> TypesRoot = crit.from(Projet.class);
			
			crit.select(TypesRoot);
			
			List<Projet> types = session.createQuery(crit).getResultList();
			
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
