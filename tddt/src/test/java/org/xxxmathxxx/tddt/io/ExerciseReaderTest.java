package org.xxxmathxxx.tddt.io;

import org.junit.Test;
import org.xxxmathxxx.tddt.data.Exercise;

import static org.junit.Assert.*;

/**
 * @author Fabian
 * Test for ExerciseReader
 */
public class ExerciseReaderTest {
	@Test
	public void constructorTest() {
		ExerciseReader er = new ExerciseReader();
		er.readAllExercises();
		
	//	Exercise test = er.readExercise("RomanNumbers.xml");

		//test.print();
		
	//	assertEquals(test.name, "RomanNumbers");
		
	}
}
