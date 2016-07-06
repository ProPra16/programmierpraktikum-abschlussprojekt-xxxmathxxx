package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.gui.WindowManager.MenuType;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class NewProfileMenu extends Scene {	
	
	private Label newProfile;
	private Button create;
	private Button picture;
	private TextField textField;
	
	public NewProfileMenu (Pane pane){
		super(pane);
		//get width and height from main pane
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		newProfile = new Label("Create a new Profile!");
		newProfile.setPrefSize(175, 32);
		newProfile.relocate((xSize/2)-83,20);
		newProfile.setFont(new Font("Times New Roman", 20));
		newProfile.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(newProfile);
		
		create = new Button("Confirm");
		create.setPrefSize(128, 32);
		create.relocate(xSize/2-64,ySize - 200);
		create.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(create);
		
		picture = new Button("Upload a Picture");
		picture.setPrefSize(128, 32);
		picture.relocate(xSize/2-64,ySize - 250);
		picture.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(picture);
		
		textField = new TextField();
		textField.relocate((xSize/2)-73,70);
		textField.setPromptText("Enter a name");
		pane.getChildren().add(textField);
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == picture){
				WindowManager.getInstance().showMenu(MenuType.IMAGE_CROPPER);
				create.setDisable(false); //?
			}
			if (event.getSource() == create){
				if ((textField.getText().isEmpty() || textField.getText() == null)) {
					textField.setPromptText("Please enter a name!");
		        } else {
		            System.out.println("Welcome " + textField.getText() + "!");
		            System.out.println("New Profile has been created!");
		            WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		        }
			}
		}
	}
}

