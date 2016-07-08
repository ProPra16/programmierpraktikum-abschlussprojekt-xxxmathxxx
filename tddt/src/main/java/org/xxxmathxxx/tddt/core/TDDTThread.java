package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.data.Exercise;
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
	
}
