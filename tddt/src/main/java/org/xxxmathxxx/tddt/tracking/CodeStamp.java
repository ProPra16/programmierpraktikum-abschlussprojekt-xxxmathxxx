/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.Iterator;
import java.util.Set;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

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
	private Result result;
	
	/** The list of CompilationUnits. */
	private CompilationUnit[] cUnits; //array is the format required by VirtualKataLib so why convert all the time
	
	
	/**
	 * Instantiates a new code stamp.
	 *
	 * @param result the result of compiling and executing
	 * @param list the CompilationUnit list
	 * @param date the date
	 */
	public CodeStamp(Result result, CompilationUnit[] cUnits){
		this.result = result;
		this.cUnits = cUnits;
	}

	
	/**
	 * Gets the compilation units.
	 *
	 * @return the compilation units
	 */
	public CompilationUnit[] getCompilationUnits(){
		return cUnits;
	}
	
	/**
	 * Generates a CodeStamp.
	 *
	 * @param compiler the compiler
	 * @return the code stamp
	 */
	public static CodeStamp generateCodeStamp(JavaStringCompiler compiler){	
		TDDTLogManager.getInstance().logMessage("Generating new Code-Stamp");
		compiler.compileAndRunTests();
		
		Set<String> set = compiler.getAllCompilationUnitNames();
		Iterator<String> iterator = set.iterator();
		CompilationUnit compilationUnit = null;
		int i = 0;
		CompilationUnit[] cUnits = new CompilationUnit[set.size()];

		CompilerResult compilerResult = compiler.getCompilerResult();
		TestResult testResult = compiler.getTestResult();
		
		Result result = new Result(testResult,compilerResult);

		while(iterator.hasNext()){
			String nextName = iterator.next();
			TDDTLogManager.getInstance().logMessage("CompilationUnit found and added to CodeStamp: "+nextName);
			compilationUnit = compiler.getCompilationUnitByName(nextName);	
			cUnits[i] = compilationUnit;
			i++;
		}
		
		
		CodeStamp codeStamp = new CodeStamp(result, cUnits);
		return codeStamp;
	}
	
	public Result getResult(){
		//getter because it should not be modified from outside
		return result;
	}

}
