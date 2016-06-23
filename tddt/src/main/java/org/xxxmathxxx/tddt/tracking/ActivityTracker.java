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
public abstract class ActivityTracker {

	private int keystrokes = 0;
	private boolean enableTracking = false;
	
	
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
		if(enableTracking == true)
			keystrokes++;
	}
	
	/*
	 * returns made keystrokes
	 */
	public int getKeystrokes(){
		return keystrokes;
	}
	
}
