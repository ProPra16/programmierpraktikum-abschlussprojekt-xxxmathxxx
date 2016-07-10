/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

import org.xxxmathxxx.tddt.tracking.StageRefactor;

/**
 * The Class AnalyzedStageRefactor.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedStageRefactor {

	/** The time. */
	public String time;
	
	/** The keystrokes. */
	public int keystrokes;
	
	/** The error. */
	public ErrorCounter error = new ErrorCounter();
	
	/**
	 * Instantiates a new analyzed stage refactor.
	 *
	 * @param stageRefactor the stage refactor
	 */
	public AnalyzedStageRefactor(StageRefactor stageRefactor){
		time = stageRefactor.getTime();
		keystrokes = stageRefactor.getKeystrokes();
		AnalyzeError.analyzeCodeStamps(stageRefactor.codeStampCollection, error);
	}
}
