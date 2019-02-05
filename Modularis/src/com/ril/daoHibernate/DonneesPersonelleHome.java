package com.ril.daoHibernate;
// Generated 9 janv. 2019 13:13:58 by Hibernate Tools 4.3.5.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ril.hibernate.HibernateUtil;
import com.ril.model.DonneesPersonelle;

/**
 * Home object for domain model class DonneesPersonelle.
 * @see com.ril.daoHibernate.DonneesPersonelle
 * @author Hibernate Tools
 */
@Stateless
public class DonneesPersonelleHome {

	private static final Log log = LogFactory.getLog(DonneesPersonelleHome.class);

	public void persist(DonneesPersonelle transientInstance) {
		log.debug("persisting DonneesPersonelle instance");
		try {
			HibernateUtil.getSession().persist(transientInstance);
			HibernateUtil.push();
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(DonneesPersonelle persistentInstance) {
		log.debug("removing DonneesPersonelle instance");
		try {
			HibernateUtil.getSession().remove(persistentInstance);
			HibernateUtil.push();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public DonneesPersonelle merge(DonneesPersonelle detachedInstance) {
		log.debug("merging DonneesPersonelle instance");
		try {
			DonneesPersonelle result = (DonneesPersonelle) HibernateUtil.getSession().merge(detachedInstance);
			HibernateUtil.push();
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DonneesPersonelle findById(Integer id) {
		log.debug("getting DonneesPersonelle instance with id: " + id);
		try {
			DonneesPersonelle instance = HibernateUtil.getSession().find(DonneesPersonelle.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
