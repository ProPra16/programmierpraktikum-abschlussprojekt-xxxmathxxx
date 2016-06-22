package org.xxxmathxxx.tddt.gui.hints;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UsefulInfoNotification extends Stage {
	
	private Pane pane;
	
	private Scene dialogScene;
	
	private Label startupCheckLabel;
	private Label infoText;
	private ImageView mathFace;
	private CheckBox startupCheck;
	private Button confirmationButton;
	
	private static int xSize = 512;
	private static int ySize = 256;
	
	private static int borderSize = 8;
	private static int faceSize = 100;
	private static int spacingFaceText = 8;
	private static int spacingTopBottom = 16;
	
	
	public UsefulInfoNotification(Hint selectedHint) {
		
		pane = new Pane();
		pane.setPrefSize(xSize, ySize);
		
		mathFace = new ImageView();
		pane.getChildren().add(mathFace);
		mathFace.setImage(generateRandomMathFace());
		mathFace.fitHeightProperty().set(faceSize);
		mathFace.fitWidthProperty().set(faceSize);
		mathFace.relocate(borderSize, borderSize);
		
		infoText = new Label(selectedHint.infoText);
		infoText.setPrefSize(xSize-faceSize-2*borderSize-spacingFaceText, faceSize);
		infoText.relocate(borderSize+faceSize+spacingFaceText,borderSize);
		infoText.setFont(new Font("Times New Roman", 18));
		infoText.setWrapText(true);
		infoText.setTextAlignment(TextAlignment.LEFT);
		infoText.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(infoText);
		
		startupCheck = new CheckBox();
		startupCheck.setSelected(true);
		startupCheck.setDisable(true);
		startupCheck.setPrefSize(32, 32);
		startupCheck.relocate(xSize/2-100, borderSize+faceSize+spacingTopBottom);
		pane.getChildren().add(startupCheck);
		
		startupCheckLabel = new Label("Show hints on startup");
		startupCheckLabel.setFont(new Font("Times New Roman", 18));
		startupCheckLabel.setPrefSize(168,32 );
		startupCheckLabel.relocate(xSize/2-100+32,borderSize+faceSize+spacingTopBottom);
		pane.getChildren().add(startupCheckLabel);
		
		confirmationButton = new Button("Wow, thanks!");
		confirmationButton.setPrefSize(200, 64);
		confirmationButton.relocate(xSize/2-100,borderSize+faceSize+spacingTopBottom+64 );
		pane.getChildren().add(confirmationButton);

		if (selectedHint.imagePath != null){
			pane.setBackground(new Background(new BackgroundImage(new Image(selectedHint.imagePath),null,null, null, null)));
		}
		
		dialogScene = new Scene(pane);
		
		this.setScene(dialogScene);
		this.initStyle(StageStyle.UNDECORATED);
		this.setResizable(false);

	}
	
	private static String[] faceImagePaths = new String[]{
			"file:graphics/faces/fou.jpeg",
			"file:graphics/faces/tsc.jpeg",
			"file:graphics/faces/cau.jpeg",
			"file:graphics/faces/eul.png",
	};
	
	private static Image generateRandomMathFace(){
		int randomIndex = ((int)(Math.random()*(faceImagePaths.length-1)));
		return new Image(faceImagePaths[randomIndex]);
	}

}
