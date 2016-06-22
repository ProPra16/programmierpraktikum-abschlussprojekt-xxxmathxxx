package org.xxxmathxxx.tddt.gui.hints;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UsefulInfoNotification extends Stage {
	
	private Pane pane;
	private Scene dialogScene;
	private CheckBox startupCheck;
	private Label startupCheckLabel;
	private Label infoText;
	private Button confirmationButton;
	
	public UsefulInfoNotification(Hint selectedHint) {
		pane = new Pane();
		pane.setPrefSize(512, 256);
		this.setTitle("Useful Information");
		
		infoText = new Label(selectedHint.infoText);
		infoText.setPrefSize(512-16, 128+32-8);
		infoText.relocate(8,8);
		infoText.setFont(new Font("Times New Roman", 18));
		infoText.setWrapText(true);
		infoText.setTextAlignment(TextAlignment.LEFT);
		infoText.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(infoText);
		
		startupCheck = new CheckBox();
		startupCheck.setSelected(true);
		startupCheck.setDisable(true);
		startupCheck.setPrefSize(32, 32);
		startupCheck.relocate(8+128, 128+32+8);
		pane.getChildren().add(startupCheck);
		
		startupCheckLabel = new Label("Show hints on startup");
		startupCheckLabel.setFont(new Font("Times New Roman", 18));
		startupCheckLabel.setPrefSize(512-64-10, 32);
		startupCheckLabel.relocate(8+32+8+128, 128+32+8);
		pane.getChildren().add(startupCheckLabel);
		if (selectedHint.imagePath != null){
			pane.setBackground(new Background(new BackgroundImage(new Image(selectedHint.imagePath),null,null, null, null)));
		}
		dialogScene = new Scene(pane);
		this.setScene(dialogScene);
		this.initStyle(StageStyle.UNDECORATED);
		this.setResizable(false);

	}

}
