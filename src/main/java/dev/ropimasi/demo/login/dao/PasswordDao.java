package dev.ropimasi.demo.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dev.ropimasi.demo.login.connection.SingletonConnection;




public class PasswordDao {

	private Connection conn;



	public PasswordDao() {
		super();
		conn = SingletonConnection.getConnection();
	}



	/* True, caso existir a combinação de usuário(encryptedUsername/u_token) e senha(encryptedsPassword/s_token) */
	public boolean validate(String encryptedUsername, String encryptedsPassword) throws SQLException {
		String sqlSelect = "SELECT * FROM senha WHERE u_token = ? AND s_token = ?";
		PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
		psSelect.setString(1, encryptedUsername);
		psSelect.setString(2, encryptedsPassword);
		ResultSet rsSelect = psSelect.executeQuery();
		
		if (rsSelect.next()) {
			return true;
		}

		return false;
	}
	
	
	

}
