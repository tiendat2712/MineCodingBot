package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static Connection connection = null;

	private DbConnection() {
	}

	public static Connection getConnection() {
		if (connection == null) {

			var props = DbProvider.getDbConfigProps();
			try {
				connection = DriverManager.getConnection(
						props.getProperty("jdbc.url"),
						props.getProperty("jdbc.user"),
						props.getProperty("jdbc.pass")
				);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return connection;
	}	
}
