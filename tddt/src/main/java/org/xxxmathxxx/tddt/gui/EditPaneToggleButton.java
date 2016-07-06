package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.ToggleButton;

public class EditPaneToggleButton extends ToggleButton
{
	public int index;
	
	/**
	 * New ToggleButton used by EditPane
	 * @param text
	 * @param index
	 */
	public EditPaneToggleButton(String text, int index)
	{
		super(text);
		this.index=index;
	}
}
