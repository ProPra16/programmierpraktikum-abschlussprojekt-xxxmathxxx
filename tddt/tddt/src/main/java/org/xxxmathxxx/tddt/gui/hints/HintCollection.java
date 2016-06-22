package org.xxxmathxxx.tddt.gui.hints;


public class HintCollection {
	private static Hint[] hints = new Hint[]{
			new Hint("file:graphics/davinci.jpg","Did you know that lol?")
	};
	
	public static UsefulInfoNotification createStartupInfo(){
		int randomIndex = ((int)(Math.random()*(hints.length-1)));
		Hint selectedHint = hints[randomIndex];
		return new UsefulInfoNotification(selectedHint);
	}
}
	