package org.xxxmathxxx.tddt.tracking_analysis;
import java.util.HashMap;

import org.xxxmathxxx.tddt.data.Exercise;


/**This hash map contains AnalyzedTrackingData that is mapped with a given Exercise as key to the data.
 *
 * @author xxxMathxxx 2016
 */
public class AnalyzedTrackingDataCollection extends HashMap<Exercise,AnalyzedTrackingData>{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 108521877637533329L;
	

	/**Returns the AnalyzedTrackingData for a given exercise
	 * @param exercise The exercise as Exercise
	 * @return The AnalyzedTrackingData associated with this exercise
	 */
	public AnalyzedTrackingData getDataWithExercise(Exercise exercise){
		return get(exercise);
	}
	

	/**Adds AnalyzedTrackingData to a given exercise
	 * @param exercise The exercise to which the data should be added
	 * @param data The AnalyzedTrackingData
	 */
	public void addAnalyzedTrackingData(Exercise exercise, AnalyzedTrackingData data){
		put(exercise,data);
	}
	
	
}
