package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.ToggleButton;

public class EditPaneToggleButton extends ToggleButton
{
	public int index;
	
	public EditPaneToggleButton(String text, int index)
	{
		super(text);
		this.index=index;
	}
}
