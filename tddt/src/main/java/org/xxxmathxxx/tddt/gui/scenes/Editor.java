package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.editorpanes.*;
import org.xxxmathxxx.tddt.tracking.Tracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * @author Fabian
 *	Interface class to create an editor
 */
public class Editor extends Scene {

	//Editor Panes
	TestEditPane tep;
	CodeEditPane cep;
	
	//Editstate
	Byte state;
	/**
	 * state 0= test
	 * state 1= code
	 * state 2= refractor
	 */
	
	//Teschebycheff's Tracker
	Tracker tracker;
	
	//Menus
	Pane pane;
	Button switchButton;
	Label stateLabel;
	
	//LoadedExercise
	Exercise ex;
	
	/**fgcgh
	 * Constructor
	 * @param pane See Scene
	 * @param ex Exercise to be loaded
	 */
	public Editor(Pane pane, Exercise ex) {
		super(pane);
		this.ex=ex;
		this.pane=pane;
		
		state=0;
		
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
		
		//Button
		switchButton= new Button("Switch!");
		switchButton.setPrefSize(100, 50);
		switchButton.relocate(xSize-200,ySize-150);
		switchButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(switchButton);
		
		//Label
		stateLabel= new Label("Teststage");
		stateLabel.setPrefSize(100, 50);
		stateLabel.relocate(10,ySize-150);
		pane.getChildren().add(stateLabel);
	}
	
	/**
	 * Switches state between Test and Code Editor.
	 */
	public void switchLabel()
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
	
	/**
	 * Called when a stateswitch was requested by the User
	 */
	public void switchRequested()
	{
		switch(state)
		{
		case 0: //Switch to code
			if(true) //TODO: Test if one test fails
			{
				switchLabel();
				state=1;
				updateStateLabel();
			}
			break;
			
		case 1: //Switch to refractor
			if(true) //TODO: Test if code compiles and no test are failing
			{
				state=2;
				updateStateLabel();
			}
			break;
			
		case 2: //Switch to test
			switchLabel();
			state=0;
			updateStateLabel();
			break;
		}	
	}
	
	/**
	 * Updates StateLabel to currentState
	 */
	private void updateStateLabel()
	{
		switch(state)
		{
		case 0: 
			stateLabel.setText("Teststage");
			break;
			
		case 1: 
			stateLabel.setText("CodeStage");
			break;
			
		case 2: 
			stateLabel.setText("Refractorstage");
			break;
		}	
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==switchButton){
				switchRequested();
			}
		}
	}
}
