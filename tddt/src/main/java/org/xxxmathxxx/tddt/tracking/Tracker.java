/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * The Class Tracker.
 *
 * @author Tschebyscheff, 23.06.16
 * 	main class for our tracking system, includes all different tracker-types
 */
public class Tracker {

	/** The stage red. */
	public TimeTracker stageRed;
	
	/** The stage green. */
	public TimeTracker stageGreen;
	
	/** The stage refactor. */
	public TimeTracker stageRefactor;
	
	/**
	 * Instantiates a new tracker.
	 */
	public Tracker(){
		stageRed = new TimeTracker();
		stageGreen = new TimeTracker();
		stageRefactor = new TimeTracker();
	}
}
