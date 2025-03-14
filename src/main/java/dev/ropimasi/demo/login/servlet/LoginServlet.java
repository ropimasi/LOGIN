package dev.ropimasi.demo.login.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import dev.ropimasi.demo.login.dao.PasswordDao;
import dev.ropimasi.demo.login.model.dto.AuthenticationDto;
import dev.ropimasi.demo.login.service.AuthenticationService;
import dev.ropimasi.demo.login.utility.MyMD5;
import dev.ropimasi.demo.login.utility.MySHA512;




@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PasswordDao loginDao = new PasswordDao();
	private AuthenticationService authService = new AuthenticationService();

	/* FIXME: corrigir a Servlet associado com a RestrictedFilter.
	 * Caminho feliz está correto.
	 * Caminho errado está defeito. Onde deveria ser redirecionado para tela inicial de login
	 * não está assim fazendo. */

	public LoginServlet() {
		super();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("LOG: doGet();");
		String loginFormUsername = request.getParameter("loginFormUsername");
		String loginFormPassword = request.getParameter("loginFormPassword");
		System.out.println("LOG: " + loginFormUsername + " - " + loginFormPassword);

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String encryptedUsername = MyMD5.encrypt(request.getParameter("loginFormUsername"));
		String encryptedPassword = MySHA512.encrypt(request.getParameter("loginFormPassword"));
		
		AuthenticationDto authDto = new AuthenticationDto(encryptedUsername, encryptedPassword, "");
		authDto = authService.authentic(authDto);
		
			if (authDto.lToken() != null && !authDto.lToken().isBlank() && !authDto.lToken().isEmpty()) {
				// AUTENTICAÇÃO COM SUCESSO.
				session.setAttribute("authDto", authDto);
				request.setAttribute("logedUsername", request.getParameter("loginFormUsername"));
				RequestDispatcher rd = request.getRequestDispatcher("restricted-v1/registration.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Combinação usuário-senha incorreta.");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
	}

}
