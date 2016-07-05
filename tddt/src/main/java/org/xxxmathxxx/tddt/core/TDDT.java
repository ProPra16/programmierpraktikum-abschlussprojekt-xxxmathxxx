package org.xxxmathxxx.tddt.core;

import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.application.Application;
import javafx.stage.Stage;

public class TDDT extends Application{

	public static void main(String[] args) {
		launch(args);
		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());
		TDDTLogManager.getInstance().logMessage("Starting application!");
		WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		WindowManager.getInstance().showStartupInfo();
	}

}
