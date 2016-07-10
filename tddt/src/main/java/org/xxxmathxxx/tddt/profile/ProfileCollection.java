
package org.xxxmathxxx.tddt.profile;

import java.io.File;
import java.util.ArrayList;

import org.xxxmathxxx.tddt.logging.TDDTIOError;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

/*Author: Tschebyscheff, 22.06.16
 * A class that contains a list of Profles
 */


/**
 * The Class ProfileCollection.
 */
public class ProfileCollection {


	/** The list of Profiles */
	private ArrayList<Object> list = new ArrayList<Object>();
	

	/**
	 * Adds the profile.
	 *
	 * @param profile the profile
	 */
	public void addProfile(Profile profile){
		list.add(profile);
	}
	
	/**
	 * Gets the profile.
	 *
	 * @param i the index
	 * @return the profile
	 */
	public Profile getProfile(int i){
		Profile profile = (Profile) list.get(i);
		return profile;
	}
	
	/**
	 * Delete profile.
	 *
	 * @param i the index
	 */
	public void deleteProfile(int i){
		list.remove(i);
	}
	
	/**
	 * Show stats.
	 */
	public void showStats(){
		
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
	
}
