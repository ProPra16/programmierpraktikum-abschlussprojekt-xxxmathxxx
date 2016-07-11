/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.xxxmathxxx.tddt.timer.BasicTimer;

/**
 * The Class TimeTracker.
 *
 * @author Tschebyscheff, 23.06.16
 * rudimentary tracking class, defined for time tracking
 */
public class TimeTracker extends ActivityTracker {
	
	/** The code stemp collection. */
	public CodeStampCollection codeStampCollection = new CodeStampCollection(); //100% sure that this belongs into a time tracker??????

	/** The timer. */
	private BasicTimer timer;
	
	/**
	 * Instantiates a new time tracker.
	 */
	public TimeTracker(){
		timer = new BasicTimer();
	}
	
	/**
	 * Starts timeTracking.
	 */
	public void  startTimeTracking(){
		timer.toggleActive();
	}
	
	/**
	 * Stop tracking.
	 */
	public void stopTimeTracking(){
		timer.toggleActive();
	}
	
	/**
	 * Gets the time that elapsed since starting the timer
	 *
	 * @return the time
	 */
	public String getTime(){
		return timer.getTimeInSecondsAsString();
	}
}
