package org.xxxmathxxx.tddt.gui;

import java.util.ArrayList;

import org.xxxmathxxx.tddt.profile.Profile;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ProfilePicker extends Pane {
	
	private ArrayList <Profile> profiles;
	
	private ImageView center;
	private ImageView left;
	private ImageView right;
	private Label caption;
	
	private int index = 0;
	
	//statics
	
	private static int sizeX = 300;
	private static int sizeY = 200;

	
	public ProfilePicker(ArrayList<Profile> profiles){
		//TODO: Stylize / make more pretty
		this.profiles = profiles;
		
		this.setPrefSize(sizeX, sizeY);
		
		center = new ImageView();
		left = new ImageView();
		right = new ImageView();
		
		center.relocate(sizeX/3, 10);
		left.relocate(0, 0);
		right.relocate(sizeX/3*2, 10);
		
		
		caption = new Label();
		caption.setPrefSize(sizeX/2, 20);
		caption.relocate(sizeX/3, sizeY-20);
		
		left.setScaleX(0.5);
		left.setScaleY(0.5);

		right.setScaleX(0.5);
		right.setScaleY(0.5);

		if (profiles != null){
			setImages();
		}
		
		this.setBorder(
				new Border(
					new BorderStroke(Color.BLACK, BorderStrokeStyle.DOTTED, CornerRadii.EMPTY, BorderWidths.DEFAULT)
						)
				);
		
		
		getChildren().addAll(center,left,right,caption);

		left.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseControl);
		right.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseControl);
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
		return profiles.get(index);
	}

	private void setImages() {
		Profile centerProfile = profiles.get(index);
		if (centerProfile != null){
			centerProfile.showImageInJavaFXImageView(center);
		}
		else{
			center.setImage(null);
		}
		
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
}
