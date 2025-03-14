package dev.ropimasi.demo.login.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import dev.ropimasi.demo.login.dao.LoginDao;
import dev.ropimasi.demo.login.utility.MyMD5;
import dev.ropimasi.demo.login.utility.MySHA512;




@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao = new LoginDao();


	public LoginServlet() {
		super();
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*System.out.println("LOG: doGet();");
		String loginFormUsername = request.getParameter("loginFormUsername");
		String loginFormPassword = request.getParameter("loginFormPassword");
		System.out.println("LOG: " + loginFormUsername + " - " + loginFormPassword);*/

	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String loginFormUsername = MyMD5.encrypt(request.getParameter("loginFormUsername"));
		String loginFormPassword = MySHA512.encrypt(request.getParameter("loginFormPassword"));
		
		try {
			if (loginDao.validate(loginFormUsername, loginFormPassword)) {
				// LOGIN SUCCESS
				request.setAttribute("logedUsername", request.getParameter("loginFormUsername"));
				request.setAttribute("encryptedUsername", loginFormUsername);
				request.setAttribute("encryptedPassword", loginFormPassword);
				RequestDispatcher rd = request.getRequestDispatcher("restricted-v1/registration.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("msg", "Combinação usuário-senha incorreta.");
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
