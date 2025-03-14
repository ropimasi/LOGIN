package dev.ropimasi.demo.login.service;

import java.sql.SQLException;
import dev.ropimasi.demo.login.dao.LoginDao;
import dev.ropimasi.demo.login.dao.PasswordDao;
import dev.ropimasi.demo.login.model.dto.AuthenticationDto;




public class AuthenticationService {

	private PasswordDao passDao = new PasswordDao();
	private LoginDao loginDao = new LoginDao();



	public AuthenticationService() {
		super();
	}



	public AuthenticationDto authentic(AuthenticationDto authDto) {
		try {
			if (passDao.validate(authDto.uToken(), authDto.sToken())) {
				// USER AND PASS EXISTS: PASSED.
				AuthenticationDto rtnAuthDto = new AuthenticationDto(
						authDto.uToken(),
						authDto.sToken(),
						loginDao.insertLoginFor(authDto.uToken()) // loginDao.getLastestValidLoginTokenFor(authDto.uToken())
						
						);
				return rtnAuthDto;
			}
		} catch (SQLException e) {
			System.out.println("Exception no [if (passDao.validate(authDto.uToken(), authDto.sToken()))]");
			e.printStackTrace();
		}
		return authDto;
	}
}
