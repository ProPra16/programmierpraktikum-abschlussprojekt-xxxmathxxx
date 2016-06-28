/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

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
	private TestResult testResult;
	
	/** The compiler result. */
	private CompilerResult compilerResult;
	
	/**
	 * Adds the CompilerResult compilerResult
	 *
	 * @param compilerResult the compilerResult
	 */
	public void add(CompilerResult compilerResult){
		this.compilerResult = compilerResult;
	}
	
	/**
	 * Adds the testResult
	 *
	 * @param testResult the testResult
	 */
	/*
	 * adds TestResult
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
