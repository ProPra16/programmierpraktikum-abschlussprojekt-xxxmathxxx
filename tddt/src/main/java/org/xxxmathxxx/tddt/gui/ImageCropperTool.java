package org.xxxmathxxx.tddt.gui;


import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**A JavaFX Stage that enables loading/saving of an image and cropping-functionality
 * @author Philipp Spohr, Jul 9, 2016
 *
 */
public class ImageCropperTool extends Stage {
	
	//javafx elements
	
	private Rectangle selectionMarker;
	private ImageView iv;
	private Pane pane;
	private Scene scene;
	
	private Button confirmButton;

	private Image originalImage;
	
	private double mouseClickX = 0;
	private double mouseClickY = 0;
	
	private double oldX;
	private double oldY;
	private double oldWidth;
	private double oldHeight;
	
	//statics
	private static int profilePicSizeX = 128; //adjust as required
	private static int profilePicSizeY = 128;
	
	private enum OperationMode{
			SCALING_SELECTION,MOVING_SELECTION,NONE
	};
	
	private OperationMode mode;
	
	private String imagePath;
	
	public static String showImageCropper(Stage owner){
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image files","*.png","*.jpg","*.jpeg"));

        File file = fileChooser.showOpenDialog(owner);
        
        if (file == null){
        	TDDTLogManager.getInstance().logMessage("No valid file opened!");
        	return null;
        }
        else{
        	TDDTLogManager.getInstance().logMessage("Starting Image Cropper for file: "+file.getAbsolutePath());
        }
        //Sanity checks
        if (file.length()/ (1024 * 1024) > 50){ //50 MB limit
        	TDDTLogManager.getInstance().logMessage("You tried to open an insane and sick image file, pls don't!");
			Alert noTextDialog = new Alert(AlertType.ERROR);
			noTextDialog.setTitle("Error");
			noTextDialog.setHeaderText("Image too large!");
			String s ="You can only use images < 50 MB for your profile picture!";
			noTextDialog.setContentText(s);
			//the following line is pure BS but javafx is still buggy
			noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
			noTextDialog.showAndWait();
        	return null;
        }
        
        String filePath = file.getAbsolutePath();
		Image testImage = new Image("file:"+filePath);
		if (testImage.getWidth() < 128 || testImage.getHeight() < 128){
        	TDDTLogManager.getInstance().logMessage("You tried to open a tiny image file, pls don't!");
			Alert noTextDialog = new Alert(AlertType.ERROR);
			noTextDialog.setTitle("Error");
			noTextDialog.setHeaderText("Image too small!");
			String s ="Your profile picture needs to have at least 128x128 pixels!";
			noTextDialog.setContentText(s);
			//the following line is pure BS but javafx is still buggy
			noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
			noTextDialog.showAndWait();
        	return null;
		}
		ImageCropperTool instance = new ImageCropperTool(filePath,owner);
		instance.showAndWait();
		return instance.imagePath;
	}

