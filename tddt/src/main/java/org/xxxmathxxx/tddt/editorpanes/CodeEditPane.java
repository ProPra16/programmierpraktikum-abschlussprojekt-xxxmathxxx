package org.xxxmathxxx.tddt.editorpanes;

import org.xxxmathxxx.tddt.data.ExerciseClass;

public class CodeEditPane extends EditPane {
	
	ExerciseClass[] classes;
	
	public CodeEditPane(ExerciseClass[] classes)
	{
		super(classes);
		
		this.classes=classes;	
	}
}
