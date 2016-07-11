/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

/**
 * @author Tschebyscheff, 25.06.16
 *
 */
public class GenerateCodeStampTests {

	
	@Test
	public void GenerateCodeStempTest(){
		CompilationUnit compUnit1 = new CompilationUnit("Test1", "ich bin code1", false);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(compUnit1);
		
		CodeStamp codeStemp = CodeStamp.generateCodeStamp(compiler);
		ArrayList<CompilationUnit> list = codeStemp.getCompilationUnits();
		
		CompilationUnit compUnitTest = (CompilationUnit) list.get(0);
		assertEquals(compUnit1, compUnitTest);
		
	}
}

