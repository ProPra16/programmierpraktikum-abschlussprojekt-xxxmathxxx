package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.gui.scenes.StartupMenu;
import org.xxxmathxxx.tddt.gui.hints.HintCollection;
import org.xxxmathxxx.tddt.gui.scenes.ExistingProfileMenu;
import org.xxxmathxxx.tddt.gui.scenes.NewProfileMenu;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
		IMAGE_CROPPER
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
		if (scene == MenuType.IMAGE_CROPPER){
			new ImageCropperTool("graphics/test.jpg",mainStage).show(); //for testing purpose
		}
		if (scene == MenuType.STATISTICS){
			//WHATEVER
		}
	}

	public void showStartupInfo() {
		HintCollection.createStartupInfo(mainStage).show();
	}
	
}
