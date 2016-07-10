package org.xxxmathxxx.tdddt.logging;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

public class LoggingTests {
	@Test
	public void genericLoggingTest(){
		TDDTLogManager.getInstance().logMessage("This message was created during a build test");
		TDDTLogManager instance = TDDTLogManager.getInstance();
		assertEquals(true, (instance instanceof TDDTLogManager));
	}
}
