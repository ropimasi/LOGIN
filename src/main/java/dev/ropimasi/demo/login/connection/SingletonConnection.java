package dev.ropimasi.demo.login.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;




public class SingletonConnection {

	private static SingletonConnection instance;
	private Connection conn;



	private SingletonConnection() {
		try {
			Properties props = new Properties();
			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream("postgresql.properties");
			props.load(inputStream);

			String url = props.getProperty("db.url");
			String user = props.getProperty("db.user");
			String password = props.getProperty("db.password");

			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}



	public static synchronized SingletonConnection getInstance() {
		if (instance == null) {
			instance = new SingletonConnection();
		}
		return instance;
	}



	public Connection getConnection() {
		return conn;
	}



	public void closeConnection() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
