/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

import org.xxxmathxxx.tddt.tracking.StageRed;

/**
 * The Class AnalyzedStageRed.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedStageRed {

	/** The time. */
	public String time;
	
	/** The keystrokes. */
	int keystrokes;
	
	/** The error. */
	public ErrorCounter error = new ErrorCounter();
	
	/**
	 * Instantiates a new analyzed stage red.
	 *
	 * @param stageRed the stage red
	 */
	public AnalyzedStageRed(StageRed stageRed){
		
		time = stageRed.getTime();
		keystrokes = stageRed.getKeystrokes();
		AnalyzeError.analyzeCodeStamps(stageRed.codeStampCollection, error);
	}
}
