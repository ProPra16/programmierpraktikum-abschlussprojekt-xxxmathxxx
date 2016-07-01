package org.xxxmathxxx.tddt.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class ExistingProfileMenu extends Stage {

	private Pane pane;
	
	private Scene dialogScene;
	
	private ExistingProfileMenu self;
	
	private Label infoText;
	private Label loggedInAs;
	private Button newTask;
	private Button statistics;
	
	private static int xSize = 768;
	private static int ySize = 384;
	
	public ExistingProfileMenu() {
		
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		self = this;
		
		infoText = new Label("You are almost ready to go.");
		infoText.setPrefSize(165, 32);
		infoText.relocate((xSize/2)-75,40);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		loggedInAs = new Label("Logged in as:");
		loggedInAs.setPrefSize(280, 32);
		loggedInAs.relocate((xSize/2)-140,100);
		loggedInAs.setFont(new Font("Times New Roman", 18));
		loggedInAs.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(loggedInAs);
		
		newTask = new Button("Start a new Exercise!");
		newTask.setPrefSize(128, 32);
		newTask.relocate(xSize/2-64,ySize - 200);
		newTask.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newTask);
		
		statistics = new Button("Statistics");
		statistics.setPrefSize(128, 32);
		statistics.relocate(xSize/2-64,ySize - 160);
		statistics.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(statistics);
		
		dialogScene = new Scene(pane);
		
		this.setScene(dialogScene);
		//this.initStyle(StageStyle.UNDECORATED);
		
		this.setMaxWidth(xSize);
		this.setMaxHeight(ySize);
		this.setMinWidth(xSize);
		this.setMinHeight(ySize);
	
		this.setResizable(false);
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newTask){
				WindowManager.createNewProfileMenu(self).show();
			}
			if (event.getSource() == statistics){
				
			}
		}
	}
}
