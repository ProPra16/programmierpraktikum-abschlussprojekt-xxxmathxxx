package org.xxxmathxxx.tddt.profile;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

/**
 * @author xxxMathxxx 2016
 * Class that handles the creation of profiles for the NewProfileMenu
 */
public class ProfileCreator {

	/**
	 * Shows an error message to the User if he did not enter a name for the new profile.
	 */
	public static void profileCreationError(){	
		Alert noTextDialog = new Alert(AlertType.ERROR);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText("You haven't entered a name yet!");
		String s ="In order to proceed you need to enter a name for your profile!";
		noTextDialog.setContentText(s);
		//the following line is pure BS but javafx is still buggy
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();
	}
	
	/**
	 * Saves the new profile to the hard drive and gives a confirmation message.
	 * @param A profile which has to be created.
	 */
	public static void createProfile(Profile newProfile){
        TDDTLogManager.getInstance().logMessage("New Profile has been created!");
        newProfile.saveProfileToFile();
		TDDTLogManager.getInstance().logMessage("Profile saved!");
	}
}

