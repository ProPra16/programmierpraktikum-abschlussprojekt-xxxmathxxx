package org.xxxmathxxx.tddt.babysteps;

import java.util.Timer;
import java.util.TimerTask;


/** An abstract class providing a simple timer, useful for tracking time in the babysteps context
 * @author xxxMathxxx, Jun 21, 2016
 *
 */
public class TaskTimer {
	
	/**
	 * The internal timer object
	 */
	private Timer timer;
	/**
	 * The elapsed time in seconds
	 */
	private int elapsedTime; 
	
	/**
	 * The internal state of the clock, describes whether or not it counts seconds
	 */
	private boolean isRunning;

	/**
	 * Default constructor, creates a new abstract timer object that counts seconds.
	 * Note, that the clock doesn't start by itself and instead needs to be activated with toggleActive()
	 */
	public TaskTimer(){
		timer = new Timer();
		isRunning = false;
		timer.scheduleAtFixedRate(new TimeUpdateTask(),0 , 1000);
	}
	
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			if (isRunning){
				elapsedTime ++;
			}
		}
	}
	
	public void toggleActive(){
		isRunning =! isRunning;
	}
	
	/** Returns the time that elapsed since starting the timer as a String (suitable for labels etc)
	 * @return The time in seconds as String
	 */
	public String getTimeInSecondsAsString(){
		return ""+elapsedTime;
	}
	
}