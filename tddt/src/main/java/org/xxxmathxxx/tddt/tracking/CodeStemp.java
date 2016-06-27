/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Tschebyscheff, 25.06.16
 * CodeStemp saves data: Object Result saves Results during compiling and executing 
 * list saves all CompilationUnits, CompilationUnits can give you the code and class name
 * Date contains time informations
 *
 */
public class CodeStemp {

	private Result result;
	private ArrayList<Object> list = new ArrayList<Object>();
	private Date date;
	
	public CodeStemp(Result result, ArrayList<Object> list, Date date){
		this.result = result;
		this.list = list;
		this.date = date;
	}
	
	public Result getError(){
		return result;
	}
	
	public ArrayList<Object> getCompilationUnits(){
		return list;
	}
	
	public Date getDate(){
		return date;
	}
}
