package org.xxxmathxxx.tddt.gui.scenes;

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
	Button viewOtherside;
	Label stateLabel;
	
	//LoadedExercise
	Exercise ex;
	
	//Boolean 
	Boolean nonEditState;
	
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
		switchButton.setPrefSize(150, 50);
		switchButton.relocate(xSize-200,ySize-150);
		switchButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(switchButton);
		
		viewOtherside= new Button("View Otherside!");
		viewOtherside.setPrefSize(150, 50);
		viewOtherside.relocate(xSize-200,ySize-210);
		viewOtherside.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(viewOtherside);
		
		
		//Label
		stateLabel= new Label("Teststage");
		stateLabel.setPrefSize(100, 50);
		stateLabel.relocate(10,ySize-100);
		pane.getChildren().add(stateLabel);
		
		//Tschebycheff
		TDDT.currentThread.tracker.stageRed.startTimeTracking();
		
		//Boolean
		nonEditState=false;
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
			cep.fixWindowsGraphicsBugs();

		}
		else if(cep.isActive())
		{
			tep.switchActive();
			cep.switchActive();
			cep.setVisible(false);
			tep.setVisible(true);
			tep.fixWindowsGraphicsBugs();
		}
	}
	
	/**
	 * Updates StateLabel to currentState, also updates othersidebutton now
	 */
	private void updateStateLabel(CodeStage state)
	{
		switch(state)
		{
		case TEST: 
			stateLabel.setText("Teststage");
			viewOtherside.setDisable(false);
			break;
			
		case CODE: 
			stateLabel.setText("CodeStage");
			break;
			
		case REFACTOR: 
			stateLabel.setText("refactorstage");
			viewOtherside.setDisable(true);
			break;
		}	
	}
	
	/**
	 * Shows the other side. Non editable of course
	 * @param state
	 */
	private void showOtherside(CodeStage state)
	{
		switch(state)
		{
		case TEST: 
			switchShowCode();
			break;
			
		case CODE: 
			switchShowTest();
			break;
			
		default: //Nasty eclipsewarningsses
			break;
		}
	}
	
	/**
	 * Helper for showOtherside()
	 */
	private void switchShowTest() 
	{
		if(!nonEditState)
		{
			switchButton.setDisable(true);
			nonEditState=true;
			
			tep.setVisible(true);
			cep.setVisible(false);
			
			tep.switchDisabeled();
		}
		else
		{
			switchButton.setDisable(false);
			nonEditState=false;
			
			tep.setVisible(false);
			cep.setVisible(true);
			
			tep.switchDisabeled();
		}	
	}

	/**
	 * Helper for showOtherside()
	 */
	private void switchShowCode() 
	{
		if(!nonEditState)
		{
			switchButton.setDisable(true);
			nonEditState=true;
			
			tep.setVisible(false);
			cep.setVisible(true);
			
			cep.switchDisabeled();
		}
		else
		{
			switchButton.setDisable(false);
			nonEditState=false;
			
			tep.setVisible(true);
			cep.setVisible(false);
			
			cep.switchDisabeled();
		}	
		
	}

	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==switchButton && !nonEditState){
				tep.save();
				cep.save();
				boolean hasSwitched = TDDT.currentThread.requestSwitch(self);

				if (hasSwitched){ //this means a change has occured!
					switchLabel();
					updateStateLabel(TDDT.currentThread.state);		
				}
			}
			if (event.getSource()==viewOtherside)
			{
				showOtherside(TDDT.currentThread.state);
			}
		}
	}
}
