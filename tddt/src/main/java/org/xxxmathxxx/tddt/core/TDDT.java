package org.xxxmathxxx.tddt.core;

import java.io.File;

import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class TDDT extends Application{
	
	//visible from everywhere describes the currently running state
	public static TDDTThread currentThread;

	public static void main(String[] args) {
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());
		initializeFileSystem();
		TDDTLogManager.getInstance().logMessage("Starting application!");
		WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		WindowManager.getInstance().showStartupInfo();
	}
	
	public void initializeFileSystem(){
		//TODO: move elsewhere
		File stats = new File("profiles/stats");
		stats.mkdirs();
	}

}
