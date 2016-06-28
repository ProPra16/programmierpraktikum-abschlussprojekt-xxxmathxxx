package org.xxxmathxxx.tddt.data;

/**
 * @author Euler
 * A class out of pure awesomeness
 */
public class Exercise 
{
	public String name;
	public String description;
	public ExerciseClass[] referencedClasses;   //TODO: Wrap it
	public ExerciseTest[] referencedTests;		//TODO: This one too

	public Boolean babysteps;
	public Boolean timetracking;
	
	public Exercise(String name, String description, ExerciseClass[] referencedClasses, ExerciseTest[] referencedTests)
	{
		this.name=name;
		this.description=description;
		this.referencedClasses=referencedClasses;
		this.referencedTests=referencedTests;
		
		//WIP
	}
}