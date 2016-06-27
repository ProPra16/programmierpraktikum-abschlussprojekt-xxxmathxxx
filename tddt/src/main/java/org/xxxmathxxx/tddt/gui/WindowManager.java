package org.xxxmathxxx.tddt.gui;

public class WindowManager {
	
	public void handleStartButton() {
		createStartupMenu();
	}
	
	public static StartupMenu createStartupMenu(){
		return new StartupMenu();
	}
}
