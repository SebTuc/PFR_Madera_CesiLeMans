package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;

import org.jboss.logging.Logger;

import com.ril.hibernate.HibernateUtil;
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
}
