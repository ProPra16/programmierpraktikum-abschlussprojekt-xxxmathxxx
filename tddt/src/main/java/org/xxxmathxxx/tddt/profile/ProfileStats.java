package org.xxxmathxxx.tddt.profile;

import org.xxxmathxxx.tddt.tracking.Tracker;

/*Author: Tschebyscheff, 21.06.16
 * A class about statistics of a specific profile
 * should contain informations about our tracking-system, MasteredExerciseTests and..
 */

public abstract class ProfileStats extends MasteredExercise {

	private Tracker tracker;
	
	
	/*
	 * Tracking-Results are saved here
	 */
	public void setTracker(Tracker tracker){
		this.tracker = tracker;
	}
	
	/*
	 * getting Tracking results back
	 */
	public Tracker getTracker(){
		return tracker;
	}
}
