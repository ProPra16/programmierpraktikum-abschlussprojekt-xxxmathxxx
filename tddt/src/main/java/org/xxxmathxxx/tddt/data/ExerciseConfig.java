package org.xxxmathxxx.tddt.data;

/**
 * @author Fabian
 * This class saves the Config of an exercise
 */
public class ExerciseConfig 
{
	public Boolean babystepsEnabeled;
	public double babystepsTime; //In seks
	
	public Boolean timetrackingEnabled;
	
	public MedalTimes medalTimes; //as seconds
	
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
		
		this.timetrackingEnabled=timetrackingEnabled;
		
		this.medalTimes=medalTimes;
		
	}
}
