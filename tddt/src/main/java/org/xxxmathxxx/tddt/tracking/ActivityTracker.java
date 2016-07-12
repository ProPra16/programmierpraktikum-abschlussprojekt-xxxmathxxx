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

	/** This timer tracks how much time is spend in this particular stage */
	public BasicTimer timer;
	
	/** The keystrokes. */
	public int keystrokes = 0;
	
	/**
	 * Instantiates a new time tracker.
	 */
	public ActivityTracker(){
		timer = new BasicTimer();
	}
}
