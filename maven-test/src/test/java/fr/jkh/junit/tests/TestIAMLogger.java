/**
 * 
 */
package fr.jkh.junit.tests;

import org.junit.Test;

import fr.jkh.iam.log.IAMLogger;

/**
 * @author Junaid KHALID
 *
 */
public class TestIAMLogger {
	
	private static final IAMLogger logger = fr.jkh.iam.log.IAMLogManager.getIAMLogger(TestIAMLogger.class);
	
	
	@Test
	public void testIamLogger(){
		logger.info("test");
	}

}
