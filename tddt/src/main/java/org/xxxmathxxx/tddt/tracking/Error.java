/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import vk.core.api.CompilerResult;
import vk.core.api.TestResult;

/**
 * @author Tschebyscheff, 24.06.16
 * class for classifing and gathering ErrorTypes
 * 
 */
public class Error {

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
	 * resett Errors and set them to null
	 */
	public void resettErrors(){
		compilerResult = null;
		testResult = null;
		
	}	
	/*
	 * returns true if CompilerResult or TestResult are given
	 */
	public boolean hasErrors(){
		
		if((compilerResult == null) && (testResult == null))
				return false;
		return true;
	}
}
