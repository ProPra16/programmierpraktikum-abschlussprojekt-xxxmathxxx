/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.xxxmathxxx.tddt.timer.BasicTimer;


/**The ActivityTracker contains CodeStamps, a basic timer and a keystroke counter, all associated with one CodeStage.
 * It is used to collect information during runtime.
 * @author xxxMathxxx 2016
 *
 */
public class ActivityTracker{
	
	/** The code stamp collection. */
	public CodeStampCollection codeStampCollection = new CodeStampCollection(); //100% sure that this belongs into a time trackerManager??????

	/** This timer tracks how much time is spend in this particular stage */
	private BasicTimer timer;
	
	/** The keystrokes that occured in the associated stage as Integer */
	private int keystrokes = 0;
	
	/**Default constructor
	 * Instantiates a new ActicityTracker.
	 */
	public ActivityTracker(){
		timer = new BasicTimer();
	}

	/**Sets the timer state
	 * @param state The state to which the timer should be set
	 */
	public void setTimerActive(boolean state) {
		this.timer.setActive(state);
	}

	/**Simple getter for the time as it should not be directly modified
	 * @return The elapsed time as Double
	 */
	public double getElapsedTime() {
		return this.timer.getTime();
	}

	/**Getter for the keystrokes as they shouldn't be modified directly
	 * @return the keystrokes as Integer
	 */
	public int getKeystrokes() {
		return keystrokes;
	}

	/**
	 * Adds a keystroke to the counter
	 */
	public void addKeystroke() {
		this.keystrokes++;;
	}
}
