/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.HashMap;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.CodeStage;

/**
 * The Class TrackerManager.
 *
 * @author Tschebyscheff, 23.06.16
 * 	main class for our tracking system, includes all different tm-types
 */
public class TrackerManager {
	
	public HashMap<CodeStage,ActivityTracker> atMap;
	

	
	/**
	 * Instantiates a new tm.
	 */
	public TrackerManager(){
		atMap = new HashMap<CodeStage,ActivityTracker>();
		atMap.put(CodeStage.CODE, new ActivityTracker());
		atMap.put(CodeStage.REFACTOR, new ActivityTracker());
		atMap.put(CodeStage.TEST, new ActivityTracker());

	}

	public ActivityTracker getActiveTracker() {
		return atMap.get(TDDTThread.getInstance().getState());
	}
	
	public ActivityTracker getTrackerForStage(CodeStage stage){
		return atMap.get(stage);
	}
}
