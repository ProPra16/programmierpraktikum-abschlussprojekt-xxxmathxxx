/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author Tschebyscheff, 23.06.16
 *	main class for our tracking system, includes all different tracker-types
 */
public class Tracker {

	StageRedTracker stageRedTracker;
	StageGreenTracker stageGreenTracker;
	StageRefactorTracker stageRefactorTracker;
	
	public Tracker(){
		stageRedTracker = new StageRedTracker();
		stageGreenTracker = new StageGreenTracker();
		stageRefactorTracker = new StageRefactorTracker();
	}
}
