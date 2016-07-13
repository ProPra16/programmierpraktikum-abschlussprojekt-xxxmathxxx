package org.xxxmathxxx.tddt.data;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author xxxMathxxx
 * Exercise wrapping
 */
@SuppressWarnings("serial")
public class ExerciseCollection extends ArrayList<Exercise>
{

	/**
	 * Collection of Exercises
	 * @param list Exercises
	 */
	public ExerciseCollection(ArrayList<Exercise> list){
		super();
		this.addAll(list);
	}
	
	/**
	 * Returns a random Exercise
	 * @return random Exercise
	 */
	public Exercise getRandomExercise()
	{
		int rnd= (int) (Math.random()*size());
		
		return get(rnd);
	}

	/**
	 * Returns the collection as observaleList
	 * @return observableList
	 */
	public ObservableList<Exercise> asObservableList() {
		ObservableList<Exercise> ret = FXCollections.observableArrayList();
		for(Exercise e: this){
			ret.add(e);
		}
		return ret;
	}

}
