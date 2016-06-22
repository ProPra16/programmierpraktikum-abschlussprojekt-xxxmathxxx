package org.xxxmathxxx.tddt.profile;

import org.xxxmathxxx.tddt.tracking.StageGreenTracker;

/* 
 * @author : Tschebyscheff, 21.06.16
 * A class providing informations about users
 * provided informations are: Name, statistics, mastered exercises
 * 
 * TODO: showStats has to be managed
 */

public class Profile extends ProfileStats{
	
	private String name;
	
	public Profile(String name){
		this.name = name;
	}
	
	public String getProfileName(){
		return name;
	}
	
	/*
	 * A method about displaying stats
	 */
	public void showStats(){
		
	}
	

	
	
	
}