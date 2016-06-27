/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import vk.core.api.CompilerResult;
import vk.core.api.TestResult;

/**
 * @author Tschebyscheff, 24.06.16
 * class saves Compile and Test Results
 * 
 */
public class Result {

	private TestResult testResult;
	private CompilerResult compilerResult;
	
	/*
	 * adds CompilerResult
	 */
	public void add(CompilerResult e){
		this.compilerResult = e;
	}
	
	/*
	 * adds TestResult
	 */
	public void add(TestResult t){
		this.testResult = t;
	}
	
	/*
	 * returns TestResult
	 */
	public TestResult getTestResult(){
		return testResult;
	}
	
	/*
	 * returns CompilerResult
	 */
	public CompilerResult getCompilerResult(){
		return compilerResult;
	}
	
	
	/*
	 * resett Results and set them to null
	 */
	public void resettResults(){
		compilerResult = null;
		testResult = null;
		
	}	
}
