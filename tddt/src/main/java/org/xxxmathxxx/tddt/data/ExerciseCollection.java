package org.xxxmathxxx.tddt.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fabian
 * Exercise wrapping
 */
public class ExerciseCollection 
{

	List<Exercise> ex;
	
	/**
	 * Creates a new ExerciseColletion object
	 */
	public ExerciseCollection()
	{
		ex= new ArrayList<Exercise>();
	}
	
	/**
	 * Creates a new ExerciseColletion object
	 * @param ex Exercise List
	 */
	public ExerciseCollection(ArrayList<Exercise> ex)
	{
		this.ex=ex;
	}
	
	/**
	 * Adds a new Exercise to the colletion
	 * @param e Exercise
	 */
	public void addExercise(Exercise e)
	{
		ex.add(e);
	}
	
	/**
	 * Returns a random Exercise
	 * @return random Exercise
	 */
	public Exercise getRandomExercise()
	{
		int rnd= (int) (Math.random()*ex.size());
		
		return ex.get(rnd);
	}
	
	/**
	 * Returns the size of the Colletion
	 * @return Size
	 */
	public int getLength()
	{
		return ex.size();
	}
	
	/**
	 * Returns a specified exercise
	 * @param i Index of wanted Exercise
	 * @return Specified Exercise
	 */
	public Exercise getExercise(int i)
	{
		return ex.get(i);
	}
	
}
