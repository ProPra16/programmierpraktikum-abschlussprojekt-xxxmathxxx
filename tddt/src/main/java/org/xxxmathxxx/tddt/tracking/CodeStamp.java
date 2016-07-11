/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class CodeStamp.
 *
 * @author Tschebyscheff, 25.06.16
 * CodeStemp saves data: Object Result saves Results during compiling and executing 
 * list saves all CompilationUnits, CompilationUnits can give you the code and class name
 * Date contains time informations
 */
public class CodeStamp {

	/** The result. */
	public Result result;
	
	/** The list of CompilationUnits. */
	private ArrayList<Object> list = new ArrayList<Object>();
	
	/** The date. */
	private Date date;
	
	/**
	 * Instantiates a new code stamp.
	 *
	 * @param result the result of compiling and executing
	 * @param list the CompilationUnit list
	 * @param date the date
	 */
	public CodeStamp(Result result, ArrayList<Object> list, Date date){
		this.result = result;
		this.list = list;
		this.date = date;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result of compiling and executing
	 */
	public Result getResult(){
		return result;
	}
	
	/**
	 * Gets the compilation units.
	 *
	 * @return the compilation units
	 */
	public ArrayList<Object> getCompilationUnits(){
		return list;
	}
	
	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate(){
		return date;
	}
}
