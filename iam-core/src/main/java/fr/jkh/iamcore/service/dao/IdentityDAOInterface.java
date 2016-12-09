/**
 * 
 */
package fr.jkh.iamcore.service.dao;

import java.util.Collection;
import java.util.List;

import fr.jkh.iam.user.User;
import fr.jkh.iamcore.datamodel.Identity;
import fr.jkh.iamcore.exception.DAOSaveException;
import fr.jkh.iamcore.exception.DAOSearchException;
import fr.jkh.iamcore.exception.DAOUpdateException;

/**
 * @author Junaid KHALID
 *
 */
public interface IdentityDAOInterface {
	
	/**
	 * This method is able to save an identity into a file
	 * 
	 * @param identity
	 * @throws DAOSaveException 
	 */
	public void save(Identity identity) throws DAOSaveException;

	public Collection<Identity> search(Identity criteria)  throws DAOSearchException ;

	public void update(Identity identityToUpdate)  throws  DAOUpdateException;

	public void delete(Identity identityToDelete)  throws DAODeleteException;

	public void create(User user) throws DAOSaveException;
	
	public void deluser(User user) throws DAODeleteException;
	
	public void updateuser(User user) throws DAOUpdateException;
	
	public List userExistsinDB(User user);
	
	public List userLogin(User user);
}
