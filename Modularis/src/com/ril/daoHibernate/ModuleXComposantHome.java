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
import com.ril.model.ModuleXComposant;
import com.ril.model.ModuleXComposantId;
import com.ril.model.ModuleXComposant_;
import com.ril.model.Module;

/**
 * Home object for domain model class ModuleXComposant.
 * @see com.ril.daoHibernate.ModuleXComposant
 * @author Hibernate Tools
 */
@Stateless
public class ModuleXComposantHome {

	private static Logger log = Logger.getLogger(ModuleXComposantHome.class.toString());


	public void persist(ModuleXComposant transientInstance) {
		log.debug("persisting ModuleXComposant instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(ModuleXComposant persistentInstance) {
		log.debug("removing ModuleXComposant instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public ModuleXComposant merge(ModuleXComposant detachedInstance) {
		log.debug("merging ModuleXComposant instance");
		try {
			ModuleXComposant result = (ModuleXComposant) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ModuleXComposant findById(ModuleXComposantId id) {
		log.debug("getting ModuleXComposant instance with id: " + id);
		try {
			ModuleXComposant instance = HibernateUtil.getSession().find(ModuleXComposant.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<ModuleXComposant> findAll() {
		log.debug("getting all ModuleXComposant");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<ModuleXComposant> crit = builder.createQuery(ModuleXComposant.class);
			
			Root<ModuleXComposant> TypesRoot = crit.from(ModuleXComposant.class);
			
			crit.select(TypesRoot);
			
			List<ModuleXComposant> types = session.createQuery(crit).getResultList();
			
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
	
	public List<ModuleXComposant> findAllByModule(Module module) {
		log.debug("getting all ModuleXComposant By moduleId");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<ModuleXComposant> crit = builder.createQuery(ModuleXComposant.class);
			
			Root<ModuleXComposant> TypesRoot = crit.from(ModuleXComposant.class);
			
			crit.select(TypesRoot);
			
			crit.where(builder.equal(TypesRoot.get(ModuleXComposant_.module), module));
			
			List<ModuleXComposant> types = session.createQuery(crit).getResultList();
			
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
