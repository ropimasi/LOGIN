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


	public String insertLoginFor(String uToken) throws SQLException {
		String sqlInsert = "INSERT INTO login (u_token) VALUES (?);";
		PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
		psInsert.setString(1, uToken);
		psInsert.execute();
		conn.commit();
		return getLastestValidLoginTokenFor(uToken);
		
	}
	
	

	/* Retorna o mais recente login-token com data-hora válida para o usuário do uToken(encryptedUsername). */
	public String getLastestValidLoginTokenFor(String uToken) throws SQLException {
		String sqlSelect = "SELECT * FROM login WHERE u_token = ? AND login_expire_moment > now() ORDER BY login_expire_moment DESC LIMIT 1;";
		PreparedStatement psSelect = conn.prepareStatement(sqlSelect);
		psSelect.setString(1, uToken);
		ResultSet rsSelect = psSelect.executeQuery();
		if (rsSelect.next()) {
			return rsSelect.getString("l_token");
		}

		return null;
	}
	
	
	

}
