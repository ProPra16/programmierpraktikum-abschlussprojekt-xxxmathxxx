/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Date;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;



/**
 * @author Tschebyscheff, 24.06.16
 * responsible for generating CodeStemp
 * method generate gets JavaStringCompiler and compiles the code
 * failures(CompilerError/ TestFailure), are  stored in Error Object
 * if there arent any Failures, Error Object will have no instantiated instance variables
 */
public class GenerateCodeStemp {
	
	
	public static CodeStemp generate(JavaStringCompiler compiler){	
		compiler.compileAndRunTests();
		
		ArrayList<Object> list = new ArrayList<Object>();
		AbstractSet<String> set = (AbstractSet<String>) compiler.getAllCompilationUnitNames();
		CompilerResult compilerResult = compiler.getCompilerResult();
		TestResult testResult = compiler.getTestResult();
		String[] s = (String[]) set.toArray();
		Error error = new Error();
		Date date = new Date();
		
		for(int i = 0; i < s.length; i++){
			CompilationUnit compilationUnit = compiler.getCompilationUnitByName(s[i]);
			list.add(compilationUnit);
		}
		if(compilerResult.hasCompileErrors()){
			error.add(compilerResult);
		}
		if(testResult.getNumberOfFailedTests() > 0){
			error.add(testResult);
		}
		
		CodeStemp codeStemp = new CodeStemp(error, list, date);
		return codeStemp;
	}

}
