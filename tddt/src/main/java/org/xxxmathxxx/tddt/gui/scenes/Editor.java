package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.editorpanes.*;
import org.xxxmathxxx.tddt.tracking.Tracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * @author Fabian
 *	Interface class to create an editor
 */
public class Editor extends Scene {

	//Editor Panes
	TestEditPane tep;
	CodeEditPane cep;
	
	
	//Teschebycheff's Tracker
	Tracker tracker;
	
	//Menus
	Pane pane;
	Button switchButton;
	
	//LoadedExercise
	Exercise ex;
	
	/**
	 * Constructor
	 * @param pane See Scene
	 * @param ex Exercise to be loaded
	 */
	public Editor(Pane pane, Exercise ex) {
		super(pane);
		this.ex=ex;
		this.pane=pane;
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		//Initialise Panes
		tep= new TestEditPane(ex.referencedTests);
		cep= new CodeEditPane(ex.referencedClasses);
		tep.switchActive();
		
		tep.relocate(10,10);
		
		cep.relocate(10,10);
		
		pane.getChildren().add(tep);
		pane.getChildren().add(cep);
		
		cep.setVisible(false);
		
		//Test
		switchButton= new Button("Switch!");
		switchButton.setPrefSize(100, 50);
		switchButton.relocate(xSize-200,ySize-150);
		switchButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(switchButton);
		
		
	}
	
	/**
	 * Switches state between Test and Code Editor.
	 */
	public void switchState()
	{
		if(tep.isActive())
		{
			tep.switchActive();
			cep.switchActive();
			tep.setVisible(false);
			cep.setVisible(true);

		}
		else if(cep.isActive())
		{
			tep.switchActive();
			cep.switchActive();
			cep.setVisible(false);
			tep.setVisible(true);

		}
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==switchButton){
				switchState();
			}
		}
	}
}