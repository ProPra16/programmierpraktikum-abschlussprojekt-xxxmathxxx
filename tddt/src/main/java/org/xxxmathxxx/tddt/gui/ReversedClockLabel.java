package org.xxxmathxxx.tddt.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.timer.BasicTimer;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * @author xxxMathxxx
 * Countdown label
 */
public class ReversedClockLabel extends Label {

	/**
	 * Switchin time
	 */
	public double babystepsTime;
	
	BasicTimer syncedTimerr;
	
	/**
	 * Initialises the label
	 * @param syncedTimer timer to be synced
	 */
	public ReversedClockLabel(BasicTimer syncedTimer) {
		this.syncedTimerr = syncedTimer;
		new Timer().scheduleAtFixedRate(new TimeUpdateTask(),0 , 50);
		
		
		babystepsTime=TDDTThread.getInstance().getExercise().config.babystepsTime*60;
	}

	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			syncTime(syncedTimerr.getTime(), babystepsTime);
		}
	}
	
	private void syncTime(double time, double bsTime){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try{
					setText("Babysteps: "+String.valueOf(Math.round(bsTime-time)));
				}
				catch(Exception e){
					//TODO:
					//Probably occurs if the event is invoked after exit
				}

			}

		});

	}
}
