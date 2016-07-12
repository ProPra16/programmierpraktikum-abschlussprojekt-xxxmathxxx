package org.xxxmathxxx.tddt.io;

import org.junit.Test;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseCollection;

import static org.junit.Assert.*;

/**
 * @author Fabian Test for ExerciseReader
 */
public class ExerciseReaderTest {
	@Test
	public void testRandomExercise() throws Exception {
		ExerciseCollection ex = ExerciseReader.readAllExercises();
		assertEquals(true, (ex.getRandomExercise() instanceof Exercise));

	}
	
	@Test
	public void testExampleExercises() throws Exception {
		Exercise test = ExerciseReader.readExercise("src/main/resources/exercises/RomanNumbers.xml");
		test.print();
		assertEquals(test.name, "RomanNumbers");

	}
}
