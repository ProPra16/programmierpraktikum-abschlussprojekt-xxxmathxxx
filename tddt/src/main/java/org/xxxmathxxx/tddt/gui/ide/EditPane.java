package org.xxxmathxxx.tddt.gui.ide;

import org.xxxmathxxx.tddt.data.ClassData;
import org.xxxmathxxx.tddt.gui.ClockLabel;
import org.xxxmathxxx.tddt.gui.EditPaneToggleButton;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

/**
 * @author Fabian
 * Used to calculate logic of the editor
 */
public abstract class EditPane extends FlowPane {
	

	protected TextEditor te;
	private Boolean isActive;
	
	private ClockLabel clock;

	protected int selectedPage;

	private ToggleButton[] navigator;
	
	public ClassData[] classdata;
	private String[] backupClassdata;

	/**
	 * Used by Editor.java in package gui
	 * @param classdata an arry containing classdata?
	 */
	public EditPane(ClassData[] classdata) {
		isActive = false;

		this.clock = new ClockLabel(3.00d); //TODO: Add exercise babysteps time
		this.clock.setPrefSize(32, 32);
		this.clock.relocate(128, 8);
		getChildren().add(clock);
		
		//Creating Navigator
		
		navigator = new ToggleButton[classdata.length];
		
		int tempNextX=10;
		
		ToggleGroup tg= new ToggleGroup();
		
		for (int i = 0; i < classdata.length; i++) {
			ToggleButton temp = new EditPaneToggleButton(classdata[i].name, i);
			temp.setPrefHeight(25);
			//temp.relocate(tempNextX, 10);
			temp.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
			getChildren().add(temp);
			navigator[i] = temp;
			tempNextX=(int) (tempNextX+10+classdata[i].name.length()*7);
			temp.setToggleGroup(tg);
		}
		
		//Editor
		te = new TextEditor();
		te.setLocation(10, 50);
		te.setSize(500, 450);
		
		te.addEventHandler(MouseEvent.MOUSE_CLICKED, focusHelper);
		getChildren().add(te);
		this.classdata=classdata;
		
		
		
		
		selectedPage = 0;
		navigator[0].setSelected(true);
		
		te.setText(classdata[0].code.rawText);
		te.requestFocus();
		
		backupClassdata= new String[classdata.length];
		
		
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
	protected void loadPage(int index) {
		
		classdata[selectedPage].code.rawText=te.getText();
		te.setText(classdata[index].code.rawText);
		selectedPage=index;
	}
	
	public void switchDisabeled()
	{
		if(!te.isDisable())
		{
			te.setDisable(true);
		}
		else
		{
			te.setDisable(false);
		}
	}
	
	/**
	 * Actualizes content of ClassData[]
	 */
	public void save()
	{
		classdata[selectedPage].code.rawText=te.getText();
	}
	
	/**
	 * Creates backup of content.
	 */
	public void createBackup() {
		System.out.println("Saving current state as backup");

		for(int i=0; i<classdata.length;i++)
		{
			backupClassdata[i]=classdata[i].code.rawText;
		}
	}
	
	/**
	 * Rerolls changes to the state when createBackup() was called
	 */
	public void rerollChanges()
	{
		for(int i=0; i<classdata.length;i++)
		{
			classdata[i].code.rawText=backupClassdata[i];
		}
		
		te.setText(backupClassdata[selectedPage]);
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
