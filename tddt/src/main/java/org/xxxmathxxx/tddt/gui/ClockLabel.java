package org.xxxmathxxx.tddt.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.xxxmathxxx.tddt.timer.BasicTimer;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class ClockLabel extends Label{
	
	BasicTimer syncedTimer;
	
	public ClockLabel(BasicTimer syncedTimer){
		this.syncedTimer = syncedTimer;
		new Timer().scheduleAtFixedRate(new TimeUpdateTask(),0 , 50);
	}
	
	
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			syncTime(syncedTimer.getTime());
		}
	}
	
	public void syncTime(double time){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try{
					setText(""+Math.round(time));
				}
				catch(Exception e){
					//TODO:
					//Probably occurs if the event is invoked after exit
				}

			}

		});

	}
}
