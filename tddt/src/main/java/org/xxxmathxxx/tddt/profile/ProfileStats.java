package org.xxxmathxxx.tddt.profile;

import java.util.ArrayList;

import org.xxxmathxxx.tddt.tracking.Tracker;


/*
 * @Author: Tschebyscheff, 21.06.16
 * A class that allows to see which exercises are mastered
 * A class about statistics of a specific profile
 */


/**
 * The Class ProfileStats.
 */
public abstract class ProfileStats {

	/** The name list. */
	private ArrayList<String> nameList;		
	
	/** The mastered list. */
	private ArrayList<Boolean> masteredList;
	
	/** The tracker list. */
	private ArrayList<Tracker> trackerList; 
	
	/**
	 * Instantiates a new profile stats.
	 */
	public ProfileStats(){
		nameList = new ArrayList<String>();
		masteredList = new ArrayList<Boolean>();
		trackerList = new ArrayList<Tracker>();
		
	}
	
	
	/**
	 * a method about adding exercise names, if name is equal to an excisting object, the excisting Object  will be removed
	 * if b == true, Exercise will be set on mastered.
	 *
	 * @param tracker the tracker
	 * @param name the name
	 * @return -
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
	 * Sets the mastered exercise, true means exercise was mastered
	 *
	 * @param i the i ndex
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
	 * @param exerciseName, the exerciseName
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
	 * @param tmp the tmp
	 * @return the index of exercise exerciseName
	 */
	public int indexOf(String exerciseName){
		int i = nameList.indexOf(exerciseName);
		return i;
	}	
}
