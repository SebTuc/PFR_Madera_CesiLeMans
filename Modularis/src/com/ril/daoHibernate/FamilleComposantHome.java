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
import com.ril.model.FamilleComposant;

/**
 * Home object for domain model class FamilleComposant.
 * @see com.ril.daoHibernate.FamilleComposant
 * @author Hibernate Tools
 */
@Stateless
public class FamilleComposantHome {

	private static Logger log = Logger.getLogger(FamilleComposantHome.class.toString());

	public void persist(FamilleComposant transientInstance) {
		log.debug("persisting FamilleComposant instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(FamilleComposant persistentInstance) {
		log.debug("removing FamilleComposant instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public FamilleComposant merge(FamilleComposant detachedInstance) {
		log.debug("merging FamilleComposant instance");
		try {
			FamilleComposant result = (FamilleComposant) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public FamilleComposant findById(Integer id) {
		log.debug("getting FamilleComposant instance with id: " + id);
		try {
			FamilleComposant instance = HibernateUtil.getSession().find(FamilleComposant.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<FamilleComposant> findAll() {
		log.debug("getting all UniteMesure");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<FamilleComposant> crit = builder.createQuery(FamilleComposant.class);
			
			Root<FamilleComposant> TypesRoot = crit.from(FamilleComposant.class);
			
			crit.select(TypesRoot);
			
			List<FamilleComposant> types = session.createQuery(crit).getResultList();
			
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
