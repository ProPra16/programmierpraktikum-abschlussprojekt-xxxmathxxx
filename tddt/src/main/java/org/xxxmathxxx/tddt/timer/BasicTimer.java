package org.xxxmathxxx.tddt.timer;

import java.util.Timer;
import java.util.TimerTask;


/**
 * An abstract class providing a simple timer
 * @author xxxMathxxx, Jun 23, 2016
 */
public class BasicTimer {
	
	// STATICS
	
	/**
	 * The updateRate in ms
	 */
	private static int updateRate = 10; 
	
	// STATICS
	
	/**
	 * The internal timer object
	 */
	private Timer timer;
	/**
	 * The elapsed time in seconds
	 */
	protected double elapsedTime; 
	
	/**
	 * The internal state of the clock, describes whether or not it counts seconds
	 */
	private boolean active;

	/**
	 * Default constructor, creates a new abstract timer object that counts seconds.
	 * Note, that the clock doesn't start by itself and instead needs to be activated with toggleActive()
	 */
	public BasicTimer(){
		timer = new Timer();
		active = false;
		timer.scheduleAtFixedRate(new TimeUpdateTask(),0 , updateRate);
	}
	
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			if (active){
				elapsedTime += (double)updateRate/1000d;
				onTick();
			}
		}
	}
	
	/**
	 * Gets called on tick
	 */
	public  void onTick(){};

	
	/**
	 * Sets active or not
	 * @param state 
	 */
	public void setActive(boolean state){
		active = state;
	}
	
	/** Returns the time that elapsed since starting the timer as a String (suitable for labels etc)
	 * @return The time in seconds as String
	 */
	public String getTimeInSecondsAsString(){
		return ""+elapsedTime;
	}

	/**
	 * Returns time in passed minutes
	 * @return Time in minutes
	 */
	public double getTime() {
		return elapsedTime;
	}
	
	/**
	 * Resets elapsed time to 0
	 */
	public void resetTimer()
	{
		elapsedTime=0;
	}
	
}