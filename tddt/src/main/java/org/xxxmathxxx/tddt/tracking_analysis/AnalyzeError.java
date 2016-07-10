/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.xxxmathxxx.tddt.tracking.CodeStamp;
import org.xxxmathxxx.tddt.tracking.CodeStampCollection;
import org.xxxmathxxx.tddt.tracking.Result;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.TestResult;

/**
 * The Class AnalyzeError.
 *
 * @author Tschebyscheff, 30.06.16
 */
public class AnalyzeError {
	
	
	/**
	 * Analyze code stamps.
	 *
	 * @param codeStampCollection the code stamp collection
	 * @param error the error
	 * @return the error
	 */
	public static ErrorCounter analyzeCodeStamps(CodeStampCollection codeStampCollection, ErrorCounter error){
		
		CodeStamp codeStamp;
		Result result;
		ArrayList<Object> list;
		TestResult testResult;;

		for(int i = 0; i < codeStampCollection.size(); i++){

			codeStamp = codeStampCollection.getCodeStemp(i);
			result = codeStamp.getResult();
			testResult = result.getTestResult();
			try{
				error.testFailure += testResult.getNumberOfFailedTests();
			}
			catch(NullPointerException e){}
			
			if(result.compilerError()){
				list = codeStamp.getCompilationUnits();
				error = AnalyzeError.handleCompileError(list, result, error);
			}	
			
		}
		return error;
	}
	
	/**
	 * Handle compile error.
	 *
	 * @param list the list
	 * @param result the result
	 * @param error the error
	 * @return the error
	 */
	public static ErrorCounter handleCompileError(ArrayList<Object> list, Result result, ErrorCounter error){
		
		CompileError compileError;
		CompilationUnit compUnit;
		Collection<CompileError> collection;
		Iterator<CompileError> iterator;		
		
		for(int i2 = 0; i2 < list.size(); i2++){
			compUnit = (CompilationUnit) list.get(i2);
			collection = result.getCompilerErrors(compUnit);
			iterator = collection.iterator();
			
			while(iterator.hasNext()){
				compileError = (CompileError) iterator.next();
				error = analyzeCompileError(compileError.getMessage(), error);	
			}
		}
		return error;
	}
	
	/**
	 * Analyze compile error.
	 *
	 * @param compilerError the compiler error
	 * @param error the error
	 * @return the error
	 */
	private static ErrorCounter analyzeCompileError(String compilerError, ErrorCounter error){
		
		error.totalError++;
		
		if(compilerError.contains("not have been initialized")){ //Variable wurde nicht initialisiert
			error.semanticError++;
			error.notInitializedError++;
		}
		else if(compilerError.contains("Invalid type expression")){ // Compiler findet etwas nicht, zum beipiel wegen fehlendem Smeikolon, falscher REchtschreiung(großes IF anstatt if)
			error.semanticError++;
			error.cannotFindError++;
		}
		else if(compilerError.contains("cannot find symbol")){    //Compiler findet etwas nicht, zum Beispiel package, variable ,klasse
			error.syntaxError++;
			error.cannotFindError++;
		}
		else if(compilerError.contains("return type required")){  // rückgabewert vergessen
			error.syntaxError++;
		}
		else if(compilerError.contains("reached end of file while parsing")){ // Klammern vergessen
			error.syntaxError++;
			error.expectedError++;
		}
		else if(compilerError.contains("cannot resolve symbol")){  // Compiler findet eine Beschreibung  im SourceCode nicht
			error.syntaxError++;
			error.cannotFindError++;
		}
		else if(compilerError.contains("expected")) {              // Klammer, Semikolon oder ähnliches vergessen
			error.syntaxError++;
			error.expectedError++;
		}
		return error;
	}
	
}
