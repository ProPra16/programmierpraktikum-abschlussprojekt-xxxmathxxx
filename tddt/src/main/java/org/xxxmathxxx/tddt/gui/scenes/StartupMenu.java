package org.xxxmathxxx.tddt.gui.scenes;

import java.util.ArrayList;

import org.xxxmathxxx.tddt.gui.ProfilePicker;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.profile.Profile;

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

public class StartupMenu extends Scene {

	
	private Label welcome;
	private Label chooseProfile;
	private Button newProfile;
	private Button existingProfile;
	
	public StartupMenu(Pane pane) {
		
		super(pane);
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		
		welcome = new Label("Welcome to TDDT!");
		welcome.setPrefSize(165, 32);
		welcome.relocate((xSize/2)-75,80);
		welcome.setFont(new Font("Times New Roman", 20));
		welcome.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(welcome);
		
		chooseProfile = new Label("Select a Profile to begin your journey.");
		chooseProfile.setPrefSize(280, 32);
		chooseProfile.relocate((xSize/2)-140,140);
		chooseProfile.setFont(new Font("Times New Roman", 18));
		chooseProfile.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(chooseProfile);
		
		newProfile = new Button("New Profile");
		newProfile.setPrefSize(128, 32);
		newProfile.relocate(xSize/2-64,ySize - 250);
		newProfile.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newProfile);
		
		existingProfile = new Button("Begin training");
		existingProfile.setPrefSize(128, 32);
		existingProfile.relocate(xSize/2-64,ySize - 200);
		existingProfile.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(existingProfile);
		
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
		
		ProfilePicker pp = new ProfilePicker(profileListTest);
		pp.relocate(xSize/2-300,ySize - 390);
		pp.setPrefSize(300, 100);
		pane.getChildren().add(pp);

	}
	
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newProfile){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.NEW_PROFILE);
			}
			else if (event.getSource() == existingProfile){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.EXISTING_PROFILE);
			}
		}
	}
	
}