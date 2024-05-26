package connection;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DbConnection {
	
	private static SessionFactory sessionFactory = null;
	
	private static String DATASOURCE_PATH = "hibernate.cfg.xml";
	
	private DbConnection() {		
		
	}
	
	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			sessionFactory = configuration.configure(DATASOURCE_PATH).buildSessionFactory();
		}
		return sessionFactory;
	}
	
	
	
}