package org.xxxmathxxx.tddt.profile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.xxxmathxxx.tddt.tracking.Tracker;

public class ProfileStatsTests {

	@Test
	public void addExerciseTest(){
		Profile m = new Profile("Sascha");
		Tracker tracker = new Tracker();
		m.addExercise(tracker,"test", false);
		assertEquals(m.getExerciseName(0), "test");
	}
	@Test
	public void isExerciseMasteredTest(){
		Profile m = new Profile("Sascha");
		Tracker tracker = new Tracker();
		m.addExercise(tracker,"test", false);
		m.addExercise(tracker,"test2",false);
		m.setMasteredExercise(1, true);
		
		assertEquals(m.isExerciseMastered("test2"), true);
	}
	
	@Test
	public void isExerciseMasteredTest2(){
		Profile m = new Profile("Sascha");
		Tracker tracker = new Tracker();
		m.addExercise(tracker,"test", false);
		m.addExercise(tracker,"test2", false);
		m.setMasteredExercise(1, false);
		
		assertEquals(m.isExerciseMastered("test2"), false);
	}
	
	@Test
	public void isExerciseDeletedTest(){
		Profile m = new Profile("Sascha");
		Tracker tracker = new Tracker();
		m.addExercise(tracker,"test", false);
		m.addExercise(tracker,"test2",false);
		m.setMasteredExercise(1, true);
		m.deleteExercise(0);
		
		assertEquals(m.isExerciseMastered("test2"), true);
	}
	
	@Test
	public void masteredExercisesTest(){
		Profile m = new Profile("Sascha");
		Tracker tracker = new Tracker();
		m.addExercise(tracker,"test", false);
		m.addExercise(tracker,"test2", true);
		m.addExercise(tracker,"test3", true);
		
		m.setMasteredExercise(0, true);
		m.setMasteredExercise(2, false);
		
		assertEquals(m.masteredExercises(), 2);
	}
}
