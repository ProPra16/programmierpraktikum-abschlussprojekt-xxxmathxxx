package org.xxxmathxxx.tddt.gui;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImageCropperTool extends Stage {
	
	//javafx elements
	
	private Group imageGroup;
	private Rectangle selection;
	private ImageView iv;
	private ScrollPane ivContainer;
	private Scene scene;

	private Image originalImage;
	
	private double selectionX = 0;
	private double selectionY = 0;
	
	private double selectionWidth = 0;
	private double selectionHeight = 0;
	
	private int mouseClickX = 0;
	private int mouseClickY = 0;
	
	//statics
	private static int profilePicSizeX = 128; //adjust as required
	private static int profilePicSizeY = 128;
	
	private enum OperationMode{
			SCALING_SELECTION,MOVING_SELECTION
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
				
		ivContainer = new ScrollPane();
		ivContainer.setVmax(originalImage.getWidth());
		ivContainer.setVmin(originalImage.getWidth());
		ivContainer.setHmax(originalImage.getHeight());
		ivContainer.setHmin(originalImage.getHeight());

		ivContainer.setContent(imageGroup);
		
		ivContainer.addEventHandler(MouseEvent.MOUSE_PRESSED, MousePressedHandler);
		ivContainer.addEventHandler(MouseEvent.MOUSE_DRAGGED, MouseDraggedHandler);
		ivContainer.addEventHandler(MouseEvent.MOUSE_RELEASED, MouseDraggedHandler);
		
		this.initOwner(owner);
		this.initModality(Modality.WINDOW_MODAL);
		
		scene = new Scene(ivContainer);		
		this.setScene(scene);
	}
	
	 EventHandler<MouseEvent> MousePressedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 if (event.getButton() == MouseButton.PRIMARY){
            	 //note the click location for later
            	 mouseClickX = (int) event.getX();
            	 mouseClickY = (int) event.getY();	 
        	 }
         }
     };

     EventHandler<MouseEvent> MouseDraggedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
        	 double newX = event.getX()-selection.getWidth();
        	 double newY = event.getY()-selection.getHeight();
        	 if (newX > 0 ){
            	 selection.setX(newX);
        	 }
        	 if (newY > 0 ){
            	 selection.setY(newY);
        	 }
         }
     };


     EventHandler<MouseEvent> MouseReleasedHandler = new EventHandler<MouseEvent>() {

         @Override
         public void handle(MouseEvent event) {
         }
     };

}
