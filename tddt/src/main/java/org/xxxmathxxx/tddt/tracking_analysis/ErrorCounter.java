/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

import java.util.HashMap;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

// TODO: Auto-generated Javadoc
/**
 * The Class Error.
 *
 * @author Tschebyscheff, 05.07.16
 */
public class ErrorCounter implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private HashMap<ErrorType,Integer> errors;
	
	/**
	 * Creates new ErrorCounter
	 */
	public ErrorCounter(){
		this.errors = new HashMap<ErrorType,Integer>();
	}

	/**
	 * Logs infos to file
	 */
	public void log() {
		for (ErrorType t : errors.keySet()){
			TDDTLogManager.getInstance().logMessage("You made "+errors.get(t)+" errors of the type: "+t.toString());

		}
	}

	/**
	 * Adds a new Error to the counter
	 * @param type
	 */
	public void addError(ErrorType type) {
		//init if necessary
		if (!errors.containsKey(type)){
			errors.put(type, 0);
		}
		errors.put(type,errors.get(type)+1);
	}

	/**
	 * Adds Testing error
	 * @param type
	 * @param numberOfFailedTests
	 */
	public void addError(ErrorType type, int numberOfFailedTests) {
		for (int i = 0; i < numberOfFailedTests; i++){
			addError(type);
		}
	}

	/**
	 * Returns number of errors
	 * @param type
	 * @return number of errors
	 */
	public Integer getErrorCount(ErrorType type) {
		if (errors.containsKey(type)){
			return errors.get(type);
		}
		return 0;
	}

}
