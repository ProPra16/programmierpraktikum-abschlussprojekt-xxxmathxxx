package org.xxxmathxxx.tddt.gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Statistics extends Stage {
	
	private Pane pane;
	
	private Scene dialogScene;
	
	private Statistics self;
	
	private Label stats;
	
	private static int xSize = 300;
	private static int ySize = 400;
	
	public Statistics(Stage owner){
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		self = this;
		self.setTitle("TDDT - Statistics");
		
		stats = new Label("Statistics Overview");
		stats.setPrefSize(175, 32);
		stats.relocate((xSize/2)-78,35);
		stats.setFont(new Font("Times New Roman", 20));
		stats.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(stats);
		
		dialogScene = new Scene(pane);
		
		this.setScene(dialogScene);
		
		this.setMaxWidth(xSize);
		this.setMaxHeight(ySize);
		this.setMinWidth(xSize);
		this.setMinHeight(ySize);
		
		this.initOwner(owner);
		this.initModality(Modality.WINDOW_MODAL);
	
		this.setResizable(false);
		this.setAlwaysOnTop(true);
	}
}
