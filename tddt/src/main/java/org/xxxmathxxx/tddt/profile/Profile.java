package org.xxxmathxxx.tddt.profile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.xxxmathxxx.tddt.logging.TDDTIOError;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * The Class Profile.
 *
 * @author xxxMathxxx 2016
 * Class that describes a user profile
 */
public class Profile {
	
	
	/** The profile stats. */
	public ProfileStats profileStats;
		
	/** Internal String that contains the profile name. */
	private String name;
	
	/** Internal String that points to the profile picture if one exists. */
	private String profilePicPath;
	
	/** Internal String that points to the stats object if one exists. */
	private String statsPath;
	
	/** Internal Image object of the profile picture, is created when required. */
	private Image profilePic;
	
	
	/**
	 *  Default constructor for Profile.
	 *
	 * @param name The name of the profile
	 * @param profilePicPath The path to the profile pic
	 */
	public Profile(String name, String profilePicPath){
		//assignments
		this.name = name;
		this.profilePicPath = profilePicPath;
		//init
		profileStats = new ProfileStats();
		
	}
	
	/**
	 * Sets this user profiles name as a labels text, can be called by GUI-components.
	 *
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
				this.profilePic = new Image(profilePicPath);
			}
			catch(NullPointerException | IllegalArgumentException e){
				TDDTLogManager.getInstance().logMessage("Error loading the profile picture:");
				TDDTLogManager.getInstance().logMessage(e.getMessage());
			}
		}
		iv.setImage(profilePic);
	}
	
	/**
	 * Setter that stores a given MedalState for an exercise with a given ID.
	 *
	 * @param exerciseID The exercise ID as Long
	 * @param newState The MedalState as MedalState
	 */
	public void setMedalState(long exerciseID, MedalState newState){
		this.profileStats.setMedalState(exerciseID, newState);
	}
	
	/**
	 * Static function that attempts to load a profile from a file stored at the given path.
	 *
	 * @param filePath The filepath as String
	 * @return Returns the Profile that is stored in the file
	 * @throws TDDTIOError A TDDT-IO error that is logged to the default log file
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
			String tmpStats = in.readLine();
			
			if (tmpName == null || tmpImg == null || tmpStats == null){
				in.close();
				throw new TDDTIOError("The file you are trying to open is not a valid savegame!");
			}
			
			ret = new Profile(tmpName,tmpImg);
			ret.statsPath = tmpStats;

			in.close();
			
			//read stats
			ObjectInput objectIn = new ObjectInputStream(new FileInputStream(tmpStats));
			ret.profileStats = (ProfileStats) objectIn.readObject();
			objectIn.close();
			
			return ret;
			
		} catch (IOException | ClassNotFoundException e) {
			throw new TDDTIOError("Could not open the specified file: "+e.getMessage());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return name+":"+profilePicPath;
	}
	
	/**
	 * Getter for the MedalState, accesses the internal achievements map.
	 *
	 * @param exerciseID The exercise ID as Long
	 * @return The MedalState for the given exercise if it exists, null otherwise (meaning the exercise was not yet worked on)
	 */
	public MedalState getMedalState(long exerciseID){
		return profileStats.getMedalState(exerciseID);
	}
	
	/**
	 * Save profile to file.
	 */
	public void saveProfileToFile(){
		saveProfileToFile("profiles/"+name);
	}

	/**
	 * Save profile to file.
	 *
	 * @param filePath the file path
	 */
	public void saveProfileToFile(String filePath) {
		try {	
			//write stats
			
			//generate statsPath if necessary (first save)
			if (statsPath == null){
				int i = 0;
				File statsOutput;
				while(true){
					statsOutput = new File("profiles/stats/"+i);
					if (!statsOutput.exists()){
						statsPath = "profiles/stats/"+i;
						break;
					}
					i++;
				}
			}
			
			File statsOutput = new File(statsPath);
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(statsOutput));
			objectOut.writeObject(this.profileStats);
			objectOut.close();
			
			//write base profile
			File output = new File(filePath);
			output.createNewFile();
			
			BufferedWriter out = new BufferedWriter(new FileWriter(output));
			
			out.write(name+"\n");
			out.write(profilePicPath+"\n");
			out.write(statsPath);
			out.close();		
			
		} catch (Exception e) {
			//TODO: SOMEONE ELSE DO THIS SHIT 
		}
	}

	

}