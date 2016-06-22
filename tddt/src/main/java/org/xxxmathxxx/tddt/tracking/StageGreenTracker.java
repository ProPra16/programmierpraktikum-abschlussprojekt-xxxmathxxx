/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author Tschebyscheff, 22.06.16
 * class for gathering all kinds of information during tracking process in stage green
 * informations are kinds of: Errors, Activities(writing) and ...
 *
 *TODO: add more informations, add methos for returning time, errors, typ of errors
 */
public class StageGreenTracker extends ActivitiesDuringTracking {

	TrackingTimer timer;
	Error error = new Error();
	boolean enableTracking = false;
	
	public StageGreenTracker(){
		timer = new TrackingTimer();
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
	
	
	
	
	
	
	
}
