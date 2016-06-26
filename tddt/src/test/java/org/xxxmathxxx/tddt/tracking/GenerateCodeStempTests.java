/**
 * 
 */
package org.xxxmathxxx.tddt.tracking;

import org.junit.Test;

import vk.core.api.CompilationUnit;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

/**
 * @author Tschebyscheff, 25.06.16
 *
 */
public class GenerateCodeStempTests {

	
	@Test
	public void addProfileTest(){
		CompilationUnit compUnit = new CompilationUnit("Test", "ich bin code", false);
		JavaStringCompiler compiler = CompilerFactory.getCompiler(compUnit);
		
		CodeStemp codeStemp = GenerateCodeStemp.generate(compiler);
		
	}
}
