package org.xxxmathxxx.tddt.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.xxxmathxxx.tddt.core.TDDT;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ClockLabel extends Label{
		
	private double totalTime;
	
	public ClockLabel(double totalTime){
		this.totalTime = totalTime;
		this.setText("TEST");
		new Timer().scheduleAtFixedRate(new TimeUpdateTask(),0 , 50);

	}
	
	
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			if (TDDT.currentThread != null){
				syncTime(TDDT.currentThread.tracker.totalTimer.getTime());
			}
		}
	}
	
	public void syncTime(double time){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try{
					setText(""+Math.round(time));
					if (time > 4d/5d*totalTime){
						setTextFill(Color.RED);
					}
					else{
						setTextFill(Color.BLACK);
					}
				}
				catch(Exception e){
					//TODO:
					//Probably occurs if the event is invoked after exit
				}

			}

		});

	}
}
