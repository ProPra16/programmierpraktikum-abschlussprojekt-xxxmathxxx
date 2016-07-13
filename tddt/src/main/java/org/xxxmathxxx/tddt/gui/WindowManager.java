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

/**This class handles all JavaFX Stages and notifications by providing useful functions.
 * This class is a singleton, there is only one instance at a given time.
 * @author xxxMathxxx 2016
 *
 */
public class WindowManager {
	
	/**
	 * We are using the Diablo II resolution because it is the best resolution
	 */
	private static int width = 800;
	
	/**
	 * We are using the Diablo II resolution because it is the best resolution
	 */
	private static int height = 600;
	
	/**
	 * The Stage (JavaFX Window) in which most of the content is rendered
	 */
	private Stage mainStage;
	
	/**
	 * The Pane (JavaFX LayoutScheme) in which most of the content is rendered
	 */
	private Pane mainPane;
		
	/**This enum describes the different MenuTypes existing, it simply helps making the code more readable
	 * @author xxxMathxxx 2016
	 *
	 */
	public enum MenuType{
		STARTUP_MENU,
		NEW_PROFILE,
		EXISTING_PROFILE,
		STATISTICS,
		EDITOR,
		EXERCISEPICKER
	}
	
	/**
	 * Hidden private constructor, initializes a new WindowManager and basic components
	 */
	private WindowManager(){
				
		mainStage = new Stage();
		mainStage.setTitle("Test Driven Development Trainer");

		mainStage.setMaxWidth(width);
		mainStage.setMaxHeight(height);
		mainStage.setMinWidth(width);
		mainStage.setMinHeight(height);
	
		mainStage.setResizable(false);
		
		mainStage.setOnCloseRequest(windowCloseEvent);
		mainStage.show();
		mainStage.centerOnScreen();
		
	};
	
	/**
	 * 
	 */
	private void generateNewMainPane(){
		mainPane = new Pane();
		mainPane.setPrefSize(width, height);
		
		mainPane.getStylesheets().add(
				GraphicsHelper.getResourcePath("/MenuStyle.css")
		);
	}
	
	/**
	 * 
	 */
	private static WindowManager instance;
	
	/**
	 * @return
	 */
	public static WindowManager getInstance(){
		if (instance == null){
			instance = new WindowManager();
		}
		return instance;
	}
	

	/**
	 * @param scene
	 */
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

	/**
	 * 
	 */
	public void showStartupInfo() {
		HintCollection.createStartupInfo(mainStage).show();
	}
	
	/**
	 * @param medal
	 */
	public void createAchievementPopup(MedalState medal){
		new AchievementPopup(medal).show(mainStage);
	}

	/**
	 * @return
	 */
	public String startImageCropper() {
		String ret = ImageCropperTool.showImageCropper(mainStage);
		return ret;
	}
	
	/**
	 * 
	 */
	private EventHandler<WindowEvent> windowCloseEvent = new EventHandler<WindowEvent>(){

		@Override
		public void handle(WindowEvent event) {
			mainStage.close();
			Runtime.getRuntime().exit(0);
		}
		
	};
	
}
