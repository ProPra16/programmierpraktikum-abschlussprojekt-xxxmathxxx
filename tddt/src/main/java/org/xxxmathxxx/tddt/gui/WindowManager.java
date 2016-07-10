package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.data.Exercise;
import org.xxxmathxxx.tddt.gui.hints.HintCollection;
import org.xxxmathxxx.tddt.gui.scenes.Editor;
import org.xxxmathxxx.tddt.gui.scenes.ExercisePicker;
import org.xxxmathxxx.tddt.gui.scenes.ExistingProfileMenu;
import org.xxxmathxxx.tddt.gui.scenes.NewProfileMenu;
import org.xxxmathxxx.tddt.gui.scenes.StartupMenu;
import org.xxxmathxxx.tddt.gui.scenes.StatsController;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowManager {
	
	private static int width = 800;
	private static int height = 600;
	
	private Stage mainStage;
	private Pane mainPane;
	
	private Exercise selectedExercise;
	
	public enum MenuType{
		STARTUP_MENU,
		NEW_PROFILE,
		EXISTING_PROFILE,
		STATISTICS,
		EDITOR,
		EXERCISEPICKER
	}
	
	private WindowManager(){
		

				
		mainStage = new Stage();
		mainStage.setTitle("TDDT - Main Menu");

		mainStage.setMaxWidth(width);
		mainStage.setMaxHeight(height);
		mainStage.setMinWidth(width);
		mainStage.setMinHeight(height);
	
		mainStage.setResizable(false);
		
		mainStage.show();
		mainStage.centerOnScreen();
		
	};
	
	private void generateNewMainPane(){
		mainPane = new Pane();
		mainPane.setPrefSize(width, height);
		
		mainPane.getStylesheets().add("org/xxxmathxxx/tddt/gui/MenuStyle.css");
	}
	
	private static WindowManager instance;
	
	public static WindowManager getInstance(){
		if (instance == null){
			instance = new WindowManager();
		}
		return instance;
	}
	

	public void showMenu(MenuType scene){
		
		generateNewMainPane();
		
		if (scene == MenuType.STARTUP_MENU){
			mainStage.setScene(new StartupMenu(mainPane));
		}
		if (scene == MenuType.EXISTING_PROFILE){
			mainStage.setScene(new ExistingProfileMenu(mainPane));
		}
		if (scene == MenuType.NEW_PROFILE){
			mainStage.setScene(new NewProfileMenu(mainPane));
		}
		if (scene == MenuType.STATISTICS){
			mainStage.setScene(new Scene(new StatsController(mainPane)));
		}
		if (scene == MenuType.EXERCISEPICKER){
			mainStage.setScene(new ExercisePicker(mainPane));
		}
		if (scene == MenuType.EDITOR){
			if(selectedExercise!=null)
			{
				mainStage.setScene(new Editor(mainPane, selectedExercise));
			}
			else
			{
				TDDTLogManager.getInstance().logMessage("Could not start editor. Maybe there is no exercise selected?");
			}
		}
	}

	public void showStartupInfo() {
		HintCollection.createStartupInfo(mainStage).show();
	}
	
	public void createAchievementPopup(MedalState medal){
		new AchievementPopup(medal).show(mainStage);
	}

	public String startImageCropper() {
		String ret = ImageCropperTool.showImageCropper(mainStage);
		return ret;
	}
	
	public void setSelectedExercise(Exercise ex) //??? maybe move to TDDTThread? or is this even obsolete?
	{
		this.selectedExercise=ex;
	}
	
}
