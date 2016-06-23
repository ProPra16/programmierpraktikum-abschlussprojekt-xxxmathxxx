package org.xxxmathxxx.tddt.profile;

import static org.junit.Assert.*;

import org.junit.Test;
import org.xxxmathxxx.tddt.profile.MasteredExercise;

public class MasteredExercisesTests {

	@Test
	public void addExerciseTest(){
		Profile m = new Profile("Sascha");
		m.addExercise("test");
		assertEquals(m.getExerciseName(0), "test");
	}
	@Test
	public void isExerciseMasteredTest(){
		Profile m = new Profile("Sascha");
		m.addExercise("test");
		m.addExercise("test2");
		m.setMasteredExercise(1, true);
		
		assertEquals(m.isExerciseMastered("test2"), true);
	}
	
	@Test
	public void isExerciseMasteredTest2(){
		Profile m = new Profile("Sascha");
		m.addExercise("test");
		m.addExercise("test2");
		m.setMasteredExercise(1, false);
		
		assertEquals(m.isExerciseMastered("test2"), false);
	}
	
	@Test
	public void isExerciseDeletedTest(){
		Profile m = new Profile("Sascha");
		m.addExercise("test");
		m.addExercise("test2");
		m.setMasteredExercise(1, true);
		m.deleteExercise(0);
		
		assertEquals(m.isExerciseMastered("test2"), true);
	}
	
	@Test
	public void giveAllExerciseNamesTest(){
		Profile m = new Profile("Sascha");
		m.addExercise("test");
		m.addExercise("test2");
		m.addExercise("test3");
		
		String[] s = new String[3];
		s[0] = "test";
		s[1] = "test2";
		s[2] = "test3";
		
		assertArrayEquals(m.giveAllExerciseNames(), s);
	}
	
	@Test
	public void masteredExercisesTest(){
		Profile m = new Profile("Sascha");
		m.addExercise("test", false);
		m.addExercise("test2", true);
		m.addExercise("test3", true);
		
		m.setMasteredExercise(0, true);
		m.setMasteredExercise(2, false);
		
		assertEquals(m.masteredExercises(), 2);
	}
}
