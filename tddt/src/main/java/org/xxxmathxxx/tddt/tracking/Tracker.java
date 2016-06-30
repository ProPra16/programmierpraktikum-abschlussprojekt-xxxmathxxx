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
	public StageRed stageRed;
	
	/** The stage green. */
	public StageGreen stageGreen;
	
	/** The stage refactor. */
	public StageRefactor stageRefactor;
	
	/**
	 * Instantiates a new tracker.
	 */
	public Tracker(){
		stageRed = new StageRed();
		stageGreen = new StageGreen();
		stageRefactor = new StageRefactor();
	}
}
