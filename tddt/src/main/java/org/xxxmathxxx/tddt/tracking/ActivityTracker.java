/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * The Class ActivityTracker.
 *
 * @author Tschebyscheff, 22.06.16
 * class for gathering actions during stages
 * TODO : add more activities
 */
public abstract class ActivityTracker {

	/** The keystrokes. */
	private int keystrokes = 0;
	
	/**
	 * Adds a keystroke.
	 */
	public void addKeystroke(){
			keystrokes++;
	}
	
	/**
	 * Gets the keystrokes.
	 *
	 * @return the keystrokes
	 */
	public int getKeystrokes(){
		return keystrokes;
	}
	
}
