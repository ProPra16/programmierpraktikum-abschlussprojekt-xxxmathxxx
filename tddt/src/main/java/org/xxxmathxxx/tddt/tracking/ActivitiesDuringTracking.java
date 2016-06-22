/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author Tschebyscheff, 22.06.16
 * class for gathering actions during stages
 * TODO : add more activities
 *
 */
public class ActivitiesDuringTracking {

	int keystrokes = 0;
	boolean enableTracking = false;
	
	
	/*
	 * enables tracking
	 */
	public void enableTracking(){
		enableTracking = true;
	}
	/*
	 * disables tracking
	 */
	public void disableTracking(){
		enableTracking = false;
	}
	
	/*
	 * adding one keystroke
	 */
	public void addKeystroke(){
		keystrokes++;
	}
	
	/*
	 * returns made keystrokes
	 */
	public int getKeystrokes(){
		return keystrokes;
	}
	
	/*
	 * 
	 */
	
	
	
	
	
	
}
