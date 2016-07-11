package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.CodeStage;
import org.xxxmathxxx.tddt.gui.ide.CodeEditPane;
import org.xxxmathxxx.tddt.gui.ide.TestEditPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


/**The editor scene
 * @author xxxMathxxx 2016
 *	
 */
public class Editor extends Scene {
	

	//Editor Panes
	public TestEditPane tep;
	public CodeEditPane cep;
	
	//Menus
	Pane pane;
	Button switchButton;
	Button viewOtherside;
	Button finalizeButton;
	Button cancelButton;
	Label stateLabel;
	
	//Boolean 
	Boolean nonEditState;
	
	/**
	 * Constructor
	 * @param pane See Scene
	 */
	public Editor(Pane pane) {
		super(pane);
		this.pane=pane;
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		//Initialise Panes
		tep= new TestEditPane(TDDTThread.getInstance().getExercise().referencedTests);
		cep= new CodeEditPane(TDDTThread.getInstance().getExercise().referencedClasses);
		tep.switchActive();
		
		tep.relocate(10,10);
		
		cep.relocate(10,10);
		
		pane.getChildren().add(tep);
		pane.getChildren().add(cep);
		
		cep.setVisible(false);
		
		//Buttons
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
		
		finalizeButton= new Button("Finalize");
		finalizeButton.setPrefSize(150, 50);
		finalizeButton.relocate(xSize-200,10);
		finalizeButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(finalizeButton);
		
		cancelButton= new Button("Cancel");
		cancelButton.setPrefSize(150, 50);
		cancelButton.relocate(xSize-200,ySize-90);
		cancelButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(cancelButton);
		cancelButton.setDisable(true);
		
		//Label
		stateLabel= new Label("Teststage");
		stateLabel.setPrefSize(100, 50);
		stateLabel.relocate(10,ySize-100);
		pane.getChildren().add(stateLabel);
		
		//Tschebycheff
		TDDTThread.getInstance().tracker.stageRed.startTimeTracking();
		
		//Boolean
		nonEditState=false;
		
		//register @ TDDTTHread
		TDDTThread.getInstance().setEditor(this);
		
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
			cancelButton.setDisable(true);
			viewOtherside.setDisable(false);
			break;
			
		case CODE: 
			stateLabel.setText("CodeStage");
			cancelButton.setDisable(false);
			break;
			
		case REFACTOR: 
			stateLabel.setText("refactorstage");
			viewOtherside.setDisable(true);
			cancelButton.setDisable(true);
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
			cancelButton.setDisable(true);
			nonEditState=true;
			
			tep.setVisible(true);
			cep.setVisible(false);
			
			tep.switchDisabeled();
		}
		else
		{
			switchButton.setDisable(false);
			cancelButton.setDisable(false);
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
			//SwitchButton
			if (event.getSource()==switchButton && !nonEditState){
				tep.save();
				cep.save();
				boolean hasSwitched = TDDTThread.getInstance().requestSwitch();

				if (hasSwitched){ //this means a change has occured!
					
					if(TDDTThread.getInstance().state!=CodeStage.REFACTOR)
					{
						switchLabel();
					}
					updateStateLabel(TDDTThread.getInstance().state);	
					tep.createBackup();
					cep.createBackup();
				}
			}
			
			//Otherside Button
			if (event.getSource()==viewOtherside)
			{
				showOtherside(TDDTThread.getInstance().state);
			}
			
			//FinalizeButton
			if(event.getSource()==finalizeButton)
			{
				TDDTThread.getInstance().finalizeExercise();
			}
			
			//CancelButton
			if(event.getSource()==cancelButton&& TDDTThread.getInstance().state==CodeStage.CODE)
			{
				TDDTThread.getInstance().cancelRequested();
				switchLabel();
				updateStateLabel(TDDTThread.getInstance().state);	
			}
		}
	}
}
