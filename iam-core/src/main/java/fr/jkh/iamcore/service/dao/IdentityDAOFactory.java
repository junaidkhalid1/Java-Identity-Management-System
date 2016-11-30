/**
 * 
 */
package fr.jkh.iamcore.service.dao;

import fr.jkh.iamcore.exception.DAOInitializationException;

/**
 * @author Junaid KHALID
 *
 */
public class IdentityDAOFactory {
	
	
	public static IdentityDAOInterface getIdentityDAO() throws DAOInitializationException{
		return IdentityJDBCDAO.getInstance();
	}

}
