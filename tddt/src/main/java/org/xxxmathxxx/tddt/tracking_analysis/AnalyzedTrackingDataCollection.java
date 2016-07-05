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
	public ArrayList<Object> getAllDataWithExercise(String exercise){
		ArrayList<Object> list = new ArrayList<Object>();
		AnalyzedTrackingData data;
		
		for(int i = 0; i < this.size(); i++){
			data = (AnalyzedTrackingData) this.get(i);
			if(data.exercise.equals(exercise))
				list.add(data);
		}
		return list;
	}
	
	/**
	 * Gets all data with name.
	 *
	 * @param profileName the profile name
	 * @return all data with name
	 */
	public ArrayList<Object> getAllDataWithName(String profileName){
		ArrayList<Object> list = new ArrayList<Object>();
		AnalyzedTrackingData data;
		
		for(int i = 0; i < this.size(); i++){
			data = (AnalyzedTrackingData) this.get(i);
			if(data.profileName.equals(profileName))
				list.add(data);
		}
		return list;
	}
	
	/**
	 * Gets the analyzed tracking data.
	 *
	 * @param profileName the profile name
	 * @param exercise the exercise
	 * @return the analyzed tracking data
	 */
	public AnalyzedTrackingData getAnalyzedTrackingData(String profileName, String exercise){
		AnalyzedTrackingData data = null;
		AnalyzedTrackingData ret = null;
		
		for(int i = 0; i < this.size(); i++){
			data = (AnalyzedTrackingData) this.get(i);
			if(data.profileName.equals(profileName) && data.exercise.equals(exercise)){
				ret = data;
				break;
			}
		}
		return ret;
	}
}
