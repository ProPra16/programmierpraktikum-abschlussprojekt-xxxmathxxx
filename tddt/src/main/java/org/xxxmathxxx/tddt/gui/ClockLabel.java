package org.xxxmathxxx.tddt.gui;

import java.util.Timer;
import java.util.TimerTask;

import org.xxxmathxxx.tddt.timer.BasicTimer;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 * @author xxxMathxxx 2016
 * This class creates the clock and makes sure it runs properly.
 */
public class ClockLabel extends Label{
	
	/**
	 * Timer which is synchronized.
	 */
	BasicTimer syncedTimer;
	
	/**
	 * Used to Construct a new ClockLabel
	 * @param syncedTimer timer to synchronize
	 */
	public ClockLabel(BasicTimer syncedTimer){
		this.syncedTimer = syncedTimer;
		new Timer().scheduleAtFixedRate(new TimeUpdateTask(),0 , 50);
	}
	
	
	/**
	 * @author xxxMathxxx
	 * Private class of ClockLabel
	 */
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			syncTime(syncedTimer.getTime());
		}
	}
	
	/**
	 * Updates time on Label
	 * @param time actual time
	 */
	public void syncTime(double time){
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				try{
					setText("Total time: "+Math.round(time));
				}
				catch(Exception e){
					//TODO:
					//Probably occurs if the event is invoked after exit
				}

			}

		});

	}
}
