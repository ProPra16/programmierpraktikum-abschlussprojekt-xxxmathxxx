package org.xxxmathxxx.tddt.editorpanes;

import org.xxxmathxxx.tddt.data.ClassData;
import org.xxxmathxxx.tddt.data.ExerciseClass;
import org.xxxmathxxx.tddt.gui.EditPaneToggleButton;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

//import org.xxxmathxxx.tddt.gui.ide.TextEditor; Until its finished

import javafx.scene.layout.Pane;

public abstract class EditPane extends Pane {

	TextField te;
	Boolean isActive;

	String currentCode;

	int selectedPage;

	ToggleButton[] navigator;
	
	ClassData[] classdata;

	public EditPane(ClassData[] classdata) {
		isActive = false;

		te = new TextField();
		te.relocate(10, 50);
		te.setPrefSize(500, 450);

		
		this.classdata=classdata;
		
		getChildren().add(te);

		//Creating Navigator
		
		navigator = new ToggleButton[classdata.length];
		
		int tempNextX=10;
		
		ToggleGroup tg= new ToggleGroup();
		
		for (int i = 0; i < classdata.length; i++) {
			ToggleButton temp = new EditPaneToggleButton(classdata[i].name, i);
			temp.setPrefHeight(25);
			temp.relocate(tempNextX, 10);
			temp.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
			getChildren().add(temp);
			navigator[i] = temp;
			tempNextX=(int) (tempNextX+10+classdata[i].name.length()*7);
			temp.setToggleGroup(tg);
		}
		
		selectedPage = 0;
		navigator[0].setSelected(true);
		
		te.setText(classdata[0].code.rawText);

	}

	public Boolean isActive() {
		return isActive;
	}

	public void switchActive() {
		if (isActive)
			isActive = false;
		else
			isActive = true;
	}

	private void switchActiveDocument(int index) {
		if(index!=selectedPage)
		{
			loadPage(index);
		}
		else
		{
			navigator[index].setSelected(true);
		}
	}

	private void loadPage(int index) {
		
		classdata[selectedPage].code.rawText=te.getText();
		
		te.setText(classdata[index].code.rawText);
		selectedPage=index;
	}

	private final class menuButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if(event.getSource() instanceof EditPaneToggleButton)
			{
				switchActiveDocument(((EditPaneToggleButton)event.getSource()).index);
			}
		}
	}

}
