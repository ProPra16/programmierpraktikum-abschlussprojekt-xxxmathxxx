package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.util.Duration;

/**This class creates an achievement-notification popup, used for awarding medals.
 * @author xxxMathxxx 2016
 * 
 */
public class AchievementPopup extends Popup {
	
	/**
	 * The text that is presented
	 */
	private Label text;
	
	/**
	 * The background (this is just filled for styling purpose)
	 */
	private Label background;
	
	/**
	 * The ImageView showing the earned medal
	 */
	private ImageView medalImage;
	
	/**
	 * The duration for the entire popup-animation
	 */
	private static int duration = 5;
		
	/**
	 * Internal reference to the users screenWidth
	 */
	private double screenWidth;
	
	/**
	 * Internal reference to the users screenHeight
	 */
	private double screenHeight;
	
	/**
	 * The width in pixel, used for styling
	 */
	private static double width = 256+128;
	/**
	 * The height in pixel, used for styling
	 */
	private static double height = 128;
	
	/**
	 * The border-size in pixel, used for styling
	 */
	private static double border = 8;
	
	/**
	 * The animation that controls the movement
	 */
	private PopupAnimation animation;
		
	/**Default constructor, creates a new achievement notification
	 * @param medal The medal that was awarded
	 */
	public AchievementPopup(MedalState medal){
						
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds();
		
		screenWidth = primaryScreenBounds.getWidth();
		screenHeight = primaryScreenBounds.getHeight();

		this.setWidth(width);
		this.setHeight(height);
		
		this.setX(screenWidth-width);
		this.setY(screenHeight);
		
		this.setOpacity(0.8);
		this.setAutoFix(false);
		
		background = new Label();
		background.setPrefSize(width, height);
		background.setId("achievement_bg");
		background.getStylesheets().add(GraphicsHelper.getResourcePath("/NotificationStyle.css"));
		this.getContent().add(background);
		
		text = new Label("Congratulations, you just won a medal on this exercise!");
		text.setPrefSize(width-128-2*border, height-2*border);
		text.setTextFill(Color.WHITE);
		text.setWrapText(true);
		text.getStylesheets().add(GraphicsHelper.getResourcePath("/NotificationStyle.css"));
		text.setId("achievement_text");
		text.relocate(128+border, border);
		this.getContent().add(text);
		
		if (medal != null){
			medalImage = new ImageView(GraphicsHelper.medalIconScaled(medal, height-border*2));
			medalImage.relocate(border, border);
			this.getContent().add(medalImage);
		}
		
		animation = new PopupAnimation();
		animation.play();
		
	}
	
	private class PopupAnimation extends Transition{
		/**
		 * The percentage of running time at which the Achievement is fully visible on screen
		 */
		private static final double marker1 = 1d/5d;
		/**
		 * The percentage of running time at which the Achievement begins its descent into the depths
		 */
		private static final double marker2 = 4d/5d;
		
		/**
		 * Creates a new PopupAnimation, basic Constructor
		 */
		private PopupAnimation(){
			this.setCycleDuration(Duration.seconds(duration));
			this.setOnFinished(new VanishEvent());
		}
		
		/* (non-Javadoc)
		 * @see javafx.animation.Transition#interpolate(double)
		 */
		@Override
		protected void interpolate(double frac) {
			if (frac < marker1){
				setY(screenHeight-frac*height*5);
			}
			else if (frac > marker2){
				setY(screenHeight-height+(frac-4d/5d)*height*5);
			}
		}
		
	}
	
	/**The event that is called when the animation is finished. Simply closes this popup.
	 * @author xxxMathxxx 2016
	 *
	 */
	private final class VanishEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			hide();
		}
	}

}
