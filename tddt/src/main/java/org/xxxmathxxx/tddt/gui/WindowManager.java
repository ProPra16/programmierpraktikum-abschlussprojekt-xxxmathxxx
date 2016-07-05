package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.gui.scenes.StartupMenu;
import org.xxxmathxxx.tddt.gui.scenes.ExistingProfileMenu;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowManager {
	
	static int width = 800;
	static int height = 600;
	
	private Stage mainStage;
	private Pane mainPane;
	
	public enum MenuType{
		STARTUP_MENU,
		NEW_PROFILE,
		EXISTING_PROFILE,
		STATISTICS
	}
	
	private WindowManager(){
		
		mainPane = new Pane();
		mainPane.setPrefSize(width, height);
		
		mainStage = new Stage();
		mainStage.setTitle("TDDT - Main Menu");

		
		mainStage.setMaxWidth(width);
		mainStage.setMaxHeight(height);
		mainStage.setMinWidth(width);
		mainStage.setMinHeight(height);
	
		mainStage.setResizable(false);
		
		mainStage.show();


	};
	
	public Pane getMainPane(){
		return mainPane;
	}
	private static WindowManager instance;
	
	public static WindowManager getInstance(){
		if (instance == null){
			instance = new WindowManager();
		}
		return instance;
	}
	

	public void showMenu(MenuType scene){
		if (scene == MenuType.STARTUP_MENU){
			mainStage.setScene(new StartupMenu(mainPane));
		}
		if (scene == MenuType.EXISTING_PROFILE){
			mainStage.setScene(new ExistingProfileMenu(mainPane));
		}
		if (scene == MenuType.NEW_PROFILE){
			new NewProfileMenu(mainStage).show();
		}
		if (scene == MenuType.STATISTICS){
			//WHATEVER
		}
	}
	
}
