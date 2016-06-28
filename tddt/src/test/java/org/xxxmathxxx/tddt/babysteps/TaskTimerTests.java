package org.xxxmathxxx.tddt.babysteps;

import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTimerTests {
	
    @Test public void constructorTest() {
        TaskTimer testTimer = new TaskTimer();
        assertEquals(testTimer.getTimeInSecondsAsString(),"0");
    }
    
    @Test public void clockNotRunningTest() {
        TaskTimer testTimer = new TaskTimer();
        try {
			Thread.sleep(1000);
			assertEquals(testTimer.getTimeInSecondsAsString(), "0");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Test public void clockRunningTest() {
        TaskTimer testTimer = new TaskTimer();
        testTimer.toggleActive();
        try {
			Thread.sleep(1000);
			assertNotEquals(testTimer.getTimeInSecondsAsString(), "0");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
