/**
 * 
 */
package org.xxxmathxxx.tddt.tracking_analysis;



/**
 * @author Tschebyscheff, 05.07.16
 *
 */
public class AnalyzedTrackingDataCollectionTests {


	String string11 = "public class test{public static int hello() { return 1;}}";
	String string12 = "import static org.junit.Assert.* ;import org.junit.Test; public class TestofTest {@Test public void methodTest(){ assertEquals(2, test.hello());}}";
	
	/*
	@Test
	public void analyzeTrackingDataCollectionTests1(){
		
		TrackerManager tracker1 = new TrackerManager();
		TrackerManager tracker2 = new TrackerManager();
		CompilationUnit compUnit1 = new CompilationUnit("test", string11, false);
		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string12, true);	
		JavaStringCompiler compiler1 = CompilerFactory.getCompiler(compUnit1);
		JavaStringCompiler compiler2 = CompilerFactory.getCompiler(compUnit2);
		
		CodeStamp codeStamp1 = GenerateCodeStamp.generate(compiler1);
		tracker1.stageGreen.codeStampCollection.addCodeStemp(codeStamp1);
		
		CodeStamp codeStamp2 = GenerateCodeStamp.generate(compiler2);
		tracker2.stageGreen.codeStampCollection.addCodeStemp(codeStamp2);
		
		AnalyzedTrackingData data1 = new AnalyzedTrackingData(tracker1, "exercise1", "profilName1");
		AnalyzedTrackingData data2 = new AnalyzedTrackingData(tracker2, "exercise2", "profilName2");
		
		AnalyzedTrackingDataCollection collection = new AnalyzedTrackingDataCollection();
		collection.add(data1);
		collection.add(data2);
		
		AnalyzedTrackingData test = collection.getAnalyzedTrackingData("profilName2", "exercise2");
		assertEquals(test, data2);
	}
	
	@Test
	public void analyzeTrackingDataCollectionTests2(){
		
		TrackerManager tracker1 = new TrackerManager();
		TrackerManager tracker2 = new TrackerManager();
		CompilationUnit compUnit1 = new CompilationUnit("test", string11, false);
		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string12, true);	
		JavaStringCompiler compiler1 = CompilerFactory.getCompiler(compUnit1);
		JavaStringCompiler compiler2 = CompilerFactory.getCompiler(compUnit2);
		
		CodeStamp codeStamp1 = GenerateCodeStamp.generate(compiler1);
		tracker1.stageGreen.codeStampCollection.addCodeStemp(codeStamp1);
		
		CodeStamp codeStamp2 = GenerateCodeStamp.generate(compiler2);
		tracker2.stageGreen.codeStampCollection.addCodeStemp(codeStamp2);
		
		AnalyzedTrackingData data1 = new AnalyzedTrackingData(tracker1, "exercise1", "profilName1");
		AnalyzedTrackingData data2 = new AnalyzedTrackingData(tracker2, "exercise1", "profilName2");
		
		AnalyzedTrackingDataCollection collection = new AnalyzedTrackingDataCollection();
		collection.add(data1);
		collection.add(data2);
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(data1);
		list.add(data2);
		
		assertEquals(list, collection.getAllDataWithExercise("exercise1"));
	}
	
	@Test
	public void analyzeTrackingDataCollectionTests3(){
		
		TrackerManager tracker1 = new TrackerManager();
		TrackerManager tracker2 = new TrackerManager();
		CompilationUnit compUnit1 = new CompilationUnit("test", string11, false);
		CompilationUnit compUnit2 = new CompilationUnit("TestofTest", string12, true);	
		JavaStringCompiler compiler1 = CompilerFactory.getCompiler(compUnit1);
		JavaStringCompiler compiler2 = CompilerFactory.getCompiler(compUnit2);
		
		CodeStamp codeStamp1 = GenerateCodeStamp.generate(compiler1);
		tracker1.stageGreen.codeStampCollection.addCodeStemp(codeStamp1);
		
		CodeStamp codeStamp2 = GenerateCodeStamp.generate(compiler2);
		tracker2.stageGreen.codeStampCollection.addCodeStemp(codeStamp2);
		
		AnalyzedTrackingData data1 = new AnalyzedTrackingData(tracker1, "exercise1", "profilName1");
		AnalyzedTrackingData data2 = new AnalyzedTrackingData(tracker2, "exercise1", "profilName1");
		
		AnalyzedTrackingDataCollection collection = new AnalyzedTrackingDataCollection();
		collection.add(data1);
		collection.add(data2);
		
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(data1);
		list.add(data2);
		
		assertEquals(list, collection.getAllDataWithName("profilName1"));
	}
	*/
}
