package org.xxxmathxxx.tddt.profile;

import java.util.ArrayList;
import java.util.HashMap;

import org.xxxmathxxx.tddt.tracking.Tracker;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingData;
import org.xxxmathxxx.tddt.tracking_analysis.AnalyzedTrackingDataCollection;



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

	/** The name list. */
	private ArrayList<String> nameList;		//TODO: CHANGE TO Long as Exercises are identified by long ID -> fabian, add this plz
	
	/** The mastered list. */
	private ArrayList<Boolean> masteredList;
	
	/** The tracker list. */
	private ArrayList<Tracker> trackerList; 
	
	/** The tracking data list. */
	private AnalyzedTrackingDataCollection trackingDataList;
	
	/** Hash-Map that encodes the MedalState the user has for Exercises with a given Long ID. */
	private HashMap<Long,MedalState> achievements;
	
	/**
	 * Instantiates a new profile stats.
	 */
	public ProfileStats(){
		nameList = new ArrayList<String>();
		masteredList = new ArrayList<Boolean>();
		trackerList = new ArrayList<Tracker>();
		trackingDataList = new AnalyzedTrackingDataCollection();
		this.achievements = new HashMap<Long,MedalState>();
	}
	
	
	/**
	 * Adds the tracking data.
	 *
	 * @param data the AnalyzedTrackingData
	 */
	public void addTrackingData(AnalyzedTrackingData data){
		trackingDataList.add(data);
		
	}
	
	/**
	 * Gets the analayzed tracking data.
	 *
	 * @return the analayzed tracking data
	 */
	public AnalyzedTrackingDataCollection getAnalayzedTrackingData(){
		return trackingDataList;
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
	 * a method about adding exercise names, if name is equal to an excisting object, the excisting Object  will be removed
	 * if b == true, Exercise will be set on mastered.
	 *
	 * @param tracker the tracker
	 * @param name the name
	 * @param b ???
	 */
	public void addExercise(Tracker tracker, String name, boolean b){
		try{
			if(nameList.contains(name)){
				int i = nameList.indexOf(name);
				nameList.remove(i);
				masteredList.remove(i);
				trackerList.remove(i);
			}
		}	
		catch(NullPointerException e){}
		nameList.add(name);
		masteredList.add(b);
		trackerList.add(tracker);
	}
	
	/**
	 * Delete exercise.
	 *
	 * @param i the index
	 */
	public void deleteExercise(int i){
		nameList.remove(i);
		masteredList.remove(i);
		trackerList.remove(i);
	}
	
	/**
	 * Checks if is exercise mastered.
	 *
	 * @param name the name
	 * @return true, if is exercise mastered
	 */
	public boolean isExerciseMastered(String name){
		if(nameList.contains(name)){
			int i = nameList.indexOf(name);
			return masteredList.get(i);
		}
		return false;
	}
	
	/**
	 * Sets the mastered exercise, true means exercise was mastered.
	 *
	 * @param i the i ndex
	 * @param b the b
	 */
	public void setMasteredExercise(int i, boolean b){
		masteredList.set(i, b);
	}
	
	/**
	 * Gets the exercise name.
	 *
	 * @param i the index
	 * @return the exercise name
	 */
	public String getExerciseName(int i){
		String ret = "";
		try{
			ret = nameList.get(i);}
		catch(IndexOutOfBoundsException e){
			ret = null;
		}
		System.out.println(ret);
		return ret;
	}
		
	
	/**
	 * Mastered exercises.
	 *
	 * @return the amount of masteredExercises
	 */
	public int masteredExercises(){
		int ret = 0;
		
		for(int i = 0; i < nameList.size(); i++){
			if((masteredList.get(i) == true))
				ret++;
		}
		return ret;
	}
	
	/**
	 * Exercise size.
	 *
	 * @return the size of different exercises
	 */
	public int ExerciseSize(){
		return nameList.size();
	}
	
	/**
	 * Gets the tracker.
	 *
	 * @param i the index
	 * @return the tracker
	 */
	public Tracker getTracker(int i){
		return trackerList.get(i);
	}
	
	/**
	 * Gets the tracker.
	 *
	 * @param exerciseName the exercise name
	 * @return the tracker
	 */
	public Tracker getTracker(String exerciseName){
		int i = this.indexOf(exerciseName);
		return trackerList.get(i);
	}
	
	/**
	 * Checks if is exercise added.
	 *
	 * @param exerciseName the exerciseName
	 * @return true, if is exercise added
	 */
	public boolean isExerciseAdded(String exerciseName){
		
		int i = this.indexOf(exerciseName);
		if(i != -1)
			return true;
		return false;
	}
	
	/**
	 * Index of.
	 *
	 * @param exerciseName the tmp
	 * @return the index of exercise exerciseName
	 */
	public int indexOf(String exerciseName){
		int i = nameList.indexOf(exerciseName);
		return i;
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
}
