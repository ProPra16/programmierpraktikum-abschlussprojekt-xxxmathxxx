package org.xxxmathxxx.tddt.gui.scenes;

import java.io.File;

import org.xxxmathxxx.tddt.errors.TDDTIOError;
import org.xxxmathxxx.tddt.gui.GraphicsHelper;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.gui.WindowManager.MenuType;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class NewProfileMenu extends Scene {	
	
	//TODO: DO THE TODO STUFF and clean this mess up
	
	//jfx elements
	private Label newProfile;
	private Button create;
	private Button picture;
	private TextField textField;
	private ImageView profilePic;
	private Button back;
	
	//stuff
	private String customImagePath = null;
	
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
		
		back = new Button("back tomainmenu");
		back.setPrefSize(175, 32);
		back.relocate((xSize/2)-83,ySize-40);
		back.setFont(new Font("Times New Roman", 20));
		back.setTextAlignment(TextAlignment.LEFT);
		back.addEventHandler(ActionEvent.ACTION,new menuButtonHandler());
		pane.getChildren().add(back);
		
		create = new Button("Confirm");
		create.setPrefSize(128, 32);
		create.relocate(xSize/2-64,ySize - 200);
		create.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(create);
		
		picture = new Button("Change picture");
		picture.setPrefSize(128, 32);
		picture.relocate(xSize/2-64,ySize - 250);
		picture.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(picture);
		
		textField = new TextField();
		textField.relocate((xSize/2)-73,70);
		textField.setPromptText("Enter a name");
		pane.getChildren().add(textField);
		
		profilePic = new ImageView(GraphicsHelper.defaultProfilePicture(128));
		profilePic.relocate(300, 170); //TODO: ordentlich ausrichten
		profilePic.setFitWidth(128);
		profilePic.setFitHeight(128);
		pane.getChildren().add(profilePic);
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource() == picture){
				String newPath = WindowManager.getInstance().startImageCropper();
				if (newPath != null){
					if (customImagePath != null){ //prevent storing of unused dumb images
						new File(customImagePath).delete();
					}
					customImagePath = newPath;
					profilePic.setImage(new Image("file:"+customImagePath));
				}
			}
			else if (event.getSource() == back){
				WindowManager.getInstance().showMenu(MenuType.STARTUP_MENU);
			}
			else if (event.getSource() == create){
				//TODO: MOVE SHITTY LOGIC OUT TO SEPERATE CLASSES, GUI SHOULD BE KEPT LIGHTWEIGHT!!!
				if ((textField.getText().isEmpty() || textField.getText() == null)) {
					Alert noTextDialog = new Alert(AlertType.ERROR);
					noTextDialog.setTitle("Info");
					noTextDialog.setHeaderText("You haven't entered a name yet!");
					String s ="In order to proceed you need to enter a name for your profile!";
					noTextDialog.setContentText(s);
					//the following line is pure BS but javafx is still buggy
					noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
					noTextDialog.showAndWait();
		        } 
					else
		        {
					//TODO: Check if a profile with this name already exists to prevent overwriting!
					//TODO: Move logic to separate class and remove from GUI
		            TDDTLogManager.getInstance().logMessage("Welcome " + textField.getText() + "!");
		            TDDTLogManager.getInstance().logMessage("New Profile has been created!");
		            
		            //TODO: Check profile name for sanity / weird symbols etc.
		            if(customImagePath == null){
		            	customImagePath = "graphics/unknownProfile.png";
		            }
		            
		            Profile newProfile = new Profile(textField.getText(), customImagePath);
		            
		            try {
						newProfile.saveProfileToFile();
					} catch (TDDTIOError e) {
						e.printStackTrace();
					}
		            
		            WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		            
		        }
			}
		}
	}
}

