package org.xxxmathxxx.tddt.core;

import java.io.File;

import org.xxxmathxxx.tddt.gui.WindowManager;
import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.application.Application;
import javafx.stage.Stage;

/**The main class, this is a JavaFX application
 * @author philipp
 *
 */
public class TDDT extends Application{
	
	/**This is the current thread, visible from every class and thus globally accessible.
	 * 
	 */
	public static TDDTThread currentThread;

	/**This is the main function, it simply launches the JavaFX application
	 * @param args
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
	
	//TODO: move this function (initializeFileSystem) to a separate IO-class
	
	/**This function intializes the file-system by creating all necessarry folders and files.
	 * 
	 */
	public void initializeFileSystem(){
		//TODO: move elsewhere
		File stats = new File("profiles/stats");
		stats.mkdirs();
	}

}
