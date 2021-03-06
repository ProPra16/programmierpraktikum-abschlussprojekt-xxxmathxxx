package org.xxxmathxxx.tddt.data;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;

/** Class that contains all informations about a given exercise
 * @author xxxMathxxx 2016
 * 
 */
public class Exercise {
	/**The name of the exercise as String
	 * 
	 */
	public String name;
	/**The description of the exercise as String
	 * (This is the assignment)
	 */
	public String description;
	/**The ID of the exercise as Long, this should be unique for each exercise
	 * 
	 */
	public long id;
	/**Those are the classes contained in the exercise
	 * 
	 */
	public ExerciseClass[] referencedClasses; 
	/**The tests contained in the exercise
	 * 
	 */
	public ExerciseTest[] referencedTests; 
	/**The hidden finish test that checks if the exercise is completed
	 * 
	 */
	public ExerciseTest referencedFinishTest;

	/**The ExerciseConfig that stores additional data regarding the Exercise
	 * @see ExerciseConfig
	 */
	public ExerciseConfig config;

	/**
	 * Constructor
	 * @param name The name of the exercise as String
	 * @param id The ID of the exercise as Long
	 * @param description The description of the exercise as String
	 * @param referencedClasses The classes the exercise uses
	 * @param referencedTests The tests the exercises uses
	 * @param referencedFinishTest The (hidden) tests that check for completion
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
		TDDTLogManager.getInstance().logMessage("___Exerciseprint___");
		TDDTLogManager.getInstance().logMessage( "name: "+name+
							"\ndesc: "+description+
							"\nid: "+id);
		
		TDDTLogManager.getInstance().logMessage("\nReferencedClasses:");
		
		for(int i=0; i<referencedClasses.length; i++)
		{
			TDDTLogManager.getInstance().logMessage("name: "+referencedClasses[i].name);
			TDDTLogManager.getInstance().logMessage("code:\n "+referencedClasses[i].code.rawText);
		}
		
		TDDTLogManager.getInstance().logMessage("\nReferencedTests:");
		
		for(int i=0; i<referencedTests.length; i++)
		{
			TDDTLogManager.getInstance().logMessage("name: "+referencedTests[i].name);
			TDDTLogManager.getInstance().logMessage("code:\n "+referencedTests[i].code.rawText);
		}
		
		
		TDDTLogManager.getInstance().logMessage("\nConfig:");
		TDDTLogManager.getInstance().logMessage("Babysteps:\n"
							+config.babystepsEnabeled+" "+
							+config.babystepsTime+"\n");
		
		TDDTLogManager.getInstance().logMessage("Timetracking:\n"+config.trackingEnabled+"\n");
		
		TDDTLogManager.getInstance().logMessage(this.config.medalTimes.toString());
		
	}

	/**This returns the appropriate MedalState corresponding to the given time
	 * @param time The time for which the MedalState should be calculated
	 * @return The MedalState, representing the Medal that was earned
	 */
	public MedalState checkMedalForTime(double time) {
		if (time <this.config.medalTimes.author*60){
			return MedalState.AUTHOR;
		}
		else if (time <this.config.medalTimes.gold*60){
			return MedalState.GOLD;
		}
		else if (time <this.config.medalTimes.silver*60){
			return MedalState.SILVER;
		}
		else if (time <this.config.medalTimes.bronze*60){
			return MedalState.BRONZE;
		}
		return MedalState.NONE; //feelsBadMan
	}
}