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
import com.ril.model.Metier;
import com.ril.model.UniteMesure;

/**
 * Home object for domain model class UniteMesure.
 * @see com.ril.daoHibernate.UniteMesure
 * @author Hibernate Tools
 */
@Stateless
public class UniteMesureHome {

	private static Logger log = Logger.getLogger(UniteMesureHome.class.toString());

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
	
	public List<UniteMesure> findAll() {
		log.debug("getting all UniteMesure");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<UniteMesure> crit = builder.createQuery(UniteMesure.class);
			
			Root<UniteMesure> TypesRoot = crit.from(UniteMesure.class);
			
			crit.select(TypesRoot);
			
			List<UniteMesure> types = session.createQuery(crit).getResultList();
			
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
