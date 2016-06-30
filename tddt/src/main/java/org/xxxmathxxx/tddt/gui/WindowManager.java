package org.xxxmathxxx.tddt.gui;

import javafx.stage.Stage;

public class WindowManager {
	
	public static StartupMenu createStartupMenu(){
		return new StartupMenu();
	}
	
	public static NewProfileMenu createNewProfileMenu(Stage owner){
		return new NewProfileMenu(owner);
	}
}
