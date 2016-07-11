package org.xxxmathxxx.tddt.data;

/**
 * @author Fabian
 * Class to save all information about exercises
 */
public class Exercise {
	public String name;
	public String description;
	public long id;
	public ExerciseClass[] referencedClasses; 
	public ExerciseTest[] referencedTests; 
	public ExerciseTest referencedFinishTest;

	ExerciseConfig config;

	/**
	 * Constructor
	 * @param name The name of the exercise as String
	 * @param id The ID of the exercise as Long
	 * @param description The description of the exercise as String
	 * @param referencedClasses The classes the exercise uses
	 * @param referencedTests The tests the exercises uses
	 * @param referencedFinishTests The (hidden) tests that check for completion
	 * @param config The ExerciseConfig containing additional data
	 */
	public Exercise(String name, Long id, String description, ExerciseClass[] referencedClasses,
			ExerciseTest[] referencedTests, ExerciseTest referencedFinishTest, ExerciseConfig config) {
		this.name = name;
		this.id=id;
		this.description = description;
		this.referencedClasses = referencedClasses;
		this.referencedTests = referencedTests;
		this.referencedFinishTest = referencedFinishTest;
		this.config=config;
	}
	
	/**
	 * Prints the data. Testing purposes. You can thank me later. ... TY
	 */
	public void print()
	{
		System.out.println("___Exerciseprint___");
		System.out.println( "name: "+name+
							"\ndesc: "+description+
							"\nid: "+id);
		
		System.out.println("\nReferencedClasses:");
		
		for(int i=0; i<referencedClasses.length; i++)
		{
			System.out.println("name: "+referencedClasses[i].name);
			System.out.println("code:\n "+referencedClasses[i].code.rawText);
		}
		
		System.out.println("\nReferencedTests:");
		
		for(int i=0; i<referencedTests.length; i++)
		{
			System.out.println("name: "+referencedTests[i].name);
			System.out.println("code:\n "+referencedTests[i].code.rawText);
		}
		
		
		System.out.println("\nConfig:");
		System.out.println("Babysteps:\n"
							+config.babystepsEnabeled+" "+
							+config.babystepsTime+"\n");
		
		System.out.println("Timetracking:\n"+config.timetrackingEnabled+"\n");
		
		System.out.println(this.config.medalTimes.toString());
		
	}

	public ExerciseTest getFinalTest() {
		return referencedFinishTest;
	}
}