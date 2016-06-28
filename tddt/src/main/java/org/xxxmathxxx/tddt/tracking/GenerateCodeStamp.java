/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerResult;
import vk.core.api.JavaStringCompiler;
import vk.core.api.TestResult;



/**
 * The Class GenerateCodeStemp.
 *
 * @author Tschebyscheff, 24.06.16
 * responsible for generating CodeStemp
 * method generate gets JavaStringCompiler and compiles the code
 * Result Object is responsible for Compile and Test Results
 */
public class GenerateCodeStamp {
	
	
	/**
	 * Generates a CodeStamp.
	 *
	 * @param compiler the compiler
	 * @return the code stamp
	 */
	public static CodeStamp generate(JavaStringCompiler compiler){	
		compiler.compileAndRunTests();
		
		ArrayList<Object> list = new ArrayList<Object>();
		Set<String> set = compiler.getAllCompilationUnitNames();
		String[] s = new String[set.size()];
		Iterator<String> iterator = set.iterator();
		Result result = new Result();
		Date date = new Date();
		CompilationUnit compilationUnit;
		
		int i = 0;
		while(iterator.hasNext()){
			s[i] = (String) iterator.next();
			compilationUnit = compiler.getCompilationUnitByName(s[i++]);
			list.add(compilationUnit);
		}
		CompilerResult compilerResult = compiler.getCompilerResult();
		TestResult testResult = compiler.getTestResult();
		
		result.add(compilerResult);
		result.add(testResult);
		
		CodeStamp codeStemp = new CodeStamp(result, list, date);
		return codeStemp;
	}

}
