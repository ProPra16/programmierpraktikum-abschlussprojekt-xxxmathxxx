package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseCollection;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * @author Fabian
 * Here a exercise should be picked 
 */
public class ExercisePicker extends Scene {

	private Label infoText;
	private Button start;
	private ComboBox cb;
	
	private ExerciseCollection ec;
	
	
	public ExercisePicker(Pane pane) {

		super(pane);
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		infoText= new Label("Pick an exercise!");
		infoText.relocate(10,10);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		
		start= new Button("Start!");
		start.setPrefSize(150, 50);
		start.relocate(xSize/2+45,ySize - 430);
		start.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(start);
		
		ExerciseReader temp=new ExerciseReader();
		ec=temp.readAllExercises();
		
		
		ObservableList<String> exercises = FXCollections.observableArrayList();
		
		for(int i=0; i<ec.getLength();i++)
		{
			exercises.add(ec.getExercise(i).name);
		}
		
		exercises.add("Random");
		cb = new ComboBox(exercises);
		cb.relocate(10, 50);
		cb.setPromptText("Exercise");
		pane.getChildren().add(cb);
	}
	
	
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==start){
				
				//Ugly
				String temp= (String) cb.getValue();
				Exercise selectedExercise=null;
				
				for(int i=0; i<ec.getLength();i++)
				{
					if(ec.getExercise(i).name.equals(temp))
					{
						selectedExercise=ec.getExercise(i);
					}
				}
				
				if(selectedExercise!=null)
				{
					WindowManager.getInstance().setSelectedExercise(selectedExercise);
					WindowManager.getInstance().showMenu(WindowManager.MenuType.EDITOR);
				}
				else
				{
					infoText.setText("Please select an exercise!");
				}
			}
		}
	}
}
