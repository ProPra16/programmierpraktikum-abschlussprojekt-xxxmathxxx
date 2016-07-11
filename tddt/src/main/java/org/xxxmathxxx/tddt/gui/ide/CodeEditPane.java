package org.xxxmathxxx.tddt.gui.ide;

import org.xxxmathxxx.tddt.data.ClassData;
import org.xxxmathxxx.tddt.data.ExerciseClass;


/**
 * @author Fabian
 * Edit Pane for code
 */
public class CodeEditPane extends EditPane {
	
	ClassData[] classes;

	String[] savedClasses;
	
	public CodeEditPane(ExerciseClass[] classes)
	{
		super(classes);
		
		this.classes=classes;
		

	}
}
