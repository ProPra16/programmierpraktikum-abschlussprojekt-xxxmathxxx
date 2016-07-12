/**
 * 
 */
package org.xxxmathxxx.tddt.timer;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * @author guvon, copied TaskTimerTests
 *
 */
public class BasicTimerTests {

	
		
	@Test public void constructorTest() {
		BasicTimer testTimer = new BasicTimer();
		assertEquals(testTimer.getTime(),0.0,0);
	}
	
	@Test public void clockNotRunningTest() {
		BasicTimer testTimer = new BasicTimer();
		try {
			Thread.sleep(1000);
			assertEquals(testTimer.getTime(),0.0,0);
			} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test public void clockRunningTest() {
		BasicTimer testTimer = new BasicTimer();
		testTimer.setActive(true);
		try {
			Thread.sleep(1000);
			assertNotEquals(testTimer.getTime(),0.0,0);
			}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

	

