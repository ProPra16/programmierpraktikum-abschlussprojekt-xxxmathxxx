package org.xxxmathxxx.tddt.profile;

import java.util.ArrayList;

import org.xxxmathxxx.tddt.tracking.Tracker;

/*
 * @Author: Tschebyscheff, 21.06.16
 * A class that allows to see which exercises are mastered
 * A class about statistics of a specific profile
 */

//TODO: Shouldn't this be a static class and not the superclass of Profile???
//TODO: The problem here is that EVERY profile stores ALL exercises

public abstract class ProfileStats {

	private ArrayList<String> nameList;		// list of all exercise-names
	private ArrayList<Boolean> masteredList;// list of all mastered exercises 
	private ArrayList<Tracker> trackerList; // list of all trackers
	
	public ProfileStats(){
		nameList = new ArrayList<String>();
		masteredList = new ArrayList<Boolean>();
		trackerList = new ArrayList<Tracker>();
	}
	
	
	/*
	 * a method about adding exercise names, if name is equal to an excisting object, the excisting Object  will be removed
	 * if b == true, Exercise will be set on mastered
	 * 
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
	
	/*
	 * a method about deleting an existing object
	 */
	public void deleteExercise(int i){
		nameList.remove(i);
		masteredList.remove(i);
		trackerList.remove(i);
	}
	
	/*
	 * a method about checking if specific element was mastered
	 */
	public boolean isExerciseMastered(String name){
		if(nameList.contains(name)){
			int i = nameList.indexOf(name);
			return masteredList.get(i);
		}
		return false;
	}
	
	/*
	 * a method that will set a boolean in masteredList at index i, true means exercise was mastered
	 */
	public void setMasteredExercise(int i, boolean b){
		masteredList.set(i, b);
	}
	
	/*
	 * a method that returns the name of a exercise at the position i
	 * if i is not a valid index, method will return null
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
		
	
	/*
	 * a method about giving the number of all masteredExercises
	 */
	public int masteredExercises(){
		int ret = 0;
		
		for(int i = 0; i < nameList.size(); i++){
			if((masteredList.get(i) == true))
				ret++;
		}
		return ret;
	}
	
	/*
	 * a method about the size of all added exercise-names
	 */
	public int ExerciseSize(){
		return nameList.size();
	}
	
	public Tracker getTracker(int i){
		return trackerList.get(i);
	}
	
	public Tracker getTracker(String tmp){
		int i = this.indexOf(tmp);
		return trackerList.get(i);
	}
	
	public boolean isExerciseAdded(String tmp){
		
		int i = this.indexOf(tmp);
		if(i != -1)
			return true;
		return false;
	}
	
	public int indexOf(String tmp){
		int i = nameList.indexOf(tmp);
		return i;
	}	
}
