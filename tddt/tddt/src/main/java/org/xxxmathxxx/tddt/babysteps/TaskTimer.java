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
	 * Default constructor, creates a new timer that is instantly running
	 */
	public TaskTimer(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimeUpdateTask(),0 , 1000);
	}
	
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			elapsedTime ++;
		}
	}
	
	/**
	 * @return
	 */
	public String getTimeInSecondsAsString(){
		return ""+elapsedTime;
	}
	
}