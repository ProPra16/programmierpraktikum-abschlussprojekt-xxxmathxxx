package org.xxxmathxxx.tddt.profile;

import java.util.HashMap;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** 
 * TODO: Add streamlined header
 * A class providing informations about users
 * provided informations are: Name, tracking-data, mastered exercises
 */

public class Profile extends ProfileStats{
	
	private String name;
	private String profilePicPath;
	private Image profilePic;
	private HashMap<Long,MedalState> achievements;
	
	public Profile(String name, String profilePicPath){
		this.name = name;
		this.profilePicPath = profilePicPath;
		this.achievements = new HashMap<Long,MedalState>();
	}
	
	public void showNameInJavaFXLabel(Label label){
		label.setText(name);
	}
	
	public void showImageInJavaFXImageView(ImageView iv){
		//initialize/load picture if necessary
		if (this.profilePic == null){
			try{
				this.profilePic = new Image(profilePicPath);
			}
			catch(NullPointerException | IllegalArgumentException e){
				TDDTLogManager.getInstance().logMessage("Error loading the profile picture:");
				TDDTLogManager.getInstance().logMessage(e.getMessage());
			}
		}
		iv.setImage(profilePic);
	}
	
	public void setMedalState(long exerciseID, MedalState newState){
		achievements.put(exerciseID, newState);
	}
	
	
	public static Profile loadProfileFromFile(String filePath){
		//TODO
		return null;
	}
	
	public void saveProfileToFile(String filePath){
		//TODO
	}
	

}