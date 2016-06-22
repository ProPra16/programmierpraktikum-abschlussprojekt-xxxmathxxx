package org.xxxmathxxx.tddt.gui.hints;


public class HintCollection {
	private static Hint[] hints = new Hint[]{
			new Hint("file:graphics/infobackgrounds/davinci.jpg","Did you know that TDD was invented by famous artist Leonardo DiCaprio as early as 1500? (Sadly his notes were lost and only recently rediscovered)"),
			new Hint("file:graphics/infobackgrounds/lol.jpg","Did you know that the client of a popular MOBA is not written using TDD (and thus is barely working at all)?"),
			new Hint("file:graphics/infobackgrounds/pkmnred.jpg","Did you know that a program which can be written in one day using TDD takes around one lightyear to be written without TDD?"),
			new Hint("file:graphics/infobackgrounds/nokor.jpg","Did you know that North Korea bans the public use of TDD due to the grave danger it poses to the political party?")
	};
	
	public static UsefulInfoNotification createStartupInfo(){
		int randomIndex = ((int)(Math.round(Math.random()*(hints.length-1))));
		Hint selectedHint = hints[randomIndex];
		return new UsefulInfoNotification(selectedHint);
	}
}
	