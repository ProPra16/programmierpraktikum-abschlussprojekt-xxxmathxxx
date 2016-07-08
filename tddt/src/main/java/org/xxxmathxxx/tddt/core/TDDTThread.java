package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;

public class TDDTThread {
	
	private Profile user;
	private Exercise currentExercise;
	
	public TDDTThread(Profile user){
		this.user = user;
	}
	
	public void beginExercise(Exercise ex){
		this.currentExercise = ex;
		//TODO: Reset timers whatever do stuff
	}
	
	public Exercise getExercise(){
		//getter because exercise shouldn't be modified directly
		return currentExercise;
	}
	
	public Profile getUserProfile(){
		//getter because exercise shouldn't be modified directly
		return user;
	}

	public void awardMedal(Long exerciseID, MedalState newState) {
		user.setMedalState(exerciseID, newState);
	}
	
}
