package org.xxxmathxxx.tddt.gui.ide;

import org.xxxmathxxx.tddt.data.ExerciseClass;


/**
 * @author Fabian
 * Edit Pane for code
 */
public class CodeEditPane extends EditPane {
	
	ExerciseClass[] classes;
	
	public CodeEditPane(ExerciseClass[] classes)
	{
		super(classes);
		
		this.classes=classes;	
	}
}
