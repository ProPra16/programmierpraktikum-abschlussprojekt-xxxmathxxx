/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.xxxmathxxx.tddt.timer.BasicTimer;

/**
 * @author Tschebyscheff, 23.06.16
 * rudimentary tracking class, defined for time tracking
 *
 */
public abstract class TimeTracker extends ActivityTracker {

	private BasicTimer timer;
	
	public TimeTracker(){
		timer = new BasicTimer();
	}
	
	/*
	 * starts to track all activities including timer start
	 */
	public void  startTracking(){
		timer.toggleActive();
	}
	
	/*
	 * tracking will stopp including timer stop
	 */
	public void stopTracking(){
		timer.toggleActive();
	}
	
	/*
	 * returns the time that elapsed since starting the timer
	 */
	public String getTime(){
		return timer.getTimeInSecondsAsString();
	}
}
