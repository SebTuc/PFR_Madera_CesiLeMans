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
import com.ril.model.Catalogue;
import com.ril.model.Projet;

/**
 * Home object for domain model class Catalogue.
 * @see com.ril.daoHibernate.Catalogue
 * @author Hibernate Tools
 */
@Stateless
public class CatalogueHome {

	private static Logger log = Logger.getLogger(CatalogueHome.class.toString());

	public void persist(Catalogue transientInstance) {
		log.debug("persisting Catalogue instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	public void persistJoin(Catalogue firstPersistentJoinedInstance, Projet secondPersistentJoinedInstance) {
		log.debug("removing join beetween Projet and Catalogue instances");
		try {
			
			Session session = HibernateUtil.getSession();
			firstPersistentJoinedInstance.getProjets().add(secondPersistentJoinedInstance);			
			secondPersistentJoinedInstance.getCatalogue().add(firstPersistentJoinedInstance);
			session.merge(secondPersistentJoinedInstance);
			session.merge(firstPersistentJoinedInstance);	
			
			HibernateUtil.push();
			log.debug("join remove successful");
		} catch (RuntimeException re) {
			log.error("join remove failed", re);
			throw re;
		}
	}

	public void remove(Catalogue persistentInstance) {
		log.debug("removing Catalogue instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}
	
	public void removeJoin(Catalogue firstPersistentJoinedInstance, Projet secondPersistentJoinedInstance) {
		log.debug("removing join beetween Projet and Catalogue instances");
		try {
			
			Session session = HibernateUtil.getSession();
			firstPersistentJoinedInstance.getProjets().remove(secondPersistentJoinedInstance);			
			secondPersistentJoinedInstance.getCatalogue().remove(firstPersistentJoinedInstance);
			session.merge(secondPersistentJoinedInstance);
			session.merge(firstPersistentJoinedInstance);	
			
			HibernateUtil.push();
			log.debug("join remove successful");
		} catch (RuntimeException re) {
			log.error("join remove failed", re);
			throw re;
		}
	}

	public Catalogue merge(Catalogue detachedInstance) {
		log.debug("merging Catalogue instance");
		try {
			Catalogue result = (Catalogue) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Catalogue findById(Integer id) {
		log.debug("getting Catalogue instance with id: " + id);
		try {
			Catalogue instance = HibernateUtil.getSession().find(Catalogue.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Catalogue> findAll() {
		log.debug("getting all Catalogue");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Catalogue> crit = builder.createQuery(Catalogue.class);
			
			Root<Catalogue> TypesRoot = crit.from(Catalogue.class);
			
			crit.select(TypesRoot);
			
			List<Catalogue> types = session.createQuery(crit).getResultList();
			
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
