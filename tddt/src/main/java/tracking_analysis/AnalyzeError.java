/**
 * 
 */
package tracking_analysis;

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
	public static Error analyzeCodeStamps(CodeStampCollection codeStampCollection, Error error){
		
		CodeStamp codeStamp;
		Result result;
		ArrayList<Object> list;
		TestResult testResult;;

		for(int i = 0; i < codeStampCollection.size(); i++){
			codeStamp = codeStampCollection.getCodeStemp(i);
			result = codeStamp.getResult();
			testResult = result.getTestResult();
			error.testFailure += testResult.getNumberOfFailedTests();
			
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
	public static Error handleCompileError(ArrayList<Object> list, Result result, Error error){
		
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
	private static Error analyzeCompileError(String compilerError, Error error){
		
		if(compilerError.contains("not have been initialized")){
			error.semanticError++;
		}
		else if(compilerError.contains("cannot resolve symbol")){
			error.syntaxError++;
		}
		else if(compilerError.contains("expected")){
			error.syntaxError++;
		}
		return error;
	}
	
}
