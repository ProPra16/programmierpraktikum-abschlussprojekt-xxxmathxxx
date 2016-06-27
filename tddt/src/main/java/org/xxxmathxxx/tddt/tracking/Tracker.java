/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author Tschebyscheff, 23.06.16
 *	main class for our tracking system, includes all different tracker-types
 */
public class Tracker {

	StageRed stageRed;
	StageGreen stageGreen;
	StageRefactor stageRefactor;
	
	public Tracker(){
		stageRed = new StageRed();
		stageGreen = new StageGreen();
		stageRefactor = new StageRefactor();
	}
}
