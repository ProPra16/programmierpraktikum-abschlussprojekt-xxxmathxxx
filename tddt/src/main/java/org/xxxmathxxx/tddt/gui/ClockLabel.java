package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class ClockLabel extends Label{
	
	private double totalTime;
	
	public ClockLabel(double totalTime){
		this.totalTime = totalTime;
		this.setText("TEST");
	}
	
	public void syncTime(double time){
		this.setText(""+time);
		if (time > 4d/5d*totalTime){
			this.setTextFill(Color.RED);
		}
		else{
			this.setTextFill(Color.BLACK);
		}
	}
}
