package org.xxxmathxxx.tddt.profile;

import java.util.ArrayList;

/*Author: Tschebyscheff, 22.06.16
 * A class that contains a list of Profles
 */


public class ProfileCollection {


	private ArrayList<Object> list = new ArrayList<Object>();
	public ProfileCollection(){
	
	}
	
	/*
	 * method add a profile to the collection
	 */
	public void addProfile(Profile profile){
		list.add(profile);
	}
	
	/*
	 * method gets Profile at the Position i
	 */
	public Profile getProfile(int i){
		Profile profile = (Profile) list.get(i);
		return profile;
	}
	
	/*
	 * method deletes profile at position i
	 */
	public void deleteProfile(int i){
		list.remove(i);
	}
	
	/*
	 * method give information about stats in relation to other profiles in collection
	 */
	public void showStats(){
		
	}
	
}
