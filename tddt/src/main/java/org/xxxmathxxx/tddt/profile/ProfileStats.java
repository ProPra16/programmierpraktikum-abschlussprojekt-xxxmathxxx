package org.xxxmathxxx.tddt.profile;

import java.util.HashMap;

import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;



/*
 * @Author: Tschebyscheff, 21.06.16
 * A class that allows to see which exercises are mastered
 * A class about statistics of a specific profile
 */


/**
 * The Class ProfileStats.
 */
public class ProfileStats implements java.io.Serializable {

	/** This class is stored using Serializable and thus requires a unique identifier. */
	private static final long serialVersionUID = 1L;
	
	/** The tracking data list. */
	private HashMap<Long,AnalyzedTrackingData> trackingData;
	
	/** Hash-Map that encodes the MedalState the user has for Exercises with a given Long ID. */
	private HashMap<Long,MedalState> achievements;
	
	/**
	 * Instantiates a new profile stats.
	 */
	public ProfileStats(){
		this.trackingData = new HashMap<Long,AnalyzedTrackingData>();
		this.achievements = new HashMap<Long,MedalState>();
	}
	
	/**
	 * Setter that stores a given MedalState for an exercise with a given ID.
	 *
	 * @param exerciseID The exercise ID as Long
	 * @param newState The MedalState as MedalState
	 */
	public void setMedalState(long exerciseID, MedalState newState){
		achievements.put(exerciseID, newState);
	}
	
	

	/**
	 * Gets the medal state.
	 *
	 * @param exerciseID the exercise ID
	 * @return the medal state
	 */
	public MedalState getMedalState(long exerciseID) {
		if (achievements.containsKey(exerciseID)){
			return achievements.get(exerciseID);
		}
		return null;
	}


	public void addTrackingData(Long exerciseID, AnalyzedTrackingData dataForThisExercise) {
		trackingData.put(exerciseID, dataForThisExercise);
	}

	public AnalyzedTrackingData getTrackingData(Long key) {
		if (trackingData.containsKey(key)){
			return trackingData.get(key);
		}
		return null;
	}	
}
