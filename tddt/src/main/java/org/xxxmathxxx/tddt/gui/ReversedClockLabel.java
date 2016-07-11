package org.xxxmathxxx.tddt.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.timer.BasicTimer;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ReversedClockLabel extends Label {

	public double babystepsTime;
	
	BasicTimer syncedTimerr;
	
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
	
	public void syncTime(double time, double bsTime){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try{
					setText(""+String.valueOf(Math.round(bsTime-time)));
				}
				catch(Exception e){
					//TODO:
					//Probably occurs if the event is invoked after exit
				}

			}

		});

	}
}
