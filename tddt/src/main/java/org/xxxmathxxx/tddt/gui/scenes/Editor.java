package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.editorpanes.*;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.tracking.Tracker;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Editor extends Scene {

	//Editor Panes
	TestEditPane tep;
	CodeEditPane cep;
	
	
	//Teschebycheff's Tracker
	Tracker tracker;
	
	Pane pane;
	Button switchButton;
	
	public Editor(Pane pane) {
		super(pane);
		
		this.pane=pane;
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		//Initialise Panes
		tep= new TestEditPane();
		cep= new CodeEditPane();
		tep.switchActive();
		
		tep.relocate(10,10);
		
		cep.relocate(10,10);
		
		pane.getChildren().add(tep);
		
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
			pane.getChildren().remove(tep);
			pane.getChildren().add(cep);
		}
		else if(cep.isActive())
		{
			tep.switchActive();
			cep.switchActive();
			pane.getChildren().remove(cep);
			pane.getChildren().add(tep);
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
