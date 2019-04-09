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
import com.ril.model.Module;
import com.ril.model.Piece;

/**
 * Home object for domain model class Piece.
 * @see com.ril.daoHibernate.Piece
 * @author Hibernate Tools
 */
@Stateless
public class PieceHome {

	private static Logger log = Logger.getLogger(PieceHome.class.toString());

	public void persist(Piece transientInstance) {
		log.debug("persisting Piece instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Piece persistentInstance) {
		log.debug("removing Piece instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Piece merge(Piece detachedInstance) {
		log.debug("merging Piece instance");
		try {
			Piece result = (Piece) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Piece findById(Integer id) {
		log.debug("getting Piece instance with id: " + id);
		try {
			Piece instance = HibernateUtil.getSession().find(Piece.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public void persistJoin(Module firstPersistentJoinedInstance, Piece secondPersistentJoinedInstance) {
		log.debug("removing join beetween Projet and Catalogue instances");
		try {
			
			Session session = HibernateUtil.getSession();
			firstPersistentJoinedInstance.getPieces().add(secondPersistentJoinedInstance);			
			secondPersistentJoinedInstance.getModules().add(firstPersistentJoinedInstance);
			session.merge(secondPersistentJoinedInstance);
			session.merge(firstPersistentJoinedInstance);	
			
			HibernateUtil.push();
			log.debug("join remove successful");
		} catch (RuntimeException re) {
			log.error("join remove failed", re);
			throw re;
		}
	}
	
	public void removeJoin(Module firstPersistentJoinedInstance, Piece secondPersistentJoinedInstance) {
		log.debug("removing join beetween Projet and Catalogue instances");
		try {
			
			Session session = HibernateUtil.getSession();
			firstPersistentJoinedInstance.getPieces().remove(secondPersistentJoinedInstance);			
			secondPersistentJoinedInstance.getModules().remove(firstPersistentJoinedInstance);
			session.merge(secondPersistentJoinedInstance);
			session.merge(firstPersistentJoinedInstance);	
			
			HibernateUtil.push();
			log.debug("join remove successful");
		} catch (RuntimeException re) {
			log.error("join remove failed", re);
			throw re;
		}
	}
	

	
	public List<Piece> findAll() {
		log.debug("getting all Piece");
		try {
			Session session = HibernateUtil.getSession();
	
			CriteriaBuilder builder = HibernateUtil.getCriteriaBuilder();
			
			CriteriaQuery<Piece> crit = builder.createQuery(Piece.class);
			
			Root<Piece> TypesRoot = crit.from(Piece.class);
			
			crit.select(TypesRoot);
			
			List<Piece> types = session.createQuery(crit).getResultList();
			
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
