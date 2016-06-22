package org.xxxmathxxx.tddt.gui.hints;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UsefulInfoNotification extends Stage {
	
	private Pane pane;
	private Scene dialogScene;
	private CheckBox startupCheck;
	private Label startupCheckLabel;
	private Label infoText;
	
	public UsefulInfoNotification(Hint selectedHint) {
		pane = new FlowPane();
		
		this.setTitle("Useful Information");
		
		infoText = new Label(selectedHint.infoText);
		pane.getChildren().add(infoText);
		
		startupCheck = new CheckBox();
		startupCheck.setSelected(true);
		startupCheck.setDisable(true);
		pane.getChildren().add(startupCheck);
		
		startupCheckLabel = new Label("Show hints on startup");
		pane.getChildren().add(startupCheckLabel);
		pane.setBackground(new Background(new BackgroundImage(new Image(selectedHint.imagePath),null,null, null, null)));
		dialogScene = new Scene(pane);
		this.setScene(dialogScene);
		pane.setPrefSize(512, 256);
	}

}
