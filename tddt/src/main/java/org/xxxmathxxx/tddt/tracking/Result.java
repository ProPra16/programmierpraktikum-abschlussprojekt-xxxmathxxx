/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.Collection;

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
	 * Adds the CompilerResult compilerResult
	 *
	 * @param compilerResult the compilerResult
	 */
	public void add(CompilerResult compilerResult){
		this.compilerResult = compilerResult;
	}
	
	public boolean compilerError(){
		if(compilerResult.hasCompileErrors())
			return false;
		return true;
	}
	
	public Collection<CompileError> getCompilerErrors(CompilationUnit compUnit){
		return compilerResult.getCompilerErrorsForCompilationUnit(compUnit);
	}
	
	
	/**
	 * Adds the testResult
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
