///**
// * 
// */
//package org.xxxmathxxx.tddt.tracking_analysis;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//import org.xxxmathxxx.tddt.tracking.CodeStamp;
//import org.xxxmathxxx.tddt.tracking.Tracker;
//
//import vk.core.api.CompilationUnit;
//import vk.core.api.CompilerFactory;
//import vk.core.api.JavaStringCompiler;
//
///**
// * @author Tschebyscheff, 05.07.16
// *
// */
//public class AnalyzedTrackingDataTests {
//
//	String string11 = "public class test{public static int hello() { return 1;}}";
//	String string12 = "import static org.junit.Assert.* ;import org.junit.Test; public class TestofTest {@Test public void methodTest(){ assertEquals(2, test.hello());}}";
//	
//	String string21 = "import static org.junit.Assert.* ;import org.junit.Test; public class TestofTest {@Test public  methodTest(){ assertEquals(1, test.hello());}}";
//	
//	String string31 = "public class test { public static int hello() { int i; return i;}}";
//	
//	String string41 = "public class test{public static int hello { return 1;}}";
//	String string42 = "import static org.junit.Assert.* ;import org.junit.Test; public class TestofTest {@Test public  methodTest(){ assertEquals(2, test.hello());}}";
//	
//	@Test
//	public void analyzeTrackingDataTests1(){
//		
//		Tracker tracker = new Tracker();
//		CompilationUnit compUnit1 = new CompilationUnit("test", string11, false);
//		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string12, true);	
//		JavaStringCompiler compiler = CompilerFactory.getCompiler(compUnit1, compUnit2);
//		
//		CodeStamp codeStamp = CodeStamp.generateCodeStamp(compiler);
//		tracker.stageGreen.codeStampCollection.addCodeStamp(codeStamp);
//		
//		AnalyzedTrackingData data = new AnalyzedTrackingData(tracker);
//		
//		assertEquals(data.analyzedStageGreen.error.testFailure, 1);
//	}
//	
//	@Test
//	public void analyzeTrackingDataTests2(){
//		
//		Tracker tracker = new Tracker();
//		CompilationUnit compUnit1 = new CompilationUnit("test", string11, false);
//		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string21, true);	
//		JavaStringCompiler compiler = CompilerFactory.getCompiler(compUnit1, compUnit2);
//		
//		CodeStamp codeStamp = CodeStamp.generateCodeStamp(compiler);
//		tracker.stageGreen.codeStampCollection.addCodeStamp(codeStamp);
//		AnalyzedTrackingData data = new AnalyzedTrackingData(tracker);
//		
//		assertEquals(data.analyzedStageGreen.error.syntaxError, 1);
//	}
//	
//	@Test
//	public void analyzeTrackingDataTests4(){
//		
//		Tracker tracker = new Tracker();
//		CompilationUnit compUnit1 = new CompilationUnit("test", string41, false);
//		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string42, true);	
//		JavaStringCompiler compiler = CompilerFactory.getCompiler(compUnit1, compUnit2);
//		
//		CodeStamp codeStamp = CodeStamp.generateCodeStamp(compiler);
//		tracker.stageRefactor.codeStampCollection.addCodeStamp(codeStamp);
//		AnalyzedTrackingData data = new AnalyzedTrackingData(tracker);
//		
//		assertEquals(data.analyzedStageRefactor.error.syntaxError, 2);
//	}
//	
//	@Test
//	public void analyzeTrackingDataTests3(){
//		
//		Tracker tracker = new Tracker();
//		CompilationUnit compUnit1 = new CompilationUnit("test", string31, false);
//		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string12, true);	
//		JavaStringCompiler compiler = CompilerFactory.getCompiler(compUnit1, compUnit2);
//		
//		CodeStamp codeStamp = CodeStamp.generateCodeStamp(compiler);
//		tracker.stageRefactor.codeStampCollection.addCodeStamp(codeStamp);
//		AnalyzedTrackingData data = new AnalyzedTrackingData(tracker);
//		
//		assertEquals(data.analyzedStageRefactor.error.semanticError, 1);
//	}
//	
//}
