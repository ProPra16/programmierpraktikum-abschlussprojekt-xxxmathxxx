package org.xxxmathxxx.tddt.babysteps;

import java.util.Timer;
import java.util.TimerTask;

public class TaskTimer {
	
	private Timer timer;
	private int elapsedTime; 
	
	public TaskTimer(){
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimeUpdateTask(),0 , 1000);
	}
	
	private class TimeUpdateTask extends TimerTask{
		@Override
		public void run() {
			elapsedTime ++;
		}
	}
	
	public String getTimeInSecondsAsString(){
		return ""+elapsedTime;
	}
	
}
