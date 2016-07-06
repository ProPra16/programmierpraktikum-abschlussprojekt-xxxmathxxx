package org.xxxmathxxx.tddt.profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xxxmathxxx.tddt.errors.TDDTIOError;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author xxxMathxxx 2016
 * Class that describes a user profile
 */
public class Profile {
	
	
	public ProfileStats profileStats = new ProfileStats();
	
	
	/**
	 * Internal String that contains the profile name
	 */
	private String name;
	
	/**
	 * Internal String that points to the profile picture if one exists
	 */
	private String profilePicPath;
	
	/**
	 * Internal Image object of the profile picture, is created when required
	 */
	private Image profilePic;
	
	/**
	 * Hash-Map that encodes the MedalState the user has for Exercises with a given Long ID
	 */
	private HashMap<Long,MedalState> achievements;
	
	/** Default constructor for Profile
	 * @param name The name of the profile
	 * @param profilePicPath The path to the profile pic
	 */
	public Profile(String name, String profilePicPath){
		this.name = name;
		this.profilePicPath = profilePicPath;
		this.achievements = new HashMap<Long,MedalState>();
	}
	
	/**Sets this user profiles name as a labels text, can be called by GUI-components
	 * @param label The label that should be filled
	 */
	public void showNameInJavaFXLabel(Label label){
		label.setText(name);
	}
	
	/**Sets this user profiles picture as an ImageViews Image, can be called by GUI-components. Creates the Image if required.
	 * @param iv The ImageView that should be filled
	 */
	public void showImageInJavaFXImageView(ImageView iv){
		//initialize/load picture if necessary
		if (this.profilePic == null){
			try{
				this.profilePic = new Image("file:"+profilePicPath);
			}
			catch(NullPointerException | IllegalArgumentException e){
				TDDTLogManager.getInstance().logMessage("Error loading the profile picture:");
				TDDTLogManager.getInstance().logMessage(e.getMessage());
			}
		}
		iv.setImage(profilePic);
	}
	
	/**Setter that stores a given MedalState for an exercise with a given ID
	 * @param exerciseID The exercise ID as Long
	 * @param newState The MedalState as MedalState
	 */
	public void setMedalState(long exerciseID, MedalState newState){
		achievements.put(exerciseID, newState);
	}
	
	/**Static function that attempts to load a profile from a file stored at the given path
	 * @param filePath The filepath as String
	 * @return Returns the Profile that is stored in the file
	 * @throws TDDTIOError
	 */
	public static Profile loadProfileFromFile(String filePath) throws TDDTIOError{

		File input = new File(filePath);
		Profile ret;
		TDDTLogManager.getInstance().logMessage("Trying to open profile @: "+filePath);

		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(input));
			String tmpName = in.readLine();
			String tmpImg = in.readLine();
			
			if (tmpName == null || tmpImg == null){
				in.close();
				throw new TDDTIOError("The file you are trying to open is not a valid savegame!");
			}
			
			ret = new Profile(tmpName,tmpImg);
			
			//fetch achievements
			
			String curLine = in.readLine();
			
			while (curLine != null){
				String[] split = curLine.split(":");
				ret.achievements.put(Long.parseLong(split[0]),MedalState.valueOf(split[1]));
				curLine = in.readLine();
			}

			in.close();
			
			return ret;
			
		} catch (IOException e) {
			throw new TDDTIOError("Could not open the specified file:");
		}
	}
	
	/**Saves the Profile instance to a file at the standart path, the file is created if it doesn't exist yet.
	 * @throws TDDTIOError
	 */
	public void saveProfileToFile() throws TDDTIOError{
		this.saveProfileToFile("profiles/"+name);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return name+":"+profilePicPath;
	}
	
	/**Getter for the MedalState, accesses the internal achievements map
	 * @param exerciseID The exercise ID as Long
	 * @return The MedalState for the given exercise if it exists, null otherwise (meaning the exercise was not yet worked on)
	 */
	public MedalState getMedalState(long exerciseID){
		if(achievements.containsKey(exerciseID)){
			return achievements.get(exerciseID);
		}
		return null;
	}
	
	public static ArrayList<Profile> getAllProfiles(){
		TDDTLogManager.getInstance().logMessage("Loading profiles from disk ...");
		File profileDir = new File("profiles");
		ArrayList<Profile> ret = new ArrayList<Profile>();
		for (File f: profileDir.listFiles()){
			if (!f.isDirectory()){
				try {
					ret.add(Profile.loadProfileFromFile(f.getAbsolutePath()));
					TDDTLogManager.getInstance().logMessage("Found profile: "+f.getName());
				} catch (TDDTIOError e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	public void saveProfileToFile(String fileName) {
		try {
			File output = new File(fileName);
			output.createNewFile();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(output));
			
			out.write(name+"\n");
			out.write(profilePicPath+"\n");
			
			//write achievements
			for (Map.Entry<Long, MedalState> entry : achievements.entrySet()) {
				out.write(entry.getKey()+":"+entry.getValue().toString()+"\n");
			}
			
			out.close();
			
		} catch (Exception e) {
			//TODO: SOMEONE ELSE DO THIS SHIT 
		}
	}
	

}