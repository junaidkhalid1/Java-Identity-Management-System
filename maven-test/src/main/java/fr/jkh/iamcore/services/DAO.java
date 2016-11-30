/**
 * 
 */
package fr.jkh.iamcore.services;

import fr.jkh.iam.datamodel.Identity;

/**
 * @author Junaid KHALID
 *
 */
public interface DAO {

	/**
	 * @param identity
	 */
	void save(Identity identity);

	/**
	 * @param identity
	 */
	void update(Identity identity);

	/**
	 * @param identity
	 */
	void delete(Identity identity);
	
	

}
