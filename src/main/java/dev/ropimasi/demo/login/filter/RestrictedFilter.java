package dev.ropimasi.demo.login.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import dev.ropimasi.demo.login.connection.SingletonConnection;
import dev.ropimasi.demo.login.dao.LoginDao;
import dev.ropimasi.demo.login.model.dto.AuthenticationDto;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




/**
 * Servlet Filter implementation class AutenticacaoFilter
 */
@WebFilter(urlPatterns = { "/restricted-v1/*" })
public class RestrictedFilter extends HttpFilter implements Filter {

	private static Connection conn;
	private LoginDao loginDao = new LoginDao();



	public RestrictedFilter() {
		super();
	}



	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			// HttpServletRequest para pegar o ServletPath.
			HttpServletRequest httpServletRequest = (HttpServletRequest) request;
			HttpSession session = httpServletRequest.getSession();

			AuthenticationDto authDto = (AuthenticationDto)session.getAttribute("authDto");

			if (authDto != null
					&& authDto.lToken() == loginDao.getLastestValidLoginTokenFor(authDto.uToken())) { // Tem Login Token válido. 
				
				chain.doFilter(request, response);
				
			} else { // Não está logado:
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe usuário e senha.");
				dispatcher.forward(request, response);
				return; // Parar a execução após redirecionar.
			}

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			request.setAttribute("msg", "Filter Error: " + e.getMessage());
			dispatcher.forward(request, response);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}



	public void init(FilterConfig fConfig) throws ServletException {
		conn = SingletonConnection.getConnection();
	}



	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
