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
import javafx.scene.paint.Color;


/**The editor scene
 * @author xxxMathxxx 2016
 *	
 */
public class Editor extends Scene {
	

	//Editor Panes
	public TestEditPane tep;
	public CodeEditPane cep;
	
	
	private Button switchButton;
	private Button viewOtherside;
	private Button finalizeButton;
	private Button cancelButton;
	private Button quitButton;
	private Label stateLabel;
	
	//Boolean 
	Boolean nonEditState;
	
	/**
	 * Constructor
	 * @param pane See Scene
	 */
	public Editor(Pane pane) {
		super(pane);
		
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
		switchButton.relocate(xSize-200,ySize-230);
		switchButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(switchButton);
		
		viewOtherside= new Button("View Otherside!");
		viewOtherside.setPrefSize(150, 50);
		viewOtherside.relocate(xSize-200,ySize-290);
		viewOtherside.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(viewOtherside);
		
		finalizeButton= new Button("Finalize");
		finalizeButton.setPrefSize(150, 50);
		finalizeButton.relocate(xSize-200,36);
		finalizeButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(finalizeButton);
		
		cancelButton= new Button("Cancel");
		cancelButton.setPrefSize(150, 50);
		cancelButton.relocate(xSize-200,ySize-170);
		cancelButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(cancelButton);
		cancelButton.setDisable(true);
		
		quitButton= new Button("I am tired of this shit");
		quitButton.setPrefSize(150, 25);
		quitButton.relocate(xSize-200,ySize-70);
		quitButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(quitButton);
		
		//Label
		stateLabel= new Label();
		stateLabel.setText("Teststage: Write a failing test!");
		stateLabel.setTextFill(Color.web("#FF0000"));
		stateLabel.setPrefSize(400, 50);
		stateLabel.relocate(10,ySize-100);
		pane.getChildren().add(stateLabel);
		
		//Boolean
		nonEditState=false;
		
		//register @ TDDTTHread
		TDDTThread.getInstance().setEditor(this);
	}
	
	/**
	 * Updates StateLabel to currentState, also updates othersidebutton now
	 */
	public void updateStateLabel()
	{
		switch(TDDTThread.getInstance().getState())
		{
		case TEST: 
			stateLabel.setText("Teststage: Write a failing test!");
			stateLabel.setTextFill(Color.web("#FF0000"));
			cancelButton.setDisable(true);
			viewOtherside.setDisable(false);
			break;
			
		case CODE: 
			stateLabel.setText("Codestage: Make the code pass the test!");
			stateLabel.setTextFill(Color.web("#008000"));
			cancelButton.setDisable(false);
			break;
			
		case REFACTOR: 
			stateLabel.setText("Refactorstage: Make your code look good!");
			stateLabel.setTextFill(Color.web("#0000FF"));
			viewOtherside.setDisable(true);
			cancelButton.setDisable(true);
			break;
		}	
	}
	
	/**
	 * Updates Edit panes so they are displayed correctly
	 */
	public void updateEditPanes()
	{
		switch(TDDTThread.getInstance().getState())
		{
		case TEST:
			tep.setActive(true);
			cep.setActive(false);
			tep.setVisible(true);
			cep.setVisible(false);
			cep.fixWindowsGraphicsBugs();
			break;
		case CODE:
			tep.setActive(false);
			cep.setActive(true);
			tep.setVisible(false);
			cep.setVisible(true);
			cep.fixWindowsGraphicsBugs();
			break;
			
		case REFACTOR:
			tep.setActive(false);
			cep.setActive(true);
			tep.setVisible(false);
			cep.setVisible(true);
			cep.fixWindowsGraphicsBugs();
			break;
		}
		
	}
	
	/**
	 * Shows the other side. Non editable of course.
	 * @param state
	 */
	private void showOtherside()
	{
		switch(TDDTThread.getInstance().getState())
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
			if (event.getSource()==switchButton && !nonEditState)
			{
				
				TDDTThread.getInstance().requestSwitch();
				updateEditPanes();
				updateStateLabel();	
				
			}
			
			//Otherside Button
			if (event.getSource()==viewOtherside)
			{
				showOtherside();
			}
			
			//FinalizeButton
			if(event.getSource()==finalizeButton)
			{
				TDDTThread.getInstance().finalizeExercise();
			}
			
			//CancelButton
			if(event.getSource()==cancelButton&& TDDTThread.getInstance().getState()==CodeStage.CODE)
			{
				TDDTThread.getInstance().cancelRequested();
				updateEditPanes();
				updateStateLabel();	
			}
			
			if(event.getSource()==quitButton)
			{
				TDDTThread.getInstance().exitRequest();
			}
		}
	}
}
