package org.xxxmathxxx.tddt.gui.scenes;


import javax.swing.SwingUtilities;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.ide.CodeEditPane;
import org.xxxmathxxx.tddt.gui.ide.TestEditPane;

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
	
	private Editor self = this; //reference for button handler

	//Editor Panes
	public TestEditPane tep;
	public CodeEditPane cep;
	
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
		stateLabel.relocate(10,ySize-100);
		pane.getChildren().add(stateLabel);
		
		//Tschebycheff
		TDDT.currentThread.tracker.stageRed.startTimeTracking();
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
	 * Updates StateLabel to currentState
	 */
	private void updateStateLabel(CodeStage state)
	{
		switch(state)
		{
		case TEST: 
			stateLabel.setText("Teststage");
			break;
			
		case CODE: 
			stateLabel.setText("CodeStage");
			break;
			
		case REFACTOR: 
			stateLabel.setText("refactorstage");
			break;
		}	
	}
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==switchButton){
				tep.save();
				cep.save();
				boolean hasSwitched = TDDT.currentThread.requestSwitch(TDDT.currentThread.state,self);

				if (hasSwitched){ //this means a change has occured!
					SwingUtilities.invokeLater( //don't think too much about it swing and javafx is bugged as f***
							new Runnable(){
								@Override
								public void run() 
								{
									switchLabel();
									updateStateLabel(TDDT.currentThread.state);									
								}
							}
						);
				}
			}
		}
	}
}
