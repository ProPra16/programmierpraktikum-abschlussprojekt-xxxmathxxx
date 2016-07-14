package org.xxxmathxxx.tddt.gui.scenes;

import java.io.File;

import org.xxxmathxxx.tddt.gui.GraphicsHelper;
import org.xxxmathxxx.tddt.gui.RestrictedTextField;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.gui.WindowManager.MenuType;
import org.xxxmathxxx.tddt.profile.Profile;
import org.xxxmathxxx.tddt.profile.ProfileCreator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author xxxMathxxx 2016
 * The scene in which a new profile is created
 */
public class NewProfileMenu extends Scene {	
	
	//jfx elements
	private Label newProfile;
	private Button create;
	private Button picture;
	private TextField textField;
	private ImageView profilePic;
	private Button back;
	
	//stuff
	private String customImagePath = null;
	private String name = null;
	private String filePath = "profiles/";
	
	/**
	 * Constructor for this Menu
	 * @param pane See Scene
	 */
	public NewProfileMenu (Pane pane){
		super(pane);
		//get width and height from main pane
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		newProfile = new Label("Create a new Profile!");
		newProfile.setPrefSize(175, 32);
		newProfile.relocate(xSize-486,30);
		newProfile.setFont(new Font("Times New Roman", 20));
		newProfile.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(newProfile);
		
		back = new Button("Back");
		back.setPrefSize(150, 25);
		back.relocate(xSize-475,ySize-90);
		back.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(back);
		
		create = new Button("Confirm");
		create.setPrefSize(150, 32);
		create.relocate(xSize-475,ySize - 200);
		create.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(create);
		
		picture = new Button("Change picture");
		picture.setPrefSize(150, 32);
		picture.relocate(xSize-475,ySize - 250);
		picture.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(picture);
		
		textField = new RestrictedTextField();
		textField.relocate(xSize-475,80);
		textField.setPromptText("Enter a name");
		pane.getChildren().add(textField);
		
		profilePic = new ImageView(GraphicsHelper.defaultProfilePicture(128));
		profilePic.relocate(336, 170);
		profilePic.setFitWidth(128);
		profilePic.setFitHeight(128);
		pane.getChildren().add(profilePic);
	}
	
	/**
	 * Handler for the different buttons in this menu
	 */
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
					profilePic.setImage(new Image(customImagePath));
				}
			}
			else if (event.getSource() == back){
				WindowManager.getInstance().showMenu(MenuType.STARTUP_MENU);
			}
			else if (event.getSource() == create){
				if ((textField.getText().isEmpty() || textField.getText() == null)) {
					ProfileCreator.profileCreationError();
		        }
				else if(new File(filePath + textField.getText()).isFile()) {
					ProfileCreator.nameTakenError();
				}
				else
		        {
					name = textField.getText();
		            if(customImagePath == null){
		            	customImagePath = GraphicsHelper.getResourcePath("/graphics/unknownProfile.png");
		            }
		            
		            Profile newProfile = new Profile(name, customImagePath);
		            ProfileCreator.createProfile(newProfile,name);
		            
		            WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		            
		        }
			}
		}
	}
}

