package org.xxxmathxxx.tddt.tracking_analysis;

//TODO: Add more markers for more detailed analysis

/**
 * @author xxxMathxxx
 * Defines error types
 */
public enum ErrorType implements java.io.Serializable{
	/**
	 * Refers to all errors that are not defined otherwise 
	 */
	ANY,
	/**
	 * Semantic error
	 */
	SEMANTIC,
	/**
	 * Syntaxerror
	 */
	SYNTAX,
	/**
	 * Missed to initialise a variable
	 */
	NOT_INITIALIZED,
	/**
	 * Cannot find symbol
	 */
	CANNOT_FIND,
	/**
	 * Missing return type
	 */
	RETURN_TYPE,
	/**
	 * programm expected wrong type
	 */
	EXPECTED_WRONG_TYPE,
	/**
	 * One or more test failed
	 */
	TEST_FAILURE
}
