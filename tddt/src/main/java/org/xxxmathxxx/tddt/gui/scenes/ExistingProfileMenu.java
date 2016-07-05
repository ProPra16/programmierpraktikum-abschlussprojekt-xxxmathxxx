package org.xxxmathxxx.tddt.gui.scenes;

import java.util.ArrayList;

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
	private Label profile;
	private Label loggedInAs;
	private Button newTask;
	private Button statistics;
	private Button notYou;
	
	private static int xSize = 768;
	private static int ySize = 384;
	
	public ExistingProfileMenu(Pane pane) {
		
		super(pane);
		
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
		profile.relocate((xSize/2)-265,ySize-140);
		profile.setFont(new Font("Times New Roman", 20));
		profile.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(profile);
		
		newTask = new Button("Start a new Exercise!");
		newTask.setPrefSize(150, 50);
		newTask.relocate(xSize/2-20,ySize - 180);
		newTask.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newTask);
		
		notYou = new Button("Not you?");
		notYou.setPrefSize(100, 30);
		notYou.relocate(xSize/2-282,ySize - 105);
		notYou.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(notYou);
		
		statistics = new Button("Statistics");
		statistics.setPrefSize(150, 50);
		statistics.relocate(xSize/2-20,ySize - 170);
		statistics.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(statistics);
		
		//DEBUG STUFF
		Profile dino1 = new Profile("dino1","file:profiles/graphics/test1.png");
		Profile dino2 = new Profile("dino2","file:profiles/graphics/test2.png");
		Profile dino3 = new Profile("dino3","file:profiles/graphics/test3.png");
		Profile dino4 = new Profile("dino4","file:profiles/graphics/test4.png");
		Profile dino5 = new Profile("dino5","file:profiles/graphics/test1.png");

		ArrayList<Profile> profileListTest = new ArrayList<Profile>();
		profileListTest.add(dino1);
		profileListTest.add(dino2);
		profileListTest.add(dino3);
		profileListTest.add(dino4);
		profileListTest.add(dino5);
		
		
		//DEBUG STUFF END
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newTask){
				
			}
			if (event.getSource() == statistics){
				
			}
			if (event.getSource() == notYou){
				
			}
		}
	}
}
