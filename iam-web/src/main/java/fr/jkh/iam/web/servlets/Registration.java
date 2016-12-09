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
import fr.jkh.iamcore.exception.DAOSaveException;
import fr.jkh.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Registration
 */

@WebServlet(name="Registration", urlPatterns="/Register")
public class Registration extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(Registration.class);

    /**
     * Default constructor. 
     */
    public Registration() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * Checking if parameters are not null or empty
	 * Checking if user already registered in DB.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		
		String usernames = request.getParameter("username");
		String passwords = request.getParameter("password");
		
		logger.debug("value:" +usernames);
		logger.debug("value:" +passwords);
		
		if ( usernames != "" && passwords != "") {
		
		user.setusername(usernames);
		user.setpassword(passwords);
		
		List l = dao.userExistsinDB(user);
		
		if (l.toString().equals("[true]"))
		{
		response.sendRedirect("reconnect.jsp");}
		else {try {
			dao.create(user);
		} catch (DAOSaveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}response.sendRedirect("login.jsp");}
	} else {response.sendRedirect("reconnect.jsp");}		
	
	}

}

