package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.ToggleButton;

/**An extension of the ToggleButton that stores the associated index
 * @author xxxMathxx 2016
 */
public class EditPaneToggleButton extends ToggleButton
{
	/**
	 * The index as Integer
	 */
	public int index;
	
	/**Default constructor
	 * New ToggleButton used by EditPane
	 * @param text The text to be displayed as 
	 * @param index The index associated as Integer
	 */
	public EditPaneToggleButton(String text, int index)
	{
		super(text);
		this.index=index;
	}
}
