package org.xxxmathxxx.tddt.profile;

/* 
 * @author : Tschebyscheff, 21.06.16
 * A class providing informations about users
 * provided informations are: Name, tracking-data, mastered exercises
 * 
 */

public class Profile extends ProfileStats{
	
	private String name;
	
	public Profile(String name){
		this.name = name;
	}
	
	public String getProfileName(){
		return name;
	}
}