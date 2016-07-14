package org.xxxmathxxx.tddt.timer;

import org.xxxmathxxx.tddt.core.TDDTThread;

/**
 * @author xxxMathxxx
 * BabystepsTimer to track time until reverting changes
 */
public class BabystepsTimer extends BasicTimer{

	@Override
	public void onTick(){
		if (this.elapsedTime > TDDTThread.getInstance().getExercise().config.babystepsTime*60){
			TDDTThread.getInstance().performBabystepRevert(); //trigger revert
			this.elapsedTime = 0; //reset time
		}
	}

}
