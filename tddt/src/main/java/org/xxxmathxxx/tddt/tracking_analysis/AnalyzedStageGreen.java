/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

import org.xxxmathxxx.tddt.tracking.StageGreen;

/**
 * The Class AnalyzedStageGreen.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedStageGreen {

	/** The time. */
	public String time;
	
	/** The keystrokes. */
	public int keystrokes;
	
	/** The error. */
	public ErrorCounter error = new ErrorCounter();
	
	/**
	 * Instantiates a new analyzed stage green.
	 *
	 * @param stageGreen the stage green
	 */
	public AnalyzedStageGreen(StageGreen stageGreen){
		
		time = stageGreen.getTime();
		keystrokes = stageGreen.getKeystrokes();
		AnalyzeError.analyzeCodeStamps(stageGreen.codeStampCollection, error);
		
	}	
}

