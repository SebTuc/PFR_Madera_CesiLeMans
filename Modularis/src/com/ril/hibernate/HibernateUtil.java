package com.ril.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static CriteriaBuilder builder;
	private static EntityManager entityManager;
	private static Session session;
	
	static {
		try {
			//Création de la session Hibernate et du criteriatBuilder a l'init (InitServlet)
			sessionFactory = new Configuration().configure().buildSessionFactory();
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			entityManager = session.getEntityManagerFactory().createEntityManager();
			builder = sessionFactory.getCriteriaBuilder();			
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void restartSession() {
		session = sessionFactory.openSession();
		session.beginTransaction();
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static EntityManager getEntityManager() {
		return entityManager;
	}
	
	public static Session getSession() {
		return session;
	}

	public static CriteriaBuilder getCriteriaBuilder() {
		return builder;
	}
	
	public static void init() {
		
		System.out.println("Initialization hibernate utils");
	}
	
	public static void push() {
		getSession().getTransaction().commit();
		restartSession();
	}
	
}
