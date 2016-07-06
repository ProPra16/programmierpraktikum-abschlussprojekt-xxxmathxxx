package org.xxxmathxxx.tddt.data;

/**
 * @author Fabian
 * Class to save all information about exercises
 */
public class Exercise {
	public String name;
	public String description;
	public String id;
	public ExerciseClass[] referencedClasses; 
	public ExerciseTest[] referencedTests; 

	ExerciseConfig config;

	/**
	 * Constructor
	 * @param name
	 * @param id
	 * @param description
	 * @param referencedClasses
	 * @param referencedTests
	 * @param referencedFinishTests
	 * @param config
	 */
	public Exercise(String name, String id, String description, ExerciseClass[] referencedClasses,
			ExerciseTest[] referencedTests, ExerciseTest[] referencedFinishTests, ExerciseConfig config) {
		this.name = name;
		this.id=id;
		this.description = description;
		this.referencedClasses = referencedClasses;
		this.referencedTests = referencedTests;
		
		this.config=config;
	}
	
	/**
	 * Prints the data. Testing purposes. You can thank me later.
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
		
		System.out.println("Medals:"
							+"\nBronze: "+config.bronzeTime
							+"\nSilver: "+config.silverTime
							+"\nGold: "+config.goldTime
							);
		
	}
}