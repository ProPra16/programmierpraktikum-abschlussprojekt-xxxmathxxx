package org.xxxmathxxx.tddt.gui.hints;


public class HintCollection {
	private static Hint[] hints = new Hint[]{
			new Hint("file:graphics/davinci.jpg","Did you know that Test-Driven-Development was invented by famous artist Leonardo DiCaprio as early as 1500? (Sadly his notes were lost and only recently rediscovered)"),
			new Hint(null,"Did you know that the client of a popular MOBA is not written using TDD?"),
			new Hint(null,"Did you know that, according to Bio20%KappaStats almost 20.000.000 people die every year due to a lack of TDD?")

	};
	
	public static UsefulInfoNotification createStartupInfo(){
		int randomIndex = ((int)(Math.random()*(hints.length-1)));
		Hint selectedHint = hints[randomIndex];
		return new UsefulInfoNotification(selectedHint);
	}
}
	