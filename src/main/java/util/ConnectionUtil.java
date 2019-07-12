package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private Connection connection;
//	private String url, user, password;
	
	private static ConnectionUtil connectionInstance;
	
	private ConnectionUtil(String url, String user, String password) {
//		this.url = url;
//		this.user = user;
//		this.password = password;
		try {
			this.connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Error: SQL Connection Failed");
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getInstance() {
		if(connectionInstance == null) {
			connectionInstance = new ConnectionUtil("jdbc:postgresql://localhost:5432/postgres", "postgres","");	//TODO read from file
			
			return connectionInstance;
		}
		return connectionInstance;
	}
	
	public synchronized Connection getConnection() {
		return connection;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Failed to close connection");
			e.printStackTrace();
		}
	}
	
	
}
