package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import utils.Configs;

/**
 * The class
 * 
 * @author admin
 *
 */
public class DBConnection {

	private static DBConnection DBConnection;
	private static Connection connection;

	private DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Configs.databaseName,
					Configs.databaseUserName, Configs.databasePassword);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getDBConnection() {
		if (DBConnection == null) {
			DBConnection = new DBConnection();
		}

		return connection;
	}
}
