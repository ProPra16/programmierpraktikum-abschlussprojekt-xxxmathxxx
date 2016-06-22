/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author Tschebyscheff
 * class for classifing Errors, (there will be different types of Errors)
 * TODO : add type of Errors
 */
public class Error {

	int countErrors = 0;
	
	/*
	 * adding 1 general Error
	 */
	public void addError(){
		countErrors++;
	}
	
	/*
	 * returns general Errors
	 */
	public int getErrors(){
		return countErrors;
	}
}
