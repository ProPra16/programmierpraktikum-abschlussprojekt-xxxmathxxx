package org.xxxmathxxx.tddt.profile;

import org.xxxmathxxx.tddt.tracking.StageGreenTracker;

/*Author: Tschebyscheff, 21.06.16
 * A class about statistics of a specific profile
 * should contain informations about our tracking-system, MasteredExerciseTests and..
 */

public class ProfileStats extends MasteredExercise {

	private StageGreenTracker stageGreenTracker;
	
	
	/*
	 * Tracking-Results are saved here
	 */
	public void addStageGreenTracker(StageGreenTracker tracker){
		stageGreenTracker = tracker;
	}
}
