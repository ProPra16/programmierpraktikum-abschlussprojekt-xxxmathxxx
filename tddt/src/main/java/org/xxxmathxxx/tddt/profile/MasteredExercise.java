package org.xxxmathxxx.tddt.profile;

import java.util.ArrayList;

/*
 * @Author: Tschebyscheff, 21.06.16
 * A class that allows to see which exercises are mastered
 */
public class MasteredExercise {

	private ArrayList<String> nameList;		// list of all exercise-names
	private ArrayList<Boolean> masteredList;// list of all mastered exercises 
	
	public MasteredExercise(){
		nameList = new ArrayList<String>();
		masteredList = new ArrayList<Boolean>();
	}
	
	/*
	 * a method about adding exercise names, if name is equal to an excisting object, the excisting Object  will be removed
	 * */
	public void addExercise(String name){
	 
		try{
			if(nameList.contains(name)){
				int i = nameList.indexOf(name);
				nameList.remove(i);
				masteredList.remove(i);
			}
		}
		catch(NullPointerException e){}
		nameList.add(name);
		System.out.println(name);
		masteredList.add(false);
	}
	
	/*
	 * a method about adding exercise names, if name is equal to an excisting object, the excisting Object  will be removed
	 */
	public void addExercise(String name, boolean b){
		try{
			if(nameList.contains(name)){
				int i = nameList.indexOf(name);
				nameList.remove(i);
				masteredList.remove(i);
					}
			}	
		catch(NullPointerException e){}
		nameList.add(name);
		System.out.println(name);
		masteredList.add(b);
	}
	
	/*
	 * a method about deleting an existing object
	 */
	public void deleteExercise(int i){
		nameList.remove(i);
		masteredList.remove(i);
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
	 * a method about returning all exercise-names
	 */
	public String[] giveAllExerciseNames(){
		
		String[] ret = new String[nameList.size()];
		for(int i = 0; i < nameList.size(); i++){
			String tmp = nameList.get(i);
			ret[i] = tmp;
		}
		return ret;
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
	
	
}
