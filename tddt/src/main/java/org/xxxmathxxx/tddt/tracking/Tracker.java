/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.xxxmathxxx.tddt.timer.BabystepsTimer;

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
	
	public BabystepsTimer babystepsTimer;
	
	/**
	 * Instantiates a new tracker.
	 */
	public Tracker(){
		stageRed = new ActivityTracker();
		stageGreen = new ActivityTracker();
		stageRefactor = new ActivityTracker();
		babystepsTimer = new BabystepsTimer();
	}
}
