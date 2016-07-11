package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.gui.hints.HintCollection;
import org.xxxmathxxx.tddt.gui.scenes.Editor;
import org.xxxmathxxx.tddt.gui.scenes.ExercisePicker;
import org.xxxmathxxx.tddt.gui.scenes.ExistingProfileMenu;
import org.xxxmathxxx.tddt.gui.scenes.NewProfileMenu;
import org.xxxmathxxx.tddt.gui.scenes.StartupMenu;
import org.xxxmathxxx.tddt.gui.scenes.Statistics;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowManager {
	
	private static int width = 800;
	private static int height = 600;
	
	private Stage mainStage;
	private Pane mainPane;
		
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
		
		mainStage.setOnCloseRequest(windowCloseEvent);
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
			mainStage.setScene(new Statistics(mainPane));
		}
		if (scene == MenuType.EXERCISEPICKER){
			mainStage.setScene(new ExercisePicker(mainPane));
		}
		if (scene == MenuType.EDITOR){
			mainStage.setScene(new Editor(mainPane));
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
	
	EventHandler<WindowEvent> windowCloseEvent = new EventHandler<WindowEvent>(){

		@Override
		public void handle(WindowEvent event) {
			Runtime.getRuntime().exit(0);
		}
		
	};
	
}
