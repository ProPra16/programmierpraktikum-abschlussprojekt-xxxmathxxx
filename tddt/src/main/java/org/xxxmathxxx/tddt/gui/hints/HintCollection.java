package org.xxxmathxxx.tddt.gui.hints;

import javafx.stage.Stage;

/** Basic class that contains useful hints, shown at startup
 * @author xxxMathxxx
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
			new Hint("file:graphics/infobackgrounds/robot.jpg","Did you know that infamous SkyNet was developed using TDD?"),
			new Hint("file:graphics/infobackgrounds/info.jpg","Did you know that these hints barely rely on facts? ...But TDD does?"),
			new Hint("file:graphics/infobackgrounds/kantbackground.jpeg", "Did you know that in any particular theory of nature only so much real science can be encountered, as there is TDD therein?"),
			new Hint("file:graphics/infobackgrounds/tractor.jpg", "Did you know that Tschebyscheff's tm is an integral part of our software?"),
			new Hint("file:graphics/infobackgrounds/refractor.jpg", "Did you know that refraction is the change in direction of propagation of a wave due to a change in its transmission medium while refactoring means restructuring code?"),
			new Hint("file:graphics/infobackgrounds/lightspeed.jpg", "Did you know that hacking time is actually possible by using some java code, TDD and calculating E=mc^3 instead of E=mc^2?")
	};
	
	/**Getter function returning a JavaFX Notification containing a random hint
	 * @param mainStage The Stage in which the notification should be displayed
	 * @return a UsefulInfoNotification containing a random hint
	 */
	public static UsefulInfoNotification createStartupInfo(Stage mainStage){
		int randomIndex = ((int)(Math.round(Math.random()*(hints.length-1))));
		Hint selectedHint = hints[randomIndex];
		return new UsefulInfoNotification(selectedHint, mainStage);
	}
}
	