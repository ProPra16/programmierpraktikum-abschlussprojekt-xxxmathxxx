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
	
}
