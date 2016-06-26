/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author Tschebyscheff, 23.06.16
 *	main class for our tracking system, includes all different tracker-types
 */
public class Tracker {

	StageRed stageRedTracker;
	StageGreen stageGreenTracker;
	StageRefactor stageRefactorTracker;
	
	public Tracker(){
		stageRedTracker = new StageRed();
		stageGreenTracker = new StageGreen();
		stageRefactorTracker = new StageRefactor();
	}
}
