package org.xxxmathxxx.tddt.editorpanes;

import org.xxxmathxxx.tddt.gui.TextEditor;

import javafx.scene.layout.Pane;

public abstract class EditPane extends Pane {

	TextEditor te;
	Boolean isActive;
	
	String currentCode;
	
	public EditPane()
	{	
		isActive=false;
		
		te= new TextEditor();
		te.setPrefSize(500, 450);
		
		
		getChildren().add(te);
	}
	
	public Boolean isActive()
	{
		return isActive;
	}
	
	public void switchActive()
	{
		if(isActive) isActive=false;
		else isActive=true;
	}
	
}
