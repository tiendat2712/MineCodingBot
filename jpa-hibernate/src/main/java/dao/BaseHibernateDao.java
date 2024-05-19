package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import connection.DbConnection;

/**
 * Base class of hibernate
 * 
 * Goal: contains common method related to hibernate/jpa
 * Child: extends and use these method
 */
public class BaseHibernateDao {

	private SessionFactory sessionFactory;
	
	BaseHibernateDao() {
		sessionFactory = DbConnection.getSessionFactory();
	}
	
	Session openSession() {
		return sessionFactory.openSession();
	}
	
	Session getCurrSession() {
		return sessionFactory.getCurrentSession();
	}
	
}
