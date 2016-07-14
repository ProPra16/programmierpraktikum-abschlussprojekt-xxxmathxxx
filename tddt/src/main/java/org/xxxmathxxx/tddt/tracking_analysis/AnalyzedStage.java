package org.xxxmathxxx.tddt.tracking_analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.tracking.ActivityTracker;

/**
 * The Class AnalyzedStageRefactor.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedStage implements java.io.Serializable{

	/**
	 * Saves serialVersion
	 */
	private static final long serialVersionUID = 3L;

	/** The time. */
	public double time;
	
	/** The keystrokes. */
	private int keystrokes;
	
	/**Getter for the keystrokes
	 * @return the keystrokes that occured in this Stage as Integer
	 */
	public int getKeystrokes() {
		return keystrokes;
	}

	/** The error. */
	public ErrorCounter error = new ErrorCounter();
	
	/**
	 * Creates new analyzed Stage
	 * @param tracker
	 */
	public AnalyzedStage(ActivityTracker tracker){
		time = tracker.getElapsedTime();
		keystrokes = tracker.getKeystrokes();
		AnalyzeError.analyzeCodeStamps(tracker.codeStampCollection, error);
	}

	/**
	 * Logs infos
	 */
	public void log() {
		TDDTLogManager.getInstance().logMessage("You used "+keystrokes+" keystrokes!");
		TDDTLogManager.getInstance().logMessage("You spent "+new BigDecimal(time).setScale(2, RoundingMode.HALF_UP)+" seconds in this phase!");
		error.log();
	}
}
