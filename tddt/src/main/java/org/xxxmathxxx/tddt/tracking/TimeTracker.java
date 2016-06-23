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
	//private boolean enableTracking = false;
	
	public TimeTracker(){
		timer = new BasicTimer();
	}
	
	/*
	 * starts to track all activities including timer start
	 */
	public void  startTracking(){
		timer.toggleActive();
		this.enableTracking();
	}
	
	/*
	 * tracking will stopp including timer stop
	 */
	public void stopTracking(){
		timer.toggleActive();
		this.disableTracking();
	}
	
	/*
	 * returns the time that elapsed since starting the timer
	 */
	public String geTime(){
		return timer.getTimeInSecondsAsString();
	}
}
