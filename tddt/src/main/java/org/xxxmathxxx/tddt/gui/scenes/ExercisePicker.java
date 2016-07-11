package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.core.TDDT;
import org.xxxmathxxx.tddt.core.TDDTThread;
import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.data.ExerciseCollection;
import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.gui.ExerciseComboBox;
import org.xxxmathxxx.tddt.gui.MedalViewer;
import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.io.ExerciseReader;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

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
 * @author xxxMathxxx 2016
 * Here an exercise should be picked 
 */
public class ExercisePicker extends Scene {

	private Label infoText;
	
	private Button start;	
	
	private ComboBox<Exercise> cb;
	
	private ExerciseCollection ec;
	
	private MedalViewer mv;
	
	private Label descr;
	
	/**
	 * Constructor for this Menu
	 * @param pane See Scene
	 */
	public ExercisePicker(Pane pane) {

		super(pane);
		
		infoText= new Label("Pick an exercise!");
		infoText.relocate(20,45);
		infoText.setFont(new Font("Times New Roman", 20));
		infoText.setTextAlignment(TextAlignment.LEFT);
		pane.getChildren().add(infoText);
		
		start= new Button("Start!");
		start.setPrefSize(150, 50);
		start.relocate(480,55);
		start.addEventHandler(ActionEvent.ANY, new menuButtonHandler());
		pane.getChildren().add(start);
		
		descr = new Label(); //STYLE THIS WHOLE GODDAMN THING BETTER
		descr.relocate(200, 135);
		pane.getChildren().add(descr);
		
		ExerciseReader temp=new ExerciseReader();
		ec=temp.readAllExercises();
		
		//exercises.add("Random"); make a seperate button for random stuff that gives you 200 TDDT-Coins
		cb = new ExerciseComboBox(ec.asObservableList());
		cb.relocate(20, 85);
		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Exercise>() {

			@Override
			public void changed(ObservableValue<? extends Exercise> arg, Exercise before, Exercise now) {
				if (now != null) {
					TDDTLogManager.getInstance().logMessage("Selected exercise: " + now.name);
					mv.setMedals(now, TDDTThread.getInstance().getUserProfile());
					descr.setText(now.description);
					descr.autosize();
				}
			}
		});
		pane.getChildren().add(cb);
		
		mv = new MedalViewer();
		mv.relocate(200, 50); //TODO: KRis design and stuff
		pane.getChildren().add(mv);
		
	}
	
	
	/**
	 * Handler for the different buttons in this menu
	 */
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (event.getSource()==start){
				Exercise selectedExercise=cb.getValue();
				
				if(selectedExercise!=null)
				{
					TDDTThread.getInstance().beginExercise(selectedExercise);
					WindowManager.getInstance().showMenu(WindowManager.MenuType.EDITOR);
				}
				else
				{
					AlertMessenger.showErrorMessage("No selection", "Please select an exercise!");
				}
			}
		}
	}
	
}
