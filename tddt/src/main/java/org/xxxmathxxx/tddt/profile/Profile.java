package org.xxxmathxxx.tddt.profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import org.xxxmathxxx.tddt.errors.TDDTIOError;
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
	
	
	public static Profile loadProfileFromFile(String filePath) throws TDDTIOError{
		
		File input = new File(filePath);
		Profile ret;
		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(input));
			
			if (in.lines().count() < 2){
				throw new TDDTIOError("The target file is not a valid savegame!");
			}
			
			String tmpName = in.readLine();
			String tmpImg = in.readLine();
			
			ret = new Profile(tmpName,tmpImg);
			
			//fetch achievements
			
			String curLine = in.readLine();
			
			while (curLine != null){
				//TODO: parse back achievements
			}
			
		} catch (Exception e) {
			throw new TDDTIOError("The target file doesn't exist or can't be read / is corrupted!");
		}
		return null;
	}
	
	public void saveProfileToFile(String filePath) throws TDDTIOError{
		
		File output = new File(filePath);
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(output));
			
			out.write(name+"\n");
			out.write(profilePicPath+"\n");
			
			//write achievements
			for (Map.Entry<Long, MedalState> entry : achievements.entrySet()) {
				out.write(entry.getKey()+" : "+entry.getValue().toString());
			}
			
		} catch (Exception e) {
			throw new TDDTIOError("The target file doesn't exist or can't be read / is corrupted!");
		}
	}
	

}