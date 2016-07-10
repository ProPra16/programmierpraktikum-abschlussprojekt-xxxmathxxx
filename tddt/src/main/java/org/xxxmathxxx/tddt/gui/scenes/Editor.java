package org.xxxmathxxx.tddt.gui.scenes;

import java.util.Iterator;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.ide.CodeEditPane;
import org.xxxmathxxx.tddt.gui.ide.TestEditPane;
import org.xxxmathxxx.tddt.tracking.Tracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import vk.core.api.CompilationUnit;
import vk.core.api.CompileError;
import vk.core.api.CompilerFactory;
import vk.core.api.JavaStringCompiler;

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
	Label errorLabel;
	
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
		stateLabel.relocate(10,ySize-100);
		pane.getChildren().add(stateLabel);
		
		//Errorlabel
		errorLabel= new Label();
		errorLabel.setPrefSize(200, 400);
		errorLabel.relocate(xSize-250,10);
		pane.getChildren().add(errorLabel);
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
			if(switchToCode()) //TODO: Test if one test fails
			{
				switchLabel();
				state=1;
				updateStateLabel();
				errorLabel.setText("");
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
	 * Checks if one failed test is present
	 * @return
	 */
	private Boolean switchToCode()
	{
		
		CompilationUnit[] cuArray= getCompilationUnits();
		
		JavaStringCompiler jsc= CompilerFactory.getCompiler(cuArray);
		
		jsc.compileAndRunTests();
		
		System.out.println(jsc.getCompilerResult().hasCompileErrors());
		
		String retardedFishFrogString="";
		
		if(jsc.getCompilerResult().hasCompileErrors())
		{	
			retardedFishFrogString=retardedFishFrogString+"CompileErrors found: \n";
			
			for(int i=0; i<cuArray.length; i++)
			{
				Iterator<CompileError> errors=jsc.getCompilerResult().getCompilerErrorsForCompilationUnit(cuArray[i]).iterator();
						
				while(errors.hasNext())
				{
					retardedFishFrogString=retardedFishFrogString+((CompileError) errors.next()).getMessage();
				}
			}
			
			errorLabel.setText(retardedFishFrogString);
			return false;
		}
		else
		{
			if(jsc.getTestResult().getNumberOfFailedTests()==1)
			{
				return true;
			}
			else
			{
				retardedFishFrogString=retardedFishFrogString+"More/ Less than 1 one Test failed.";
				errorLabel.setText(retardedFishFrogString);
			}
		}
		
		return false;
	}
	
	/**
	 * Creates an Array of CompilationUnits to start compiling.
	 * @return Array of CompilationUnits
	 */
	public CompilationUnit[] getCompilationUnits()
	{
		tep.save();
		cep.save();
		
		int addedLength=cep.classdata.length+tep.classdata.length;
		
		CompilationUnit[] cuArray= new CompilationUnit[addedLength];
		
		for(int i=0; i<cep.classdata.length;i++)
		{
			cuArray[i]=new CompilationUnit(cep.classdata[i].name, cep.classdata[i].code.rawText, false);
		}
		
		for(int i=cep.classdata.length; i<addedLength;i++)
		{
			cuArray[i]=new CompilationUnit(tep.classdata[i-cep.classdata.length].name, tep.classdata[i-cep.classdata.length].code.rawText, true);
		}
		
		return cuArray;
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
