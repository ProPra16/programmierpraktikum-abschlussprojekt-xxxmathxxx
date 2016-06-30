/**
 * 
 */
package tracking_analysis;

import org.xxxmathxxx.tddt.tracking.StageRefactor;

/**
 * The Class AnalyzedStageRefactor.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedStageRefactor {

	/** The time. */
	String time;
	
	/** The keystrokes. */
	int keystrokes;
	
	/** The error. */
	Error error = new Error();
	
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
