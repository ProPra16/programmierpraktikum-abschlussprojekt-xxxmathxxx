package org.xxxmathxxx.tddt.editorpanes;

import org.xxxmathxxx.tddt.data.ExerciseTest;

/**
 * @author Fabian
 * Edit Pane for tests
 */
public class TestEditPane extends EditPane{

	ExerciseTest[] tests;
	
	
	public TestEditPane(ExerciseTest[] tests)
	{
		super(tests);
		
		this.tests=tests;
		
		
	}



}
