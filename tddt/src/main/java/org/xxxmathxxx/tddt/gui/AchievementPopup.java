package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Screen;
import javafx.stage.Window;
import javafx.util.Duration;

public class AchievementPopup extends Popup {
	
	private Label text;
	
	private static int duration = 5;
		
	private double screenWidth;
	private double screenHeight;
	
	private static int width = 256;
	private static int height = 128;
	
	private PopupAnimation animation;
		
	public AchievementPopup(MedalState medal){
						
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		
		screenWidth = primaryScreenBounds.getWidth();
		screenHeight = primaryScreenBounds.getHeight();

		this.setWidth(width);
		this.setHeight(height);
		
		this.setX(screenWidth-width);
		this.setY(screenHeight);
		
		this.setOpacity(0.8);
		this.setAutoFix(false);
		
		text = new Label("Congratulations, you just won a medal on this exercise!");
		text.setPrefSize(width, height);
		text.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
		text.setTextFill(Color.WHITE);
		
		animation = new PopupAnimation();
		animation.play();
		
		this.getContent().add(text);
		
		
	}
	
	private class PopupAnimation extends Transition{
		/*
		 * 1/5 of the time move up, 3/5 show, 1/5 move down
		 */
		
		private double marker1 = 1d/5d;
		private double marker2 = 4d/5d;
		
		private PopupAnimation(){
			this.setCycleDuration(Duration.seconds(duration));
			this.setOnFinished(new VanishEvent());
		}
		
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
	
	private final class VanishEvent implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			hide();
		}
	}

}
