package org.xxxmathxxx.tddt.profile;

import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;


/**
 * @author xxxMathxxx 2016
 * Class that handles the creation of profiles for the NewProfileMenu
 */
public class ProfileCreator {

	/**
	 * Shows an error message to the User if he did not enter a name for the new profile.
	 */
	public static void profileCreationError(){	
		String header = "You haven't entered a name yet!";
		String text = "In order to proceed you need to enter a name for your profile!";
		AlertMessenger.showErrorMessage(header, text);
	}
	
	/**
	 * Saves the new profile to the hard drive and gives a confirmation message.
	 * @param A profile which has to be created.
	 */
	public static void createProfile(Profile newProfile, String name){
		TDDTLogManager.getInstance().logMessage("Welcome " + name + "!");
		TDDTLogManager.getInstance().logMessage("New Profile has been created!");
        newProfile.saveProfileToFile();
		TDDTLogManager.getInstance().logMessage("Profile saved!");
	}
}
