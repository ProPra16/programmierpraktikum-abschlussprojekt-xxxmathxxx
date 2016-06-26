/**
 * An abstract class providing a simple timer
 * @author xxxMathxxx, Jun 23, 2016
 * 
 */
package org.xxxmathxxx.tddt.timer;

import java.util.Timer;
import java.util.TimerTask;



public class BasicTimer {
	
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
	public BasicTimer(){
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