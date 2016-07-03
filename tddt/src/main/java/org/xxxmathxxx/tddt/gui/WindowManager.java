package org.xxxmathxxx.tddt.gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class WindowManager {
	
	static int width = 800;
	static int height = 600;
	
	private Stage mainStage;
	private Pane mainPane;
	
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
	

	public void setScene(Scene newScene){
		mainStage.setScene(newScene);
	}
	
}
