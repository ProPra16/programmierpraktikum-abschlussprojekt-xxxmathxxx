package org.xxxmathxxx.tddt.gui;

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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewProfileMenu extends Stage {
	
	private Pane pane;
	
	private Scene dialogScene;
	
	private Label newProfile;
	private Label name;
	private Button create;
	private Button picture;
	
	private static int xSize = 512;
	private static int ySize = 256;
	
	public NewProfileMenu (Stage owner){
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		newProfile = new Label("Create a new Profile!");
		newProfile.setPrefSize(175, 32);
		newProfile.relocate((xSize/2)-75,20);
		newProfile.setFont(new Font("Times New Roman", 20));
		newProfile.setTextAlignment(TextAlignment.LEFT);
		newProfile.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(newProfile);
		
		name = new Label("Name:");
		name.setPrefSize(280, 32);
		name.relocate((xSize/2)-140,70);
		name.setFont(new Font("Times New Roman", 18));
		name.setTextAlignment(TextAlignment.LEFT);
		name.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(name);
		
		create = new Button("Confirm");
		create.setPrefSize(128, 32);
		create.relocate(xSize/2-64,ySize - 100);
		create.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(create);
		
		picture = new Button("Upload a Picture");
		picture.setPrefSize(128, 32);
		picture.relocate(xSize/2-64,ySize - 140);
		picture.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(picture);
		
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
			if (event.getSource() == create){
				System.out.println("New Profile has been created!");
				close();
			}
		}
	}
}

