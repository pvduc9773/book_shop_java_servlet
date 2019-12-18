package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDatabase {
	
	public Connection connection;
	
	public void ConnectionDB() throws Exception {
		// B1: Xác định HQTCSDL
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// B2: Kết nối vào CSDL
		String url = "jdbc:sqlserver://DESKTOP-UJ3G9B1:1433;databaseName=BookStore;user=sa; password=123";
		connection = DriverManager.getConnection(url);
		System.out.println("Connect DB Success!!");
	}
}
