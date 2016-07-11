/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

// TODO: Auto-generated Javadoc
/**
 * The Class Error.
 *
 * @author Tschebyscheff, 05.07.16
 */
public class ErrorCounter {
	
	/** The total Error. Combines all different ErrorTypes. */
	public int totalError = 0;
	
	/** The semantic error. */
	public int semanticError = 0;
	
	/** The syntax error. */
	public int syntaxError = 0;
	
	/** The not initialized error. */
	public int notInitializedError = 0;
	
	/** The cannot find error. */
	public int cannotFindError = 0;
	
	/** The return type error. */
	public int returnTypeError = 0;
	
	/** The expected error. */
	public int expectedError = 0;
	
	/** The test failure. */
	public int testFailure = 0;

}
