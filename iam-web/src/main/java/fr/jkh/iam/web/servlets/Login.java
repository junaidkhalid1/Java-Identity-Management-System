package fr.jkh.iam.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.jkh.iam.log.IAMLogger;
import fr.jkh.iam.log.impl.IAMLogManager;
import fr.jkh.iam.user.User;
import fr.jkh.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Login
 */

@WebServlet(name="Login", urlPatterns="/Login")
public class Login extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(Login.class);

    /**
     * Default constructor. 
     */
    public Login() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = new User();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		user.setusername(username);
		user.setpassword(password);
		
		logger.debug(username);
		logger.debug(password);
		
		List l = dao.userLogin(user);
		
		if (l.toString().equals("[true]"))
		{
		response.sendRedirect("welcome.jsp");}
		else {response.sendRedirect("reconnect.jsp");}
	}

}
