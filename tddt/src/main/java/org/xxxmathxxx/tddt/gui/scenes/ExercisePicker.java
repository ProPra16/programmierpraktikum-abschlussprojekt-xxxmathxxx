package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ExercisePicker extends Scene {

	private Label infoText;
	private Button start;
	
	
	public ExercisePicker(Pane pane) {

		super(pane);
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		infoText= new Label("Pick an exercise! (Currently none selectable, just a test!)");
		infoText.setPrefSize(240, 32);
		infoText.relocate((xSize/2)+10,100);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		
		start= new Button("Start!");
		start.setPrefSize(150, 50);
		start.relocate(xSize/2+45,ySize - 430);
		start.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(start);
	}
	
	
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==start){
				WindowManager.getInstance().showMenu(WindowManager.MenuType.EDITOR);
			}
		}
	}
}
