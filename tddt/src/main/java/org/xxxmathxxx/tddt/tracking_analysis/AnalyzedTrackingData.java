
package org.xxxmathxxx.tddt.tracking_analysis;

import java.util.HashMap;

import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.tracking.TrackerManager;

/**
 * @author xxxMathxxx 2016
 */
public class AnalyzedTrackingData {
	
	public HashMap<CodeStage,AnalyzedStage> anMap;

	public AnalyzedTrackingData(TrackerManager tm){
		anMap = new HashMap<CodeStage,AnalyzedStage>();
		for (CodeStage stage: tm.atMap.keySet()){
			anMap.put(stage, new AnalyzedStage(tm.atMap.get(stage)));
		}
	}
}

