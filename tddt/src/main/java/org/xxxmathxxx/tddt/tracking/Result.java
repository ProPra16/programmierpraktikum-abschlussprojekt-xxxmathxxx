/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.Collection;
import java.util.Iterator;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.TestResult;


/**
 * This class stores the result of a given build
 * It saves the results of code/test compilation.
 * @author xxxMathxxx 2016
 */
public class Result {

	/** The test result. */
	private TestResult testResult;
	
	/** The compiler result. */
	private CompilerResult compilerResult;
	
	
	/**Default construcot, creates a new result
	 * @param testResult The TestResult
	 * @param compilerResult The CompilerResult
	 */
	public Result(TestResult testResult, CompilerResult compilerResult){
		this.testResult = testResult;
		this.compilerResult = compilerResult;
	}
	
	/**
	 * Compiler error, this is simply piped through
	 * @return true, if no compiler errors are present, false otherwise
	 */
	public boolean hasNoCompilerErrors(){
		if(compilerResult.hasCompileErrors())
			return true;
		return false;
	}
	
	/**
	 * This function returns true if exactly one test failed (requirement for TDD)
	 * @return true, if Number of FailedTests == 1, false otherwise
	 */
	public boolean oneFailedTest(){
		if(testResult.getNumberOfFailedTests() == 1)
			return true;
		return false;
	}
	
	/**
	 * Gets the compiler errors for a given set of compilation units
	 *
	 * @param cUnits the compilation units
	 * @return the compiler errors as raw String
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
	 * Gets the compiler errors for single CompilationUnit
	 *
	 * @param compUnit the compilation unit
	 * @return the compiler errors as a CompileError collection
	 */
	public Collection<CompileError> getCompilerErrors(CompilationUnit compUnit){
		return compilerResult.getCompilerErrorsForCompilationUnit(compUnit);
	}
	
	

	/**This function returns the number of failed tests in the build
	 * @return the number as Integer
	 */
	public int getNumberOfFailedTests() {
		if (testResult == null){
			TDDTLogManager.getInstance().logMessage("An error occured while resolving the number of failed tests!");
			return 0;
		}
		else{
			return testResult.getNumberOfFailedTests();
		}
	}	
}
