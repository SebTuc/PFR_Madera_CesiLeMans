package com.ril.daoHibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.EtapeFacture;

public class EtapeFactureHome {
	
private static Logger log = Logger.getLogger(EtapeFactureHome.class.toString());
	
	public void persist(EtapeFacture transientInstance) {
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

	public void remove(EtapeFacture persistentInstance) {
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

	public EtapeFacture merge(EtapeFacture detachedInstance) {
		log.debug("merging Facture instance");
		try {
			EtapeFacture result = (EtapeFacture) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EtapeFacture findById(Integer id) {
		log.debug("getting Facture instance with id: " + id);
		try {
			EtapeFacture instance = HibernateUtil.getSession().find(EtapeFacture.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	
	public List<EtapeFacture> findAll() {
		log.debug("getting all Devis");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<EtapeFacture> crit = builder.createQuery(EtapeFacture.class);
			
			Root<EtapeFacture> EtapeFactureRoot = crit.from(EtapeFacture.class);
			
			crit.select(EtapeFactureRoot);
			
			List<EtapeFacture> list = session.createQuery(crit).getResultList();
			
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
