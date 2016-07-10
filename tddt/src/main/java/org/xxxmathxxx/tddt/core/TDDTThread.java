package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;

/**
 * The Class TDDTThread.
 */
public class TDDTThread {
	
	/** The user. */
	private Profile user;
	
	/** The current exercise. */
	private Exercise currentExercise;
	
	/**  analyzedData. */
	private AnalyzedTrackingData analyzedTrackingData = null;
	
	/**
	 * Instantiates a new TDDT thread.
	 *
	 * @param user the user
	 */
	public TDDTThread(Profile user){
		this.user = user;
	}
	
	/**
	 * Sets the analyzed tracking data.
	 *
	 * @param data the new analyzed tracking data
	 */
	public void setAnalyzedTrackingData(AnalyzedTrackingData data){
		analyzedTrackingData = data;
	}
	
	/**
	 * Gets the analyzed tracking data.
	 *
	 * @return the analyzed tracking data
	 */
	public AnalyzedTrackingData getAnalyzedTrackingData(){
		return analyzedTrackingData;
	}
	
	/**
	 * Begin exercise.
	 *
	 * @param ex the ex
	 */
	public void beginExercise(Exercise ex){
		this.currentExercise = ex;
		//TODO: Reset timers whatever do stuff
	}
	
	/**
	 * Gets the exercise.
	 *
	 * @return the exercise
	 */
	public Exercise getExercise(){
		//getter because exercise shouldn't be modified directly
		return currentExercise;
	}
	
	/**
	 * Gets the user profile.
	 *
	 * @return the user profile
	 */
	public Profile getUserProfile(){
		//getter because exercise shouldn't be modified directly
		return user;
	}

	/**
	 * Award medal.
	 *
	 * @param exerciseID the exercise ID
	 * @param newState the new state
	 */
	public void awardMedal(Long exerciseID, MedalState newState) {
		user.setMedalState(exerciseID, newState);
	}
	
}
