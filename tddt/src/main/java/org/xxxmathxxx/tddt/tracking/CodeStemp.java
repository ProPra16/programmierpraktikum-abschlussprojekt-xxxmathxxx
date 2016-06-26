/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Tschebyscheff, 25.06.16
 * CodeStemp saves data: Object Error saves possible Errors during compiling and executing 
 * list saves all CompilationUnits, CompilationUnits can give you the code and class name
 *
 */
public class CodeStemp {

	private Error error;
	private ArrayList<Object> list = new ArrayList<Object>();
	private Date date;
	
	public CodeStemp(Error error, ArrayList<Object> list, Date date){
		this.error = error;
		this.list = list;
		this.date = date;
	}
	
	public Error getError(){
		return error;
	}
	
	public ArrayList<Object> getCompilationUnits(){
		return list;
	}
	
	public Date getDate(){
		return date;
	}
}
