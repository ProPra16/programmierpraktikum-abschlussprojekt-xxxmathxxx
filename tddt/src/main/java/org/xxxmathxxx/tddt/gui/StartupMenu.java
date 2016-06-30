package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class StartupMenu extends Stage {

	private Pane pane;
	
	private Scene dialogScene;
	
	private StartupMenu self;
	
	private Label welcome;
	private Label chooseProfile;
	private Button newProfile;
	private Button existingProfile;
	
	private static int xSize = 768;
	private static int ySize = 384;
	
	public StartupMenu() {
		
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		self = this;
		
		welcome = new Label("Welcome to TDDT!");
		welcome.setPrefSize(165, 32);
		welcome.relocate((xSize/2)-75,40);
		welcome.setFont(new Font("Times New Roman", 20));
		welcome.setTextAlignment(TextAlignment.LEFT);
		welcome.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(welcome);
		
		chooseProfile = new Label("Select a Profile to begin your journey.");
		chooseProfile.setPrefSize(280, 32);
		chooseProfile.relocate((xSize/2)-140,100);
		chooseProfile.setFont(new Font("Times New Roman", 18));
		chooseProfile.setTextAlignment(TextAlignment.LEFT);
		chooseProfile.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(chooseProfile);
		
		newProfile = new Button("New Profile");
		newProfile.setPrefSize(128, 32);
		newProfile.relocate(xSize/2-64,ySize - 200);
		newProfile.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newProfile);
		
		existingProfile = new Button("Use existing One");
		existingProfile.setPrefSize(128, 32);
		existingProfile.relocate(xSize/2-64,ySize - 160);
		existingProfile.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(existingProfile);
		
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
			if (event.getSource() == newProfile){
				WindowManager.createNewProfileMenu(self).show();
			}
			if (event.getSource() == existingProfile){
				new AchievementPopup(MedalState.BRONZE).show(self);
			}
		}
	}
	
}