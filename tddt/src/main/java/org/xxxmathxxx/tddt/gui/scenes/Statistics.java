package org.xxxmathxxx.tddt.gui.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;


import org.xxxmathxxx.tddt.gui.StatisticsPane;


public class Statistics extends Scene {

		
	
	private StatisticsPane statPane;
	
	
	public Statistics(Pane pane){
		super(pane);
		
		statPane = new StatisticsPane();
		pane.getChildren().add(statPane);
				
	}
}
