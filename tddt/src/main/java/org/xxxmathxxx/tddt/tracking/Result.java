/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.Collection;
import java.util.Iterator;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.TestResult;


/**
 * The Class Result. ??? WHAT DOES IT EVEN DO?
 *
 * @author Tschebyscheff, 24.06.16
 * class saves Compile and Test Results
 */
public class Result {

	/** The test result. */
	private TestResult testResult;
	
	/** The compiler result. */
	private CompilerResult compilerResult;
	
	
	public Result(TestResult testResult, CompilerResult compilerResult){
		this.testResult = testResult;
		this.compilerResult = compilerResult;
	}
	
	/**
	 * Compiler error.
	 * @return true, if successful
	 */
	public boolean compilerError(){
		if(compilerResult.hasCompileErrors())
			return true;
		return false;
	}
	
	/**
	 * One failed test.
	 *
	 * @return true, if Number of FailedTests == 1
	 */
	public boolean oneFailedTest(){
		if(testResult.getNumberOfFailedTests() == 1)
			return true;
		return false;
	}
	
	/**
	 * Gets the compiler errors. //AREN't those functions all available by default???
	 *
	 * @param list the compilation units
	 * @return the compiler errors
	 */
	public String getCompilerErrors(CompilationUnit[] cUnits){
		String ret = "CompileErrors found: \n";
		for(int i = 0; i < cUnits.length; i++)
		{
			CompilationUnit compUnit = (CompilationUnit) cUnits[i];
			Collection<CompileError> collection = getCompilerErrors(compUnit);
			Iterator<CompileError> errors = collection.iterator();
					
			while(errors.hasNext())
			{
				ret += ((CompileError) errors.next()).getMessage();
			}
		}
		return ret;
	}
	
	/**
	 * Gets the compiler errors.
	 *
	 * @param compUnit the compilation unit
	 * @return the compiler errors
	 */
	public Collection<CompileError> getCompilerErrors(CompilationUnit compUnit){
		return compilerResult.getCompilerErrorsForCompilationUnit(compUnit);
	}
	
	
	/**
	 * Gets the compiler result.
	 *
	 * @return the compiler result
	 */
	public CompilerResult getCompilerResult(){
		return compilerResult;
	}


	public int getNumberOfFailedTests() {
		if (testResult == null){ //WHY THE FUCK WOULD THIS EVER HAPPEN???
			return 0;
		}
		else{
			return testResult.getNumberOfFailedTests();
		}
	}	
}
