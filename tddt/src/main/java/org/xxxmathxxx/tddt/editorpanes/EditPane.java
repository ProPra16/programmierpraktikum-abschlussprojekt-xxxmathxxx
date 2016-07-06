package org.xxxmathxxx.tddt.editorpanes;


import javafx.scene.control.TextField;

//import org.xxxmathxxx.tddt.gui.ide.TextEditor; Until its finished

import javafx.scene.layout.Pane;

public abstract class EditPane extends Pane {

	TextField te;
	Boolean isActive;
	
	String currentCode;
	
	public EditPane()
	{	
		isActive=false;
		
		te= new TextField();
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
