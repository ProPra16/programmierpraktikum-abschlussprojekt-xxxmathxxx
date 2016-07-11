
package org.xxxmathxxx.tddt.tracking_analysis;

import org.xxxmathxxx.tddt.tracking.Tracker;

/**
 * The Class AnalyzedTrackingData.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedTrackingData {
	
	/** The analyzed stage green. */
	public AnalyzedStage analyzedStageGreen;
	
	/** The analyzed stage red. */
	public AnalyzedStage analyzedStageRed;
	
	/** The analyzed stage refactor. */
	public AnalyzedStage analyzedStageRefactor; 
	
	
	/**
	 * Instantiates a new analyzed tracking data.
	 *
	 * @param tracker the tracker
	 * @param exercise the exercise
	 * @param profileName the profile name
	 */
	public AnalyzedTrackingData(Tracker tracker){
		
		analyzedStageGreen = new AnalyzedStage(tracker.stageGreen);
		analyzedStageRed = new AnalyzedStage(tracker.stageRed);
		analyzedStageRefactor = new AnalyzedStage(tracker.stageRefactor);
	}
}

