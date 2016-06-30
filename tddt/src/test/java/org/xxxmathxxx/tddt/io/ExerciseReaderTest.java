package org.xxxmathxxx.tddt.io;

import org.junit.Test;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseCollection;

import static org.junit.Assert.*;

/**
 * @author Fabian
 * Test for ExerciseReader
 */
public class ExerciseReaderTest {
	@Test
	public void constructorTest() {
		ExerciseReader er = new ExerciseReader();
		ExerciseCollection ex= er.readAllExercises();
		
		//ex.getExercise(1).print();
		//ex.getExercise(0).print();
		
	//	Exercise test = er.readExercise("RomanNumbers.xml");

		//test.print();
		
	//	assertEquals(test.name, "RomanNumbers");
		
	}
}
