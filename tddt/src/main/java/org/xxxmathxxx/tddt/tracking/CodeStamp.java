/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;

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
	
	
	/**
	 * Instantiates a new code stamp.
	 *
	 * @param result the result of compiling and executing
	 * @param list the CompilationUnit list
	 * @param date the date
	 */
	public CodeStamp(Result result, ArrayList<Object> list){
		this.result = result;
		this.list = list;
	}
	
	/**
	 * Gets the compiler errors.
	 *
	 * @return the compiler errors
	 */
	public String getCompilerErrors(){
		String ret = "";
		ret = result.getCompilerErrors(list);
		return ret;
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
	 * Generates a CodeStamp.
	 *
	 * @param compiler the compiler
	 * @return the code stamp
	 */
	public static CodeStamp generateCodeStamp(JavaStringCompiler compiler){	
		compiler.compileAndRunTests();
		
		ArrayList<Object> list = new ArrayList<Object>();
		Set<String> set = compiler.getAllCompilationUnitNames();
		String[] s = new String[set.size()];
		Iterator<String> iterator = set.iterator();
		CompilationUnit compilationUnit = null;
		int i = 0;
		
		CompilerResult compilerResult = compiler.getCompilerResult();
		TestResult testResult = compiler.getTestResult();
		
		Result result = new Result(testResult,compilerResult);

		while(iterator.hasNext()){
			s[i] = (String) iterator.next();
			compilationUnit = compiler.getCompilationUnitByName(s[i++]);	
			list.add(compilationUnit);
		}
		
		
		CodeStamp codeStamp = new CodeStamp(result, list);
		return codeStamp;
	}

}
