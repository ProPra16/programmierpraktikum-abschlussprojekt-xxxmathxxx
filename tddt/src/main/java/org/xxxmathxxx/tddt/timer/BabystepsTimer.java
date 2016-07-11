package org.xxxmathxxx.tddt.timer;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.core.TDDTThread;

public class BabystepsTimer extends BasicTimer{

	@Override
	public void onTick(){
		if (this.elapsedTime > TDDTThread.getInstance().getExercise().config.babystepsTime){
			TDDTThread.getInstance().performBabystepRevert(); //trigger revert
			this.elapsedTime = 0; //reset time
		}
	}

}
