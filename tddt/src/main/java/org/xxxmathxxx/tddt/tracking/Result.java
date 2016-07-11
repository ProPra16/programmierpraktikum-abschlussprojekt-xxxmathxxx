/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerResult;
import vk.core.api.TestResult;


/**
 * The Class Result.
 *
 * @author Tschebyscheff, 24.06.16
 * class saves Compile and Test Results
 */
public class Result {

	/** The test result. */
	TestResult testResult;
	
	/** The compiler result. */
	CompilerResult compilerResult;
	
	/**
	 * Adds the CompilerResult compilerResult.
	 *
	 * @param compilerResult the compilerResult
	 */
	public void add(CompilerResult compilerResult){
		this.compilerResult = compilerResult;
	}
	
	/**
	 * Compiler error.
	 *
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
	 * Gets the compiler errors.
	 *
	 * @param list the compilation units
	 * @return the compiler errors
	 */
	public String getCompilerErrors(ArrayList<Object> list){
		String ret = "CompileErrors found: \n";
		for(int i = 0; i < list.size(); i++)
		{
			CompilationUnit compUnit = (CompilationUnit) list.get(i);
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
	 * Adds the testResult.
	 *
	 * @param testResult the testResult
	 */
	public void add(TestResult testResult){
		this.testResult = testResult;
	}
	
	/**
	 * Gets the test result.
	 *
	 * @return the test result
	 */
	public TestResult getTestResult(){
		return testResult;
	}
	
	/**
	 * Gets the compiler result.
	 *
	 * @return the compiler result
	 */
	public CompilerResult getCompilerResult(){
		return compilerResult;
	}
	
	
	/**
	 * Resett results.
	 */
	public void resettResults(){
		compilerResult = null;
		testResult = null;
		
	}	
}
