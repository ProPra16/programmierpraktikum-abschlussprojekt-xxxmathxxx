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
	
	public double bronzeTime; //In seks
	public double silverTime;
	public double goldTime;
	
	/**
	 * @param babystepsEnabled Boolean
	 * @param babystepstime Double
	 * @param timetrackingEnabled Boolean
	 * @param bronzeTime Double
	 * @param silverTime Double
	 * @param goldTime Double
	 */
	public ExerciseConfig(Boolean babystepsEnabled, double babystepstime, Boolean timetrackingEnabled, double bronzeTime, double silverTime, double goldTime)
	{
		this.babystepsEnabeled=babystepsEnabled;
		this.babystepsTime= babystepstime;
		
		this.timetrackingEnabled=timetrackingEnabled;
		
		this.bronzeTime=bronzeTime;
		this.silverTime=silverTime;
		this.goldTime= goldTime;
		
	}
}
