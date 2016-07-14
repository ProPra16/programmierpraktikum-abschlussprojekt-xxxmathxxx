package org.xxxmathxxx.tddt.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.io.ExerciseReader;


public class TDDTThreadTest {

	@Test
	public void constructorTest() {
		TDDTThread.getInstance().initialize();
		TDDTThread.getInstance().beginExercise(ExerciseReader.readAllExercises().get(0));
		
		if(TDDTThread.getInstance() instanceof TDDTThread)
		{
			assertEquals(true, true);
		}
		else
		{
			assertEquals(true, false);
		}
	}
	
	@Test
	public void trackingtest() {
		TDDTThread.getInstance().initialize();
		TDDTThread.getInstance().beginExercise(ExerciseReader.readAllExercises().get(0));
		
		assertEquals(TDDTThread.getInstance().trackerManager.getActiveTracker().getKeystrokes(), 0);
		assertEquals(TDDTThread.getInstance().trackerManager.atMap.get(CodeStage.CODE).getElapsedTime(), 0.0, 0.0);
		assertEquals(TDDTThread.getInstance().trackerManager.atMap.get(CodeStage.REFACTOR).getElapsedTime(), 0.0, 0.0);
		
	}
}