	/**This creates a new stage, that allows you to select a square region of an image
	 * Only call this constructor if you are 100% sure that the filePath points to a valid Image
	 * @param owner The Stage that parents this tool
	 * @param filePath The file path pointing to an Image
	 */
	public ImageCropperTool(String filePath, Stage owner){
		
		this.setTitle("Image Cropping Tool");
		

		
		originalImage = new Image("file:"+filePath);
		iv = new ImageView(originalImage);
		
		selectionMarker = new Rectangle(0,0,profilePicSizeX,profilePicSizeY);
		
        selectionMarker.setStroke(Color.BLUE);
        selectionMarker.setStrokeWidth(1);
        selectionMarker.setStrokeLineCap(StrokeLineCap.ROUND);
        selectionMarker.setFill(Color.LIGHTBLUE.deriveColor(0, 1, 1, 0.4));
        
		confirmButton = new Button("Confirm profile picture!");
		confirmButton.setPrefSize(200, 50);
		confirmButton.relocate(150, originalImage.getHeight());
		confirmButton.addEventHandler(ActionEvent.ANY,confButtonHandler);
		
		this.addEventHandler(MouseEvent.MOUSE_PRESSED, MousePressedHandler);
		this.addEventHandler(MouseEvent.MOUSE_DRAGGED, MouseDraggedHandler);
		this.addEventHandler(MouseEvent.MOUSE_MOVED, MouseMovedHandler);
		this.addEventHandler(MouseEvent.MOUSE_RELEASED, MouseReleasedHandler);

		this.initOwner(owner);
		this.initModality(Modality.WINDOW_MODAL);
		
		
		//javafx is intuitive and ... makes a lot of sense ... all the time
		pane = new Pane();
		pane.setPrefSize(originalImage.getWidth(), originalImage.getHeight()+50);
		
		Group imageLayer = new Group();
		imageLayer.getChildren().add(iv);
		imageLayer.getChildren().add(selectionMarker);
		
		pane.getChildren().add(confirmButton);
		pane.getChildren().add(imageLayer);
		
		
		scene = new Scene(pane);	
		
		this.setScene(scene);
	}
	
	
	private void cropAndExport() {
		//TODO: Feel free to replace the fileChooser with a predefined save location for all user profiles,
		//maybe encoded by UserID/ProfileID

        File profileImagesDir = new File("profiles/pics");
        
        //create if necessary
        profileImagesDir.mkdirs();
        
        int namingCounter = 0;
        boolean foundName = false;
        File file = null;
        
        while (foundName != true){
        	file = new File("profiles/pics/"+namingCounter+".png");
        	if (!file.exists()){
        		foundName = true;
        	}
        	namingCounter ++;
        }

        int width = (int) selectionMarker.getWidth();
        int height = (int) selectionMarker.getHeight();

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setViewport(new Rectangle2D( selectionMarker.getLayoutX(), selectionMarker.getLayoutY(), width, height));

        WritableImage wi = new WritableImage( width, height);
        iv.snapshot(parameters, wi);

        BufferedImage bufImageARGB = SwingFXUtils.fromFXImage(wi, null);
        BufferedImage bufImageRGB = new BufferedImage(profilePicSizeX, profilePicSizeY, BufferedImage.OPAQUE);

        Graphics2D graphics = bufImageRGB.createGraphics();
        graphics.drawImage(bufImageARGB,
        		0, 0, profilePicSizeX, profilePicSizeY,
        		0, 0, width, height, null);

        try {
            ImageIO.write(bufImageRGB, "png", file); 
            imagePath = file.getAbsolutePath();
            TDDTLogManager.getInstance().logMessage("A cropped image has been stored @ " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        graphics.dispose();
        close();
        //TODO: return some information about where the profile pic is stored to someone else

    }
	

	
	private boolean isOnBottomRightCorner(MouseEvent event) {
		int tolerance = 3;
		if (
				event.getX() > selectionMarker.getLayoutX() + selectionMarker.getWidth() - tolerance
				&& event.getX() < selectionMarker.getLayoutX() + selectionMarker.getWidth() + tolerance
				&& event.getY() > selectionMarker.getLayoutY() + selectionMarker.getHeight() - tolerance
				&& event.getY() < selectionMarker.getLayoutY() + selectionMarker.getHeight() + tolerance
			) 
		{
			return true;
		}
		return false;
	}
	
	private boolean isOnSelection(MouseEvent event) {
		if (
				event.getX() > selectionMarker.getLayoutX()
				&& event.getX() < selectionMarker.getLayoutX() + selectionMarker.getWidth()
				&& event.getY() > selectionMarker.getLayoutY()
				&& event.getY() < selectionMarker.getLayoutY() + selectionMarker.getHeight()
			) 
		{
			return true;
		}
		return false;
	}
	
	 EventHandler<MouseEvent> MouseMovedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 if (isOnBottomRightCorner(event)){
        		 scene.setCursor(Cursor.SE_RESIZE);
        	 }
        	 else if (isOnSelection(event)){
        		 scene.setCursor(Cursor.MOVE);
        	 }
        	 else{
             	scene.setCursor(Cursor.DEFAULT);
        	 }
         }

     };

	 EventHandler<MouseEvent> MouseReleasedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 if (
        			 selectionMarker.getLayoutX() < 0
        			 || selectionMarker.getLayoutY() < 0
        			 || selectionMarker.getLayoutX() + selectionMarker.getWidth() > originalImage.getWidth()
        			 || selectionMarker.getLayoutY() + selectionMarker.getHeight() > originalImage.getHeight()

        			 ){
        		 selectionMarker.relocate(oldX, oldY);
        		 selectionMarker.setWidth(oldWidth);
        		 selectionMarker.setHeight(oldHeight);
        	 }
         }

     };
     
	
	 EventHandler<MouseEvent> MousePressedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 if (event.getButton() == MouseButton.PRIMARY){
        		 oldX = selectionMarker.getLayoutX();
        		 oldY = selectionMarker.getLayoutY();
        		 oldWidth = selectionMarker.getWidth();
        		 oldHeight = selectionMarker.getHeight();

        		 
             	mouseClickX = event.getSceneX()-selectionMarker.getLayoutX();
             	mouseClickY = event.getSceneY()-selectionMarker.getLayoutY();	
             	
             	//System.out.println("MOUSECLICK:"+mouseClickX+"/"+mouseClickY);
             	
            	 if (isOnBottomRightCorner(event)&& event.getButton() == MouseButton.PRIMARY){
                	 //note the click location for later 
                	 mode = OperationMode.SCALING_SELECTION;
            	 }
            	 else if(isOnSelection(event)){
            		 mode = OperationMode.MOVING_SELECTION;
            	 }
            	 else{
            		 mode = OperationMode.NONE;
            	 }
        	 }
         }

     };

     EventHandler<MouseEvent> MouseDraggedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        					
				if (mode == OperationMode.SCALING_SELECTION){
					
					double diffX = (event.getSceneX() - (selectionMarker.getLayoutX()+selectionMarker.getWidth()));
					double diffY = (event.getSceneY() - (selectionMarker.getLayoutY()+selectionMarker.getHeight()));
					double change;
					
					if (Math.abs(diffX) > Math.abs(diffY)) {
						change = diffX;
					} else {
						change = diffY;
					}
					
					selectionMarker.setWidth(selectionMarker.getWidth() + change);
					selectionMarker.setHeight(selectionMarker.getHeight() + change);
				}
				else if (mode == OperationMode.MOVING_SELECTION){
					double diffX = (event.getSceneX() - (selectionMarker.getLayoutX()+mouseClickX));
					double diffY = (event.getSceneY() - (selectionMarker.getLayoutY()+mouseClickY));
					System.out.println(diffX+"/"+diffY);
		        	selectionMarker.relocate(selectionMarker.getLayoutX()+diffX, selectionMarker.getLayoutY()+diffY);
				}
         }
     };
     
 	EventHandler<ActionEvent> confButtonHandler = new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event) {
			cropAndExport();
		}
	};

}
