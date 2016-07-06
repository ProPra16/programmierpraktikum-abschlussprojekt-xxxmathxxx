package org.xxxmathxxx.tddt.editorpanes;

import org.xxxmathxxx.tddt.data.ExerciseTest;

public class TestEditPane extends EditPane{

	ExerciseTest[] tests;
	
	public TestEditPane(ExerciseTest[] tests)
	{
		super();
		te.setText("Test");
		
		this.tests=tests;
	}
}
