package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.timer.BasicTimer;

import javafx.application.Platform;

public class ReversedClockLabel extends ClockLabel {

	double babystepsTime;
	
	public ReversedClockLabel(BasicTimer syncedTimer, double bsTime) {
		super(syncedTimer);
		
		
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
