package dev.ropimasi.demo.login.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class SingletonConnection {

	private static final String dbUser = "postgres";
	private static final String dbPass = "postgres";
	private static final String dbUrl = "jdbc:postgresql://localhost:5432/logindb?autoReconnect=treu";
	private static Connection conn = null;

	static {
		connect();
	}



	private static void connect() {
		try {
			if (conn == null) {
				Class.forName("org.postgresql.Driver");
				conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
				conn.setAutoCommit(false);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}



	public static Connection getConnection() {
		return conn;
	}



	public static void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
