
package org.xxxmathxxx.tddt.profile;

import java.util.ArrayList;

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
	
}
