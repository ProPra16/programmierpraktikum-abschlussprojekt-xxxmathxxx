/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.xxxmathxxx.tddt.timer.BasicTimer;

/**
 * The Class Tracker.
 *
 * @author Tschebyscheff, 23.06.16
 * 	main class for our tracking system, includes all different tracker-types
 */
public class Tracker {

	/** The stage red. */
	public ActivityTracker stageRed;
	
	/** The stage green. */
	public ActivityTracker stageGreen;
	
	/** The stage refactor. */
	public ActivityTracker stageRefactor;
	
	/** A timer that tracks the entire time spend doing this exercise */
	public BasicTimer totalTimer;
	
	/**
	 * Instantiates a new tracker.
	 */
	public Tracker(){
		stageRed = new ActivityTracker();
		stageGreen = new ActivityTracker();
		stageRefactor = new ActivityTracker();
		totalTimer = new BasicTimer();
	}
}
