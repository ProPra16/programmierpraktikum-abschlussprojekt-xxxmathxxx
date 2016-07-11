/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.xxxmathxxx.tddt.timer.BasicTimer;

/**
 * The Class ActivityTracker.
 *
 * @author Tschebyscheff, 23.06.16
 * rudimentary tracking class, defined for time tracking
 */
public class ActivityTracker{
	
	/** The code stemp collection. */
	public CodeStampCollection codeStampCollection = new CodeStampCollection(); //100% sure that this belongs into a time tracker??????

	/** The timer. */
	private BasicTimer timer;
	
	/**
	 * Instantiates a new time tracker.
	 */
	public ActivityTracker(){
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
	
	/** The keystrokes. */
	private int keystrokes = 0;
	
	/**
	 * Adds a keystroke.
	 */
	public void addKeystroke(){
			keystrokes++;
	}
	
	/**
	 * Gets the keystrokes.
	 *
	 * @return the keystrokes
	 */
	public int getKeystrokes(){
		return keystrokes;
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
