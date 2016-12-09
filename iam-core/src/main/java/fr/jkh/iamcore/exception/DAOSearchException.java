/**
 * 
 */
package fr.jkh.iamcore.exception;

/**
 * @author tbrou
 *
 */
public class DAOSearchException extends Exception {

	private String saveFault;
	
	public DAOSearchException(String message) {
		this.saveFault = message;
	}

	public String getSaveFault() {
		return saveFault;
	}

}
