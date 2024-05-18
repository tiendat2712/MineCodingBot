package view;

import connection.DbConnection;

public class Ex01TestConnection {

	public static void main(String[] args) {
		
		final var sessionFactory = DbConnection.getSessionFactory();
		
		System.out.println("sessionFactory --> " + sessionFactory);
		
	}
	
}
