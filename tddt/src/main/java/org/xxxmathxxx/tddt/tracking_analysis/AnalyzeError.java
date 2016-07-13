/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;

import java.util.Collection;
import java.util.Iterator;

import org.xxxmathxxx.tddt.tracking.CodeStamp;
import org.xxxmathxxx.tddt.tracking.CodeStampCollection;
import org.xxxmathxxx.tddt.tracking.Result;

import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;

/**
 * The Class AnalyzeError contains static functions to extract statistical data from CodeStamps
 * @author xxxMathxxx
 */
public class AnalyzeError {
	
	
	/**
	 * Analyze code stamps.
	 *
	 * @param codeStampCollection the code stamp collection
	 * @param error the error
	 * @return The ErrorCounter storing the occured errors
	 */
	public static ErrorCounter analyzeCodeStamps(CodeStampCollection codeStampCollection, ErrorCounter error){
		
		CodeStamp codeStamp;
		Result result;
		CompilationUnit[] cUnits;

		for(int i = 0; i < codeStampCollection.size(); i++){

			codeStamp = codeStampCollection.getCodeStemp(i);
			result = codeStamp.getResult();
			try{
				error.addError(ErrorType.TEST_FAILURE,result.getNumberOfFailedTests());
			}
			catch(NullPointerException e){}
			
			if(result.hasNoCompilerErrors()){
				cUnits = codeStamp.getCompilationUnits();
				error = AnalyzeError.handleCompileError(cUnits, result, error);
			}	
			
		}
		return error;
	}
	
	/**
	 * Counts errors occurring in various compilation units and generates an ErrorCounter
	 *
	 * @param cUnits The compilation units used
	 * @param result the result
	 * @param error the error
	 * @return the error
	 */
	public static ErrorCounter handleCompileError(CompilationUnit[] cUnits, Result result, ErrorCounter error){
		
		CompileError compileError;
		CompilationUnit compUnit;
		Collection<CompileError> collection;
		Iterator<CompileError> iterator;		
		
		for(int i2 = 0; i2 < cUnits.length; i2++){
			compUnit = cUnits[i2];
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
		
		
		//TODO: PLEASE!!! FOR THE LOVE OF GOD SOMEONE HAS TO CHECK IF ALL THOSE ERRORS ARE CORRECT AND MAYBE ADD SOME MORE
		
		error.addError(ErrorType.ANY);
		
		if(compilerError.contains("not have been initialized")){ //Variable wurde nicht initialisiert
			error.addError(ErrorType.SEMANTIC);
			error.addError(ErrorType.NOT_INITIALIZED);
		}
		else if(compilerError.contains("Invalid type expression")){ // Compiler findet etwas nicht, zum beipiel wegen fehlendem Smeikolon, falscher REchtschreiung(großes IF anstatt if)
			error.addError(ErrorType.SEMANTIC);
			error.addError(ErrorType.CANNOT_FIND);
		}
		else if(compilerError.contains("cannot find symbol")){    //Compiler findet etwas nicht, zum Beispiel package, variable ,klasse
			error.addError(ErrorType.SYNTAX);
			error.addError(ErrorType.CANNOT_FIND);
		}
		else if(compilerError.contains("return type required")){  // rückgabewert vergessen
			error.addError(ErrorType.SYNTAX);
		}
		else if(compilerError.contains("reached end of file while parsing")){ // Klammern vergessen
			error.addError(ErrorType.SYNTAX);
			error.addError(ErrorType.EXPECTED_WRONG_TYPE);
		}
		else if(compilerError.contains("cannot resolve symbol")){  // Compiler findet eine Beschreibung  im SourceCode nicht
			error.addError(ErrorType.SYNTAX);
			error.addError(ErrorType.CANNOT_FIND);
		}
		else if(compilerError.contains("expected")) {              // Klammer, Semikolon oder ähnliches vergessen
			error.addError(ErrorType.SYNTAX);
			error.addError(ErrorType.EXPECTED_WRONG_TYPE);
		}
		return error;
	}
	
}
