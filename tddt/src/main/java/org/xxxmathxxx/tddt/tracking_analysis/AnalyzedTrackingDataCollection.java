package org.xxxmathxxx.tddt.tracking_analysis;
import java.util.HashMap;

import org.xxxmathxxx.tddt.data.Exercise;


/**
 * The Class AnalyzedTrackingDataCollection.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedTrackingDataCollection extends HashMap<Exercise,AnalyzedTrackingData>{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 108521877637533329L;
	

	public AnalyzedTrackingData getDataWithExercise(Exercise exercise){
		return get(exercise);
	}
	
	/**
	 * Adds the analyzed tracking data.
	 *
	 * @param data the data
	 */
	public void addAnalyzedTrackingData(Exercise exercise, AnalyzedTrackingData data){
		put(exercise,data);
	}
	
	
}
