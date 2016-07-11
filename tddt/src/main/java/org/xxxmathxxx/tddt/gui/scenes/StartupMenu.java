package org.xxxmathxxx.tddt.gui.scenes;


import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.gui.ProfilePicker;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.profile.ProfileCollection;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author xxxMathxxx 2016
 * The scene which shows up as soon as the program starts
 */
public class StartupMenu extends Scene {

	
	private Label welcome;
	private Label chooseProfile;
	private Button newProfile;
	private Button existingProfile;
	private ProfilePicker pp;
	
	/**
	 * Constructor for this Menu
	 * @param pane See Scene
	 */
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
		
		pp = new ProfilePicker(ProfileCollection.getAllProfiles());
		pp.relocate(250,ySize - 390);
		pp.setPrefSize(300, 100);
		pane.getChildren().add(pp);

	}
	
	/**
	 * Handler for the different buttons in this menu
	 */
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newProfile){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.NEW_PROFILE);
			}
			else if (event.getSource() == existingProfile){
				if (pp.getSelection() != null){
					//start new thread
					TDDT.currentThread = new TDDTThread(pp.getSelection());
					WindowManager.getInstance().showMenu(WindowManager.MenuType.EXISTING_PROFILE);					
				}
				else{
					AlertMessenger.showErrorMessage("No profile available", "Please create a profile to start practice!");
				}
			}
		}
	}
	
}