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
import com.ril.model.Utilisateur;

/**
 * Home object for domain model class Utilisateur.
 * @see com.ril.daoHibernate.Utilisateur
 * @author Hibernate Tools
 */
@Stateless
public class UtilisateurHome {

	private static Logger log = Logger.getLogger(UtilisateurHome.class.toString());

	public void persist(Utilisateur transientInstance) {
		log.debug("persisting Utilisateur instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Utilisateur persistentInstance) {
		log.debug("removing Utilisateur instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Utilisateur merge(Utilisateur detachedInstance) {
		log.debug("merging Utilisateur instance");
		try {
			Utilisateur result = (Utilisateur) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Utilisateur findById(Integer id) {
		log.debug("getting Utilisateur instance with id: " + id);
		try {
			Utilisateur instance = HibernateUtil.getSession().find(Utilisateur.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<Utilisateur> findAll() {
		log.debug("getting all Metier");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Utilisateur> crit = builder.createQuery(Utilisateur.class);
			
			Root<Utilisateur> TypesRoot = crit.from(Utilisateur.class);
			
			crit.select(TypesRoot);
			
			List<Utilisateur> types = session.createQuery(crit).getResultList();
			
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
