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
		
		savedClasses= new String[classdata.length];
	}
	
	/**
	 * Creates backup of content.
	 */
	public void createBackup() {
		System.out.println("Saving current state as backup");

		for(int i=0; i<classdata.length;i++)
		{
			savedClasses[i]=classdata[i].code.rawText;
		}
	}
	
	/**
	 * Rerolls changes to the state when createBackup() was called
	 */
	public void rerollChanges()
	{
		for(int i=0; i<classdata.length;i++)
		{
			classdata[i].code.rawText=savedClasses[i];
		}
		
		te.setText(savedClasses[selectedPage]);
	}
}
