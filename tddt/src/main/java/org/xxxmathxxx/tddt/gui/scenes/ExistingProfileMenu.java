package org.xxxmathxxx.tddt.gui.scenes;

import java.util.ArrayList;

//import org.xxxmathxxx.tddt.gui.AchievementPopup;
import org.xxxmathxxx.tddt.gui.ProfilePicker;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.profile.MedalState;
import org.xxxmathxxx.tddt.profile.Profile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ExistingProfileMenu extends Scene {
	
	private Label infoText;
	private Label loggedInAs;
	private Button newTask;
	private Button statistics;
	private Button notYou;
	
	public ExistingProfileMenu(Pane pane) {
		
		super(pane);
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		infoText = new Label("You are almost ready to go!");
		infoText.setPrefSize(240, 32);
		infoText.relocate((xSize/2)+10,100);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		loggedInAs = new Label("Logged in as:");
		loggedInAs.setPrefSize(280, 32);
		loggedInAs.relocate((xSize/2)-280,100);
		loggedInAs.setFont(new Font("Times New Roman", 18));
		pane.getChildren().add(loggedInAs);
		
		newTask = new Button("Start a new Exercise!");
		newTask.setPrefSize(150, 50);
		newTask.relocate(xSize/2+45,ySize - 430);
		newTask.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newTask);
		
		notYou = new Button("Not you?");
		notYou.setPrefSize(100, 30);
		notYou.relocate(xSize/2-282,ySize - 255);
		notYou.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(notYou);
		
		statistics = new Button("Statistics");
		statistics.setPrefSize(150, 50);
		statistics.relocate(xSize/2+45,ySize - 360);
		statistics.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(statistics);
				
		//DEBUG STUFF END
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newTask){
				System.out.println("Exercises not yet implemented!");
			}
			if (event.getSource() == statistics){
				//for testing
				WindowManager.getInstance().createAchievementPopup(MedalState.GOLD);
			}
			if (event.getSource() == notYou){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
			}
		}
	}
}
