
package org.xxxmathxxx.tddt.io;

import java.io.File;
import java.util.ArrayList;

import org.xxxmathxxx.tddt.logging.TDDTIOError;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.Profile;


/**
 * The Class ProfileReader.
 */
public class ProfileReader {

	/**
	 * Reads all profiles
	 * @return List of profiles
	 */
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
