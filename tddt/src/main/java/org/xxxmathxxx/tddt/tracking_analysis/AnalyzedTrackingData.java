
package org.xxxmathxxx.tddt.tracking_analysis;

import org.xxxmathxxx.tddt.tracking.Tracker;

/**
 * The Class AnalyzedTrackingData.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedTrackingData {
	
	/** The analyzed stage green. */
	public AnalyzedStageGreen analyzedStageGreen;
	
	/** The analyzed stage red. */
	public AnalyzedStageRed analyzedStageRed;
	
	/** The analyzed stage refactor. */
	public AnalyzedStageRefactor analyzedStageRefactor; 
	
	
	/**
	 * Instantiates a new analyzed tracking data.
	 *
	 * @param tracker the tracker
	 * @param exercise the exercise
	 * @param profileName the profile name
	 */
	public AnalyzedTrackingData(Tracker tracker){
		
		analyzedStageGreen = new AnalyzedStageGreen(tracker.stageGreen);
		analyzedStageRed = new AnalyzedStageRed(tracker.stageRed);
		analyzedStageRefactor = new AnalyzedStageRefactor(tracker.stageRefactor);
	}
}

