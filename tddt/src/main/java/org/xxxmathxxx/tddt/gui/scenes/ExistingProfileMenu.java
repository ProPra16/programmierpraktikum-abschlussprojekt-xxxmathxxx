package org.xxxmathxxx.tddt.gui.scenes;


import org.xxxmathxxx.tddt.core.TDDTThread;
//import org.xxxmathxxx.tddt.gui.AchievementPopup;
import org.xxxmathxxx.tddt.gui.WindowManager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**The scene after you picked a profile
 * @author xxxMathxxx 2016
 *	
 */
public class ExistingProfileMenu extends Scene {
	
	private Label infoText;
	private Label loggedInAs;
	private ImageView profilePic;
	private Label profileName;
	private Button newTask;
	private Button statistics;
	private Button notYou;
	
	/**
	 * Constructor for this Menu
	 * @param pane See Scene
	 */
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
		
		profilePic = new ImageView();
		profilePic.setFitWidth(128);
		profilePic.setFitHeight(128);
		
		TDDTThread.getInstance().getUserProfile().showImageInJavaFXImageView(profilePic);

		profilePic.relocate((xSize/2)-280,146);
		pane.getChildren().add(profilePic);
		
		loggedInAs = new Label("Logged in as:");
		loggedInAs.setPrefSize(280, 32);
		loggedInAs.relocate((xSize/2)-280,100);
		loggedInAs.setFont(new Font("Times New Roman", 18));
		pane.getChildren().add(loggedInAs);
		
		profileName = new Label();
		TDDTThread.getInstance().getUserProfile().showNameInJavaFXLabel(profileName);
		profileName.setPrefSize(280, 32);
		profileName.relocate((xSize/2)-280,290);
		profileName.setFont(new Font("Times New Roman", 18));
		pane.getChildren().add(profileName);
		
		newTask = new Button("Start a new Exercise!");
		newTask.setPrefSize(150, 50);
		newTask.relocate(xSize/2+45,146);
		newTask.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(newTask);
		
		notYou = new Button("Back");
		notYou.setPrefSize(150, 25);
		notYou.relocate(xSize-200,ySize-70);
		notYou.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(notYou);
		
		statistics = new Button("Statistics");
		statistics.setPrefSize(150, 50);
		statistics.relocate(xSize/2+45,224);
		statistics.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(statistics);
				
		//DEBUG STUFF END
	}
	
	/**
	 * Handler for the different buttons
	 */
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == newTask){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.EXERCISEPICKER);
			}
			if (event.getSource() == statistics){
				//for testing
				WindowManager.getInstance().showMenu(WindowManager.MenuType.STATISTICS);
			}
			if (event.getSource() == notYou){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
			}
		}
	}
}
