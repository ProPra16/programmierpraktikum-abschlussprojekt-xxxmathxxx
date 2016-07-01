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
	private Label profile;
	private Label loggedInAs;
	private Button newTask;
	private Button statistics;
	private Button notYou;
	
	private static int xSize = 768;
	private static int ySize = 384;
	
	public ExistingProfileMenu() {
		
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		self = this;
		self.setTitle("TDDT - Main Menu");
		
		infoText = new Label("You are almost ready to go!");
		infoText.setPrefSize(240, 32);
		infoText.relocate((xSize/2)-60,70);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		loggedInAs = new Label("Logged in as:");
		loggedInAs.setPrefSize(280, 32);
		loggedInAs.relocate((xSize/2)-280,100);
		loggedInAs.setFont(new Font("Times New Roman", 18));
		pane.getChildren().add(loggedInAs);
		
		profile = new Label("Aranior");
		profile.setPrefSize(280, 32);
		profile.relocate((xSize/2)-265,140);
		profile.setFont(new Font("Times New Roman", 20));
		profile.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(profile);
		
		newTask = new Button("Start a new Exercise!");
		newTask.setPrefSize(150, 50);
		newTask.relocate(xSize/2-20,ySize - 240);
		newTask.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newTask);
		
		notYou = new Button("Not you?");
		notYou.setPrefSize(100, 30);
		notYou.relocate(xSize/2-282,ySize - 195);
		notYou.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(notYou);
		
		statistics = new Button("Statistics");
		statistics.setPrefSize(150, 50);
		statistics.relocate(xSize/2-20,ySize - 170);
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
				
			}
			if (event.getSource() == statistics){
				WindowManager.createStatistics(self).show();
			}
			if (event.getSource() == notYou){
				close();
				WindowManager.createStartupMenu().show();
			}
		}
	}
}
