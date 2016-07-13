package org.xxxmathxxx.tddt.core;

import java.io.File;

import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.application.Application;
import javafx.stage.Stage;

/**The main class, this is a JavaFX application
 * @author xxxMathxxx 2016
 */
public class TDDT extends Application{


	/**This is the main function, it simply launches the JavaFX application
	 * @param args The command line arguments provided (Those are completely ignored)
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Runtime.getRuntime().addShutdownHook(new ShutdownThread());
		initializeFileSystem();
		TDDTLogManager.getInstance().logMessage("Starting application!");
		WindowManager.getInstance().showMenu(WindowManager.MenuType.STARTUP_MENU);
		WindowManager.getInstance().showStartupInfo();
	}
		
	/**This function initializes the file-system by creating all necessary folders and files.
	 * 
	 */
	private void initializeFileSystem(){
		//TODO: move elsewhere
		File stats = new File("profiles/stats");
		stats.mkdirs();
	}

}
