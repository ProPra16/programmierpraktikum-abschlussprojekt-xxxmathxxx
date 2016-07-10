package org.xxxmathxxx.tddt.tracking_analysis;
import java.util.ArrayList;


/**
 * The Class AnalyzedTrackingDataCollection.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzedTrackingDataCollection extends ArrayList<Object>{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 108521877637533329L;
	
	/**
	 * Gets all data with exercise.
	 *
	 * @param exercise the exercise
	 * @return all data with this exercise
	 */
	public AnalyzedTrackingData getDataWithExercise(String exercise){

		AnalyzedTrackingData data = null;
		
		for(int i = 0; i < this.size(); i++){
			data = (AnalyzedTrackingData) this.get(i);
			if(data.exercise.equals(exercise))
				return data;
		}
		return data;
	}
	
	/**
	 * Adds the analyzed tracking data.
	 *
	 * @param data the data
	 */
	public void addAnalyzedTrackingData(AnalyzedTrackingData data){
		
		for(int i = 0; i < this.size(); i++){
			if( ((AnalyzedTrackingData)this.get(i)).exercise.equals(data.exercise)){
				this.add(i, data);
				return;
			}			
		}
		this.add(data);
	}
	
	
}
