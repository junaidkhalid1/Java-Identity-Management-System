package fr.jkh.iam.web.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.jkh.iam.log.IAMLogger;
import fr.jkh.iam.log.impl.IAMLogManager;
import fr.jkh.iamcore.datamodel.Identity;
import fr.jkh.iamcore.exception.DAOSaveException;
import fr.jkh.iamcore.service.dao.IdentityDAOInterface;

/**
 * Servlet implementation class Login
 */

@WebServlet(name = "IdentityServlet", urlPatterns = "/IdAction")
public class IdentityServlet extends GenericSpringServlet {
	private static final long serialVersionUID = 1L;

	
	@Inject
	IdentityDAOInterface dao;
	
	IAMLogger logger = IAMLogManager.getIAMLogger(IdentityServlet.class);

	/**
	 * Default constructor.
	 */
	public IdentityServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		Identity identity = new Identity();
		
		String displayName = request.getParameter("displayName");
		String email = request.getParameter("email");
		String uid = request.getParameter("uid");
		String birthDate = request.getParameter("birthDate");
		
		String str_date=birthDate;
		DateFormat formatter ; 
		Date date ; 
		formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		
		try {
			date = formatter.parse(str_date);
			
			identity.setDisplayName(displayName);
			identity.setEmail(email);
			identity.setUid(uid);
			identity.setBirthDate(date);
			dao.save(identity);
			response.sendRedirect("success.jsp");
		} catch (DAOSaveException | ParseException e) {
			// TODO Redirect to error page
			e.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());

	}

}
