/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

/**
 * @author guvon, 23.06.16
 * class for gathering all kinds of information during tracking process in stage refactor
 * informations are kinds of: Errors, Activities(writing) and ...
 *
 * TODO: add more informations, add methods in context of errors, type of errors
 *
 */
public class StageRefactorTracker extends TimeTracker {


	private Error error = new Error();
	
	public StageRefactorTracker(){
		
	}

}
