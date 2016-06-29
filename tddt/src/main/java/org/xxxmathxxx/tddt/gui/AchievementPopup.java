package org.xxxmathxxx.tddt.gui;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;
import org.xxxmathxxx.tddt.profile.MedalState;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;

public class AchievementPopup extends Popup {
	
	private Label text;
	
	private static int duration = 5;
		
	private double stageHeight;
	
	private static int width = 100;
	private static int height = 50;
	
	private PopupAnimation animation;
	
	private Window currentWindow;
	
	public AchievementPopup(MedalState medal, Window currentWindow){
		
		this.currentWindow = currentWindow;
		
		TDDTLogManager.getInstance().logMessage("Creating new achievement popup on window: "+currentWindow.toString());
		stageHeight = this.getOwnerWindow().getHeight();

		this.setWidth(width);
		this.setHeight(height);
		
		this.setAnchorLocation(AnchorLocation.CONTENT_BOTTOM_RIGHT);
		this.setOpacity(0.8);
		
		text = new Label("Congratulations, you just won a medal on this exercise!");
		text.setPrefSize(100, 50);
		text.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
		text.setTextFill(Color.WHITE);
		
		animation = new PopupAnimation();
		animation.play();
		
		this.getContent().add(text);
		
	}
	
	@Override
	public void show(){
		super.show(currentWindow);
	}
	
	private class PopupAnimation extends Transition{
		/*
		 * 1/5 of the time move up, 3/5 show, 1/5 move down
		 */
		
		private double marker1 = 1/5;
		private double marker2 = 4/5;
		
		private PopupAnimation(){
			this.setCycleDuration(Duration.seconds(duration));
			this.setOnFinished(new VanishEvent());
		}
		
		@Override
		protected void interpolate(double frac) {
			if (frac < marker1){
				setY(stageHeight-frac*height*5);
			}
			else if (frac > marker2){
				setY(stageHeight-marker1*height);
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
