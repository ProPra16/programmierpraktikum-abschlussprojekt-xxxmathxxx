/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.HashMap;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.timer.BabystepsTimer;
import org.xxxmathxxx.tddt.timer.BasicTimer;

/**
 * The Class TrackerManager.
 *
 * @author Tschebyscheff, 23.06.16
 * 	main class for our tracking system, includes all different tracker-types
 */
public class TrackerManager {
	
	public HashMap<CodeStage,ActivityTracker> atMap;
	
	public BabystepsTimer babystepsTimer;
	
	public BasicTimer totalTimer;
	
	/**
	 * Instantiates a new tracker.
	 */
	public TrackerManager(){
		atMap = new HashMap<CodeStage,ActivityTracker>();
		atMap.put(CodeStage.CODE, new ActivityTracker());
		atMap.put(CodeStage.REFACTOR, new ActivityTracker());
		atMap.put(CodeStage.TEST, new ActivityTracker());

		babystepsTimer = new BabystepsTimer();
		totalTimer = new BasicTimer();
	}

	public ActivityTracker getActiveTracker() {
		return atMap.get(TDDTThread.getInstance().getState());
	}
}
