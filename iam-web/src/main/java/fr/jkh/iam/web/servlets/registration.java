package fr.jkh.iam.web.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.jkh.iam.User;
import fr.jkh.iam.log.IAMLogger;
import fr.jkh.iam.log.impl.IAMLogManager;
import fr.jkh.iamcore.exception.DAOSaveException;
import fr.jkh.iamcore.service.authentication.AuthenticationService;
import fr.jkh.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Registration
 */

//Test Commit again

@WebServlet(name="Register", urlPatterns="/Register")
public class registration extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(Login.class);

    /**
     * Default constructor. 
     */
    public registration() {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		logger.debug(username);
		logger.debug(password);
		
		User user = new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		try {
////			dao.create(user);
//			response.sendRedirect("login.jsp");
//		} catch (DAOSaveException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}

