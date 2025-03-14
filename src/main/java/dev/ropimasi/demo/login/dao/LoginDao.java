package dev.ropimasi.demo.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dev.ropimasi.demo.login.connection.SingletonConnection;




public class LoginDao {

	private Connection conn;



	public LoginDao() {
		super();
		conn = SingletonConnection.getConnection();
	}



	public boolean validate(String encryptedUsername, String encryptedsPassword) throws SQLException {

		String sql = "SELECT * FROM senha WHERE u_token = ? AND s_token = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, encryptedUsername);
		ps.setString(2, encryptedsPassword);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			// registrar login, gerar l_token.
			return true;
		}

		return false;
	}

}
