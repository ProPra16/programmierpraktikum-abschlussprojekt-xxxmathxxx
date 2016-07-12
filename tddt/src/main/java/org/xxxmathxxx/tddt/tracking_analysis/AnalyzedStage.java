package org.xxxmathxxx.tddt.tracking_analysis;

import org.xxxmathxxx.tddt.tracking.ActivityTracker;

/**
 * The Class AnalyzedStageRefactor.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedStage {

	/** The time. */
	public double time;
	
	/** The keystrokes. */
	public int keystrokes;
	
	/** The error. */
	public ErrorCounter error = new ErrorCounter();
	
	public AnalyzedStage(ActivityTracker tracker){
		time = tracker.timer.getTime();
		keystrokes = tracker.keystrokes;
		AnalyzeError.analyzeCodeStamps(tracker.codeStampCollection, error);
	}
}
