package org.xxxmathxxx.tddt.data;

/**
 * @author xxxMathxxx
 * This class saves the Config of an exercise
 */
public class ExerciseConfig 
{
	/**
	 * Determines if babysteps are enabled.
	 */
	public Boolean babystepsEnabeled;
	
	/**
	 * Saves time you can use for babysteps.
	 */
	public double babystepsTime; 
	
	/**
	 * Determines if the tracking extension is enabled.
	 */
	public Boolean trackingEnabled;
	
	/**
	 * Saves times you need for Medals.
	 */
	public MedalTimes medalTimes; 
	
	/**
	 * @param babystepsEnabled Boolean
	 * @param babystepstime Double
	 * @param timetrackingEnabled Boolean
	 * @param medalTimes The time requirements for getting medals on this exercise
	 */
	public ExerciseConfig(Boolean babystepsEnabled, double babystepstime, Boolean timetrackingEnabled, MedalTimes medalTimes)
	{
		this.babystepsEnabeled=babystepsEnabled;
		this.babystepsTime= babystepstime;
		
		this.trackingEnabled=timetrackingEnabled;
		
		this.medalTimes=medalTimes;
		
	}
}
