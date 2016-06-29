package org.xxxmathxxx.tddt.gui.hints;


/** Basic class that contains useful hints, shown at startup
 * @author TODO: Fill in generic xxxMathxxx header with license in all files
 *
 */
public class HintCollection {
	/**
	 * The static collection of hints
	 */
	private static Hint[] hints = new Hint[]{
			new Hint("file:graphics/infobackgrounds/davinci.jpg","Did you know that TDD was invented by famous artist Leonardo DiCaprio as early as 1500? (Sadly his notes were lost and only recently rediscovered)"),
			new Hint("file:graphics/infobackgrounds/lol.jpg","Did you know that the client of a popular MOBA is not written using TDD (and thus is barely working at all)?"),
			new Hint("file:graphics/infobackgrounds/pkmnred.jpg","Did you know that a program which can be written in one day using TDD takes around one lightyear to be written without TDD?"),
			new Hint("file:graphics/infobackgrounds/nokor.jpg","Did you know that North Korea bans the public use of TDD due to the grave danger it poses to the political party?"),
			new Hint("file:graphics/infobackgrounds/stoneage.jpg","Did you know that during the Stone Age no software was written at all? Experts believe that this is due to the lack of TDD in this era."),
			new Hint("file:graphics/infobackgrounds/stoneage.jpg","Did you know that infamous SkyNet was developed using TDD?"),
			new Hint("file:graphics/infobackgrounds/kantbackground.jpeg", "But I maintain that in any particular theory of nature only so much real science can be encountered, as encountered in mathematics.")
	};
	
	/**Getter function returning a JavaFX Notification containing a random hint
	 * @return
	 */
	public static UsefulInfoNotification createStartupInfo(){
		int randomIndex = ((int)(Math.round(Math.random()*(hints.length-1))));
		Hint selectedHint = hints[randomIndex];
		return new UsefulInfoNotification(selectedHint);
	}
}
	