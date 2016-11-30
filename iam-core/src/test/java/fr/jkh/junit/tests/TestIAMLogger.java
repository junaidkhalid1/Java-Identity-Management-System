/**
 * 
 */
package fr.jkh.junit.tests;

import org.junit.Test;

import fr.jkh.iam.log.IAMLogger;
import fr.jkh.iam.log.impl.IAMLogManager;

/**
 * @author Junaid KHALID
 *
 */
public class TestIAMLogger {
	
	private static final IAMLogger logger = IAMLogManager.getIAMLogger(TestIAMLogger.class);
	
	
	@Test
	public void testIamLogger(){
		logger.info("test");
	}

}
