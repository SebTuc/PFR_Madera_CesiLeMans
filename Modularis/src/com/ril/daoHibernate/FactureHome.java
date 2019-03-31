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
import com.ril.model.Devis;
import com.ril.model.Facture;

/**
 * Home object for domain model class Facture.
 * @see com.ril.daoHibernate.Facture
 * @author Hibernate Tools
 */
@Stateless
public class FactureHome {

	private static Logger log = Logger.getLogger(FactureHome.class.toString());
	
	public void persist(Facture transientInstance) {
		log.debug("persisting Facture instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Facture persistentInstance) {
		log.debug("removing Facture instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Facture merge(Facture detachedInstance) {
		log.debug("merging Facture instance");
		try {
			Facture result = (Facture) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Facture findById(Integer id) {
		log.debug("getting Facture instance with id: " + id);
		try {
			Facture instance = HibernateUtil.getSession().find(Facture.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<Facture> findAll() {
		log.debug("getting all Devis");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Facture> crit = builder.createQuery(Facture.class);
			
			Root<Facture> FactureRoot = crit.from(Facture.class);
			
			crit.select(FactureRoot);
			
			List<Facture> list = session.createQuery(crit).getResultList();
			
			if(list.isEmpty()) {
				log.debug("get successful, no instance found");
				return null;
				
			}else {
				log.debug("get successful");
				return list;
			}
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
