package org.xxxmathxxx.tddt.gui.ide;

import org.xxxmathxxx.tddt.data.ClassData;
import org.xxxmathxxx.tddt.gui.EditPaneToggleButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

//import org.xxxmathxxx.tddt.gui.ide.TextEditor; Until its finished

import javafx.scene.layout.Pane;

/**
 * @author Fabian
 * Used to calculate logic of the editor
 */
public abstract class EditPane extends Pane {
	

	private TextEditor te;
	private Boolean isActive;

	private int selectedPage;

	private ToggleButton[] navigator;
	
	private ClassData[] classdata;

	/**
	 * Used by Editor.java in package gui
	 * @param classdata
	 */
	public EditPane(ClassData[] classdata) {
		isActive = false;

		te = new TextEditor();
		te.setLocation(10, 50);
		te.setSize(500, 450);
		
		te.addEventHandler(MouseEvent.MOUSE_CLICKED, focusHelper);
		getChildren().add(te);
		this.classdata=classdata;
		

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
		te.requestFocus();
	}

	/**
	 * Returns is Pane is active
	 * @return activeness
	 */
	public Boolean isActive() {
		return isActive;
	}
	
	

	/**
	 * Inverts activeness
	 */
	public void switchActive() {
		isActive = !isActive;
	}

	/**
	 * Switches to specified document
	 * @param index document to be switched to
	 */
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

	/**
	 * Loads Document and saves old one
	 * @param index
	 */
	private void loadPage(int index) {
		
		classdata[selectedPage].code.rawText=te.getText();
		te.setText(classdata[index].code.rawText);
		selectedPage=index;
	}
	
	EventHandler<MouseEvent> focusHelper = new EventHandler<MouseEvent>(){

		@Override
		public void handle(MouseEvent event) {
			te.requestFocus();
		}
		
	};

	private final class menuButtonHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			if(event.getSource() instanceof EditPaneToggleButton)
			{
				switchActiveDocument(((EditPaneToggleButton)event.getSource()).index);
			}
		}
	}

	public void fixWindowsGraphicsBugs() {
		te.fixWindowsGraphicsBugs();
	}
}
