package org.xxxmathxxx.tddt.profile;

import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;


/**Class that handles the creation of profiles for the NewProfileMenu
 * @author xxxMathxxx 2016
 * 
 */
public class ProfileCreator {

	/**
	 * Shows an error message to the User if he did not enter a name for the new profile.
	 */
	public static void profileCreationError(){	
		String header = "You haven't entered a name yet!";
		String text = "In order to proceed, you need to enter a name for your profile!";
		AlertMessenger.showErrorMessage(header, text);
	}
	
	/**
	 * Shows an error message to the User if he enters a name which is already in use.
	 */
	public static void nameTakenError(){
		String header = "This name is already taken!";
		String text = "You must not use a name, which another profile already uses!";
		AlertMessenger.showErrorMessage(header, text);
	}
	
	/**
	 * Saves the new profile to the hard drive and gives a confirmation message.
	 * @param newProfile The profile that should be created
	 * @param name The name of the profile
	 */
	public static void createProfile(Profile newProfile, String name){
		TDDTLogManager.getInstance().logMessage("Welcome " + name + "!");
		TDDTLogManager.getInstance().logMessage("New Profile has been created!");
        newProfile.saveProfileToFile();
		TDDTLogManager.getInstance().logMessage("Profile saved!");
	}
}

