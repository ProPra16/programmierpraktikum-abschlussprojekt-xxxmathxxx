package org.xxxmathxxx.tddt.profile;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.xxxmathxxx.tddt.tracking.TrackerManager;

public class ProfileStatsTests {

	@Test
	public void addExerciseTest(){
		Profile m = new Profile("Sascha","graphics/faces/fou.png");
		TrackerManager tracker = new TrackerManager();
		m.profileStats.addExercise(tracker,"test", false);
		assertEquals(m.profileStats.getExerciseName(0), "test");
	}
	@Test
	public void isExerciseMasteredTest(){
		Profile m = new Profile("Sascha","graphics/faces/fou.png");
		TrackerManager tracker = new TrackerManager();
		m.profileStats.addExercise(tracker,"test", false);
		m.profileStats.addExercise(tracker,"test2",false);
		m.profileStats.setMasteredExercise(1, true);
		
		assertEquals(m.profileStats.isExerciseMastered("test2"), true);
	}
	
	@Test
	public void isExerciseMasteredTest2(){
		Profile m = new Profile("Sascha","graphics/faces/fou.png");
		TrackerManager tracker = new TrackerManager();
		m.profileStats.addExercise(tracker,"test", false);
		m.profileStats.addExercise(tracker,"test2", false);
		m.profileStats.setMasteredExercise(1, false);
		
		assertEquals(m.profileStats.isExerciseMastered("test2"), false);
	}
	
	@Test
	public void isExerciseDeletedTest(){
		Profile m = new Profile("Sascha","graphics/faces/fou.png");
		TrackerManager tracker = new TrackerManager();
		m.profileStats.addExercise(tracker,"test", false);
		m.profileStats.addExercise(tracker,"test2",false);
		m.profileStats.setMasteredExercise(1, true);
		m.profileStats.deleteExercise(0);
		
		assertEquals(m.profileStats.isExerciseMastered("test2"), true);
	}
	
	@Test
	public void masteredExercisesTest(){
		Profile m = new Profile("Sascha","graphics/faces/fou.png");
		TrackerManager tracker = new TrackerManager();
		m.profileStats.addExercise(tracker,"test", false);
		m.profileStats.addExercise(tracker,"test2", true);
		m.profileStats.addExercise(tracker,"test3", true);
		
		m.profileStats.setMasteredExercise(0, true);
		m.profileStats.setMasteredExercise(2, false);
		
		assertEquals(m.profileStats.masteredExercises(), 2);
	}
}
