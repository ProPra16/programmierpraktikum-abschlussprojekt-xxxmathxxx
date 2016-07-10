package org.xxxmathxxx.tddt.gui;

import java.util.ArrayList;

import org.xxxmathxxx.tddt.profile.Profile;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;

public class ProfilePicker extends Pane {
	
	private ArrayList <Profile> profiles;
	
	
	private ImageView center;
	private ImageView left;
	private ImageView right;
	
	private Label caption;
	
	private int index = 0;
	
	public ProfilePicker(ArrayList<Profile> profiles){
		//Stylize / make more pretty
		this.profiles = profiles;
		
		//init images
		center = new ImageView();
		left = new ImageView();
		right = new ImageView();
		
		//make non-selected entries transparent
		left.setOpacity(0.8);
		right.setOpacity(0.8);
		
		caption = new Label();


		if (!profiles.isEmpty()){
			setImages();
		}
		else{
			caption.setText("No profile available, you need to create one!");
		}
		
		getChildren().addAll(center,left,right,caption);
	
		left.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseControl);
		right.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseControl);
		
		left.addEventHandler(MouseEvent.ANY, selectionHighlighter);
		right.addEventHandler(MouseEvent.ANY, selectionHighlighter);
	}
	
	@Override
	public void resize(double width, double height){
				
		double sizeX = width;
		double sizeY = height;
		
		//calculate layout parameters
		
		double borderX = sizeX/16;
		double borderY = sizeY/16;
		
		double textSize = (sizeY-(borderY*2))/8;
		
		double mainFaceSize = sizeY-borderY-textSize;
		
		caption.setPrefSize(sizeX-(2*borderX),textSize);
		caption.relocate(137, sizeY-borderY-textSize+10);
		caption.setTextAlignment(TextAlignment.CENTER);
		
		center.setFitHeight(mainFaceSize);
		center.setFitWidth(mainFaceSize);

		
		left.setFitWidth((mainFaceSize)/2);
		left.setFitHeight((mainFaceSize)/2);

		right.setFitWidth((mainFaceSize)/2);
		right.setFitHeight((mainFaceSize)/2);
		
		//relocate
		left.relocate(borderX, borderY+mainFaceSize/4);
		center.relocate((sizeX-(2*borderX)-mainFaceSize-mainFaceSize)/2+borderX+mainFaceSize/2, 0);
		right.relocate(sizeX-borderX-mainFaceSize/2, borderY+mainFaceSize/4);
	}
	
	private void shiftSelection(int dir){
		index += dir;
		if (index == -1){
			index = 0;
		}
		else if (index == profiles.size()){
			index -= 1;
		}
		setImages();
	}

	public Profile getSelection(){
		if (profiles.isEmpty()){
			return null;
		}
		return profiles.get(index);
	}

	private void setImages() {
		
		Profile centerProfile = profiles.get(index);
		
		centerProfile.showImageInJavaFXImageView(center);

		
		if (index+1 < profiles.size()){
			Profile rightProfile = profiles.get(index+1);
			if (rightProfile != null){
				rightProfile.showImageInJavaFXImageView(right);
			}
			else{
				right.setImage(null);
			}
		}
		else{
			right.setImage(null);
		}

		if (index-1 >= 0){
			Profile leftProfile = profiles.get(index-1);
			if (leftProfile != null){
				leftProfile.showImageInJavaFXImageView(left);
			}
			else{
				left.setImage(null);
			}
		}
		else{
			left.setImage(null);
		}
		profiles.get(index).showNameInJavaFXLabel(caption);
	}
	
	EventHandler<MouseEvent> mouseControl = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent event) {
			if (event.getSource() == left){
				shiftSelection(-1);
			}
			else if (event.getSource() == right){
				shiftSelection(1);
			}
		}
		
	};
	
	EventHandler<MouseEvent> selectionHighlighter = new EventHandler<MouseEvent>(){
		@Override
		public void handle(MouseEvent event) {
			if (event.getEventType() == MouseEvent.MOUSE_ENTERED){
				((ImageView)event.getSource()).setOpacity(1);
			}
			else if (event.getEventType() == MouseEvent.MOUSE_EXITED){
				((ImageView)event.getSource()).setOpacity(0.8);
			}
		}
		
	};
}
