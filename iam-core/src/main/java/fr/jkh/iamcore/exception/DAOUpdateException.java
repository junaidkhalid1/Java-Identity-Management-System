/**
 * 
 */
package fr.jkh.iamcore.exception;

/**
 * @author tbrou
 *
 */
public class DAOUpdateException extends Exception {
	
    private String saveFault;
	
	public DAOUpdateException(String message) {
		this.saveFault = message;
	}

	public String getSaveFault() {
		return saveFault;
	}

}
