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
 * 	main class for our tracking system, includes all different trackerManager-types
 */
public class TrackerManager {
	
	/**
	 * Map of Stage and trackers
	 */
	public HashMap<CodeStage,ActivityTracker> atMap;
	

	
	/**
	 * Instantiates a new trackerManager.
	 */
	public TrackerManager(){
		atMap = new HashMap<CodeStage,ActivityTracker>();
		atMap.put(CodeStage.CODE, new ActivityTracker());
		atMap.put(CodeStage.REFACTOR, new ActivityTracker());
		atMap.put(CodeStage.TEST, new ActivityTracker());

	}

	/**
	 * Returns active tracker
	 * @return active Tracker
	 */
	public ActivityTracker getActiveTracker() {
		return atMap.get(TDDTThread.getInstance().getState());
	}
	
	/**
	 * Returns tracker by stage
	 * @param stage
	 * @return Tracker for specified stage
	 */
	public ActivityTracker getTrackerForStage(CodeStage stage){
		return atMap.get(stage);
	}
}
