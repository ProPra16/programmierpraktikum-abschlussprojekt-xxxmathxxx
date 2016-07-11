package org.xxxmathxxx.tddt.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.xxxmathxxx.tddt.timer.BasicTimer;

import javafx.application.Platform;

public class ReversedClockLabel extends ClockLabel {

	public double babystepsTime;
	
	BasicTimer syncedTimerr;
	
	public ReversedClockLabel(BasicTimer syncedTimer, double bsTime) {
		super(syncedTimer);
		this.syncedTimerr = syncedTimer;
		new Timer().scheduleAtFixedRate(new TimeUpdateTask(),0 , 50);
		
		babystepsTime=bsTime;
	}

	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			syncTime(syncedTimer.getTime(), babystepsTime);
		}
	}
	
	public void syncTime(double time, double bsTime){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try{
					setText(""+String.valueOf(bsTime*60-Math.round(time)));
				}
				catch(Exception e){
					//TODO:
					//Probably occurs if the event is invoked after exit
				}

			}

		});

	}
}
