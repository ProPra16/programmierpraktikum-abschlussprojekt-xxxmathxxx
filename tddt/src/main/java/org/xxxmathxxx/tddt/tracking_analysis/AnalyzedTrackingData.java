
package org.xxxmathxxx.tddt.tracking_analysis;

import java.util.HashMap;

import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.tracking.TrackerManager;

/**
 * @author xxxMathxxx 2016
 */
public class AnalyzedTrackingData implements java.io.Serializable{
	
	/**
	 * versionSerial
	 */
	private static final long serialVersionUID = 2L;
	
	/**
	 * Map
	 */
	public HashMap<CodeStage,AnalyzedStage> anMap;

	/**
	 * Creates new AnalyzedTrackingData
	 * @param tm
	 */
	public AnalyzedTrackingData(TrackerManager tm){
		anMap = new HashMap<CodeStage,AnalyzedStage>();
		for (CodeStage stage: tm.atMap.keySet()){
			anMap.put(stage, new AnalyzedStage(tm.atMap.get(stage)));
		}
	}

	/**
	 * Logs infos to file
	 */
	public void log() {
		TDDTLogManager.getInstance().logMessage("Your stats for this exercise:");
		for (CodeStage stage: anMap.keySet()){
			TDDTLogManager.getInstance().logMessage("Stage "+stage.toString());
			anMap.get(stage).log();
		}
	}
}

