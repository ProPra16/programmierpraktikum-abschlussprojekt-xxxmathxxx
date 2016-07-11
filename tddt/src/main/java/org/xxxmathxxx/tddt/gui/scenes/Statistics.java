package org.xxxmathxxx.tddt.gui.scenes;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;


import org.xxxmathxxx.tddt.gui.StatisticsPane;

/**
 * @author xxxMathxxx 2016
 * The scene which shows the statistics
 */
public class Statistics extends Scene {

		
	
	private StatisticsPane statPane;
	
	/**
	 * Constructor for this Menu
	 * @param pane See Scene
	 */
	public Statistics(Pane pane){
		super(pane);
		
		statPane = new StatisticsPane();
		pane.getChildren().add(statPane);
				
	}
}
