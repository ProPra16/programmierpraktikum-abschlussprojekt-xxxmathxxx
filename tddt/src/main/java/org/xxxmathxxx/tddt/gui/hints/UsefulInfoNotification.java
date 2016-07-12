package org.xxxmathxxx.tddt.gui.hints;

import org.xxxmathxxx.tddt.gui.AlertMessenger;
import org.xxxmathxxx.tddt.gui.GraphicsHelper;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UsefulInfoNotification extends Stage {
	
	private Pane pane;
	
	private Scene dialogScene;
	
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
	
	
	/**Default constructor, creates a new notification with the given hint
	 * @param selectedHint The hint that will be displayed
	 * @param mainStage The parent Stage
	 */
	public UsefulInfoNotification(Hint selectedHint, Stage mainStage) {
		
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
		infoText.setFont(new Font("Times New Roman", 17));
		infoText.setWrapText(true);
		infoText.setTextAlignment(TextAlignment.LEFT);
		infoText.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		pane.getChildren().add(infoText);
		
		startupCheck = new CheckBox("Always show hints on startup!");
		startupCheck.setSelected(true);
		startupCheck.setAllowIndeterminate(false);
		startupCheck.setTooltip(new Tooltip("You won't be able to disable this, sorry!"));
		startupCheck.setPrefSize(240, 32);
		startupCheck.setTextAlignment(TextAlignment.CENTER);
		startupCheck.relocate(xSize/2-120, borderSize+faceSize+spacingTopBottom);
		startupCheck.setBackground(new Background(new BackgroundFill(new Color(1,1,1,0.7), null, null)));
		startupCheck.setOnAction(new CheckBoxTrollBot());
		pane.getChildren().add(startupCheck);
		
		
		confirmationButton = new Button("Wow, thanks!");
		confirmationButton.setPrefSize(128, 32);
		confirmationButton.relocate(xSize/2-64,borderSize+faceSize+spacingTopBottom+64 );
		confirmationButton.addEventHandler(ActionEvent.ANY, new confButtonHandler());
		pane.getChildren().add(confirmationButton);

		if (selectedHint.imagePath != null){
			pane.setBackground(new Background(new BackgroundImage(new Image(selectedHint.imagePath),null,null, null, null)));
		}
		
		dialogScene = new Scene(pane);
		
		dialogScene.getStylesheets().add(this.getClass().getResource("/NotificationStyle.css").toExternalForm());
		
		this.setScene(dialogScene);
		this.initStyle(StageStyle.UNDECORATED);
		//this part seems dumb but as javafx still has some bugs it is needed to really prevent resizing on unix-based systems
		this.setMaxWidth(xSize);
		this.setMaxHeight(ySize);
		this.setMinWidth(xSize);
		this.setMinHeight(ySize);
		
		this.setResizable(false);
		
		this.initOwner(mainStage);
		this.initModality(Modality.APPLICATION_MODAL);

	}
	
	/**
	 * Static reference to all the mathematicians glorious faces
	 */
	private static String[] faceImagePaths = new String[]{
			"/graphics/faces/fou.jpeg",
			"/graphics/faces/tsc.jpeg",
			"/graphics/faces/cau.jpeg",
			"/graphics/faces/eul.png",
			"/graphics/faces/kant.jpeg"
	};
	
	private static Image generateRandomMathFace(){
		int randomIndex = ((int)Math.round((Math.random()*(faceImagePaths.length-1))));
		return new Image(GraphicsHelper.getResourcePath(faceImagePaths[randomIndex]));
	}
	
	private class CheckBoxTrollBot implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			((CheckBox)event.getSource()).setSelected(true);
			AlertMessenger.showErrorMessage("Nice try ...", "Hints are informative and you can learn more from them than you would like to admit!");
		}
		
	}
	
	private final class confButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			close();
		}
	}

}
