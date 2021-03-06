package org.xxxmathxxx.tddt.gui.ide;

import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.ClassData;
import org.xxxmathxxx.tddt.gui.ClockLabel;
import org.xxxmathxxx.tddt.gui.EditPaneToggleButton;
import org.xxxmathxxx.tddt.gui.ReversedClockLabel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import vk.core.api.CompilationUnit;

/**
 * @author Fabian
 * Used to calculate logic of the editor
 */
public abstract class EditPane extends FlowPane {
	

	private TextEditor te;
	private Boolean isActive;
	
	private ReversedClockLabel babyClock;
	private ClockLabel totalClock;
	

	private int selectedPage;

	private ToggleButton[] navigator;
	
	public ClassData[] classdata;

	/**
	 * Used by Editor.java in package gui
	 * @param classdata an arry containing classdata?
	 */
	public EditPane(ClassData[] classdata) {
		isActive = false;
		
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
		te.setSize(580, 450);
		
		te.addEventHandler(MouseEvent.MOUSE_CLICKED, focusHelper);
		getChildren().add(te);
		this.classdata=classdata;
		
		//navigator
		selectedPage = 0;
		navigator[0].setSelected(true);
		
		te.setText(classdata[0].code.rawText);
		te.requestFocus();
				
		//Adds clocks
		
		if(TDDTThread.getInstance().getExercise().config.babystepsEnabeled)
		{
			this.babyClock = new ReversedClockLabel(TDDTThread.getInstance().babystepsTimer); 
			this.babyClock.setPrefSize(250, 16);
			getChildren().add(babyClock);
		}
		
		if(TDDTThread.getInstance().getExercise().config.trackingEnabled)
		{
			this.totalClock = new ClockLabel(TDDTThread.getInstance().totalTimer); 
			this.totalClock.setPrefSize(250, 16);
			getChildren().add(totalClock);
		}
		
		
		
	}


	/**
	 * Inverts activeness
	 */
	public void switchActive() {
		isActive = !isActive;
	}
	
	public void setActive(Boolean a)
	{
		isActive=a;
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
	 * Rerolls pane to previous state
	 * @param compilationUnits desired state
	 */
	public void rerollTo(CompilationUnit[] compilationUnits)
	{
		if(compilationUnits.length!=0)
		{
		
			for(int i=0; i<compilationUnits.length;i++)
			{
				for(int j=0; j<classdata.length;j++)
				{
					if(classdata[j].name.equals(compilationUnits[i].getClassName()))
					{
						classdata[j].code.rawText=compilationUnits[i].getClassContent();
					}
				}
			}
			te.setText(classdata[selectedPage].code.rawText);
		}
		else
		{
			if(this instanceof TestEditPane)
			{
				classdata=TDDTThread.getInstance().getExercise().referencedTests;
			}
			else
			{
				classdata=TDDTThread.getInstance().getExercise().referencedClasses;
			}
		}
	}
	
	private EventHandler<MouseEvent> focusHelper = new EventHandler<MouseEvent>(){

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
