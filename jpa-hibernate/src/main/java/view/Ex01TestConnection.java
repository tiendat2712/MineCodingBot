package view;

import org.hibernate.Session;

import connection.DbConnection;

public class Ex01TestConnection {

	public static void main(String[] args) {
		// factory is used to produce sessions
		
		// session: its thread that helps us to access/query to database
		
		final var sessionFactory = DbConnection.getSessionFactory();
		
		System.out.println("sessionFactory --> " + sessionFactory + "\n");
		
		// there are 2 type of sessions
		
		// openSession: luôn tạo session mới
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		
		System.out.println("session1 --> "+ session1);
		System.out.println("session2 --> "+ session2 + "\n");
		
		// currentSession: singleton --> tạo ra duy nhất một session từ sessionFactory <prefer>
		Session session3 = sessionFactory.getCurrentSession();
		Session session4 = sessionFactory.getCurrentSession();
		
		System.out.println("session3 --> "+ session3);
		System.out.println("session4 --> "+ session4 + "\n");
		
		
		
	}
	
}
