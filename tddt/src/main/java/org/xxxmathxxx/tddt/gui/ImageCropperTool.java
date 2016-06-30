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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImageCropperTool extends Stage {
	
	//TODO: Adjust to general css style
	
	//javafx elements
	
	private Group imageGroup;
	private Rectangle selection;
	private ImageView iv;
	private ScrollPane ivContainer;
	private Pane pane;
	private Scene scene;
	
	private Button confirmButton;

	private Image originalImage;
	
	private int mouseClickX = 0;
	private int mouseClickY = 0;
	
	//statics
	private static int profilePicSizeX = 128; //adjust as required
	private static int profilePicSizeY = 128;
	
	private enum OperationMode{
			SCALING_SELECTION,MOVING_SELECTION,NONE
	};
	
	private OperationMode mode;

	/**This creates a new stage, that allows you to select a square region of an image
	 * Only call this constructor if you are 100% sure that the filePath points to a valid Image
	 * @param root The root on which the scene should be displayed
	 * @param filePath The file path pointing to an Image
	 */
	public ImageCropperTool(String filePath, Stage owner){
		
		this.setTitle("Image Cropping Tool");
		
		
		originalImage = new Image("file:"+filePath);
		iv = new ImageView(originalImage);
		
		selection = new Rectangle(0,0,profilePicSizeX,profilePicSizeY);
		
        selection.setStroke(Color.BLUE);
        selection.setStrokeWidth(1);
        selection.setStrokeLineCap(StrokeLineCap.ROUND);
        selection.setFill(Color.LIGHTBLUE.deriveColor(0, 1, 1, 0.4));
                
		imageGroup = new Group();
		imageGroup.getChildren().add(iv);
		imageGroup.getChildren().add(selection);
		
		confirmButton = new Button("Confirm profile picture!");
		confirmButton.setPrefSize(200, 50);
		confirmButton.relocate(0, originalImage.getHeight());
		confirmButton.addEventHandler(ActionEvent.ANY,confButtonHandler);
		
		ivContainer = new ScrollPane();
		ivContainer.setVmax(originalImage.getWidth());
		ivContainer.setHmax(originalImage.getHeight());

		ivContainer.setContent(imageGroup);
		
		ivContainer.addEventHandler(MouseEvent.MOUSE_PRESSED, MousePressedHandler);
		ivContainer.addEventHandler(MouseEvent.MOUSE_DRAGGED, MouseDraggedHandler);
		ivContainer.addEventHandler(MouseEvent.MOUSE_RELEASED, MouseDraggedHandler);
		ivContainer.addEventHandler(MouseEvent.MOUSE_MOVED, MouseMovedHandler);
		
		this.initOwner(owner);
		this.initModality(Modality.WINDOW_MODAL);
		
		pane = new Pane();
		pane.setPrefSize(originalImage.getWidth(), originalImage.getHeight()+50);
		pane.getChildren().add(ivContainer);
		pane.getChildren().add(confirmButton);
		
		scene = new Scene(pane);		

		this.setScene(scene);
	}
	
	private void cropAndExport() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image files", ".png"));

        File file = fileChooser.showSaveDialog(this);
        if (file == null){
            return;
        }
        int width = (int) selection.getWidth();
        int height = (int) selection.getHeight();

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setViewport(new Rectangle2D( selection.getX(), selection.getY(), width, height));

        WritableImage wi = new WritableImage( profilePicSizeX, profilePicSizeY);
        iv.snapshot(parameters, wi);

        BufferedImage bufImageARGB = SwingFXUtils.fromFXImage(wi, null);
        BufferedImage bufImageRGB = new BufferedImage(bufImageARGB.getWidth(), bufImageARGB.getHeight(), BufferedImage.OPAQUE);

        Graphics2D graphics = bufImageRGB.createGraphics();
        graphics.drawImage(bufImageARGB, 0, 0, null);

        try {
            ImageIO.write(bufImageRGB, "png", file); 
            TDDTLogManager.getInstance().logMessage("A cropped image has been stored @ " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        graphics.dispose();
        close();
        //TODO: return some information about where the profile pic is stored to someone else

    }
	
	private boolean isOnBottomRightCorner(MouseEvent event) {
		if ( // 2 pix offset to enable easier selection
				event.getX() > selection.getX() + selection.getWidth() - 2
				&& event.getX() < selection.getX() + selection.getWidth() + 2
				&& event.getY() > selection.getY() + selection.getHeight() - 2
				&& event.getY() < selection.getY() + selection.getHeight() + 2
			) 
		{
			return true;
		}
		return false;
	}
	
	private boolean isOnSelection(MouseEvent event) {
		if (
				event.getX() > selection.getX()
				&& event.getX() < selection.getX() + selection.getWidth()
				&& event.getY() > selection.getY()
				&& event.getY() < selection.getY() + selection.getHeight()
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
             else{ //mouse click to some other location
        		 scene.setCursor(Cursor.DEFAULT);
             }
         }

     };
     
	
	 EventHandler<MouseEvent> MousePressedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 if (isOnBottomRightCorner(event)&& event.getButton() == MouseButton.PRIMARY){
            	 //note the click location for later
            	mouseClickX = (int) event.getX();
            	mouseClickY = (int) event.getY();	 
            	mode = OperationMode.SCALING_SELECTION;
        	 }
        	 else if(isOnSelection(event)){
        		 mode = OperationMode.MOVING_SELECTION;
        	 }
             else{ //mouse click to some other location
            	 mode = OperationMode.NONE;
             }
         }

     };

     EventHandler<MouseEvent> MouseDraggedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 if (mode == OperationMode.MOVING_SELECTION){
            	 double newX = event.getX()-0.5*selection.getWidth();
            	 double newY = event.getY()-0.5*selection.getHeight();
            	 if (newX > 0 && newX < originalImage.getWidth()+selection.getWidth() ){
                	 selection.setX(newX);
            	 }
            	 if (newY > 0&& newY < originalImage.getHeight()+selection.getHeight()  ){
                	 selection.setY(newY);
            	 }
        	 }
         }
     };


     EventHandler<MouseEvent> MouseReleasedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	if (mode == OperationMode.SCALING_SELECTION){
        		mode = OperationMode.NONE;
        		//detect "bigger" change
        		double change;
        		double diffX = Math.abs((event.getX()-mouseClickX));
        		double diffY = Math.abs((event.getY()-mouseClickY));
        		if (diffX > diffY){
        			change = diffX;
        		}
        		else{
        			change = diffY;
        		}
        		selection.setWidth(selection.getWidth()+change);
        		selection.setHeight(selection.getHeight()+change);
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
