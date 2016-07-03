package org.xxxmathxxx.tddt.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewProfileMenu extends Stage {
	
	private Pane pane;
	
	private Scene dialogScene;
	
	private NewProfileMenu self;
	
	private Label newProfile;
	private Button create;
	private Button picture;
	private TextField textField;
	
	private static int xSize = 512;
	private static int ySize = 256;
	
	public NewProfileMenu (Stage owner){
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		self = this;
		self.setTitle("TDDT - New Profile");
		
		newProfile = new Label("Create a new Profile!");
		newProfile.setPrefSize(175, 32);
		newProfile.relocate((xSize/2)-83,20);
		newProfile.setFont(new Font("Times New Roman", 20));
		newProfile.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(newProfile);
		
		create = new Button("Confirm");
		create.setPrefSize(128, 32);
		create.relocate(xSize/2-64,ySize - 100);
		create.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		create.setDisable(true);
		pane.getChildren().add(create);
		
		picture = new Button("Upload a Picture");
		picture.setPrefSize(128, 32);
		picture.relocate(xSize/2-64,ySize - 140);
		picture.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(picture);
		
		textField = new TextField();
		textField.relocate((xSize/2)-73,70);
		textField.setPromptText("Enter a name");
		pane.getChildren().add(textField);
		
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
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == picture){
				new ImageCropperTool("graphics/test.jpg",self).show(); //for testing purpose
				create.setDisable(false);
			}
			if (event.getSource() == create){
				if ((textField.getText().isEmpty() || textField.getText() == null)) {
					textField.setPromptText("Please enter a name!");
		        } else {
		            System.out.println("Willkommen " + textField.getText() + "!");
		            System.out.println("New Profile has been created!");
					WindowManager.getInstance().showMenu(WindowManager.MenuType.EXISTING_PROFILE);
					close();
		        }
			}
		}
	}
}

