package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseCollection;
import org.xxxmathxxx.tddt.gui.ExerciseComboBox;
import org.xxxmathxxx.tddt.gui.MedalViewer;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	private boolean debugHorst = false;
	private Button debugButton;
	
	
	private ComboBox<Exercise> cb;
	
	private ExerciseCollection ec;
	
	private MedalViewer mv;
	
	
	public ExercisePicker(Pane pane) {

		super(pane);
		
		double xSize = pane.getPrefWidth();
		double ySize = pane.getPrefHeight();
		
		infoText= new Label("Pick an exercise!");
		infoText.relocate(10,10);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		debugButton = new Button("DEBUG GIVE MEDAL!");
		debugButton.setPrefSize(200, 200);
		debugButton.relocate(10, 300);
		debugButton.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(debugButton);
		
		start= new Button("Start!");
		start.setPrefSize(150, 50);
		start.relocate(xSize/2+45,ySize - 430);
		start.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(start);
		
		ExerciseReader temp=new ExerciseReader();
		ec=temp.readAllExercises();
		
		//exercises.add("Random"); make a seperate button for random stuff that gives you 200 TDDT-Coins
		cb = new ExerciseComboBox(ec.asObservableList());
		cb.relocate(10, 50);
		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Exercise>() {

			@Override
			public void changed(ObservableValue<? extends Exercise> arg, Exercise before, Exercise now) {
				if (now != null) {
					TDDTLogManager.getInstance().logMessage("Selected exercise: " + now.name);
					mv.setMedals(now, TDDT.currentThread.getUserProfile());
				}
			}
		});
		pane.getChildren().add(cb);
		
		mv = new MedalViewer();
		mv.relocate(200, 50); //TODO: KRis design and stuff
		pane.getChildren().add(mv);
		
	}
	
	
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==start){
				Exercise selectedExercise=cb.getValue();
				
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
			if (event.getSource() == debugButton){
				if (debugHorst == false){
					TDDT.currentThread.awardMedal(cb.getValue().id,MedalState.SILVER);
					debugHorst = true;
				}
				else{
					TDDT.currentThread.awardMedal(cb.getValue().id,MedalState.AUTHOR);
				}
				mv.setMedals(cb.getValue(),TDDT.currentThread.getUserProfile());
			}
		}
	}
	
}
