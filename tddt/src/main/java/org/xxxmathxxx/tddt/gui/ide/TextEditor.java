package org.xxxmathxxx.tddt.gui.ide;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;

public class TextEditor extends TextArea {
	//DONT LOOK AT THIS CLASS FOR CLEAN PROGRAMMING OR GOOD CODESTYLE, BTW F*** JAVAFX
	
	private ArrayList<Rectangle> markers; //hackiest solution ever ever
	
	public TextEditor(){
		textProperty().addListener(
			new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    	checkHighlighting(); //TODO: Increase performance by only checking changed stuff
		    }
		}
		);
	}

	public void checkHighlighting(){
		for (Rectangle r: markers){
			this.getChildren().remove(r);
		}
		markers = new ArrayList<Rectangle>();
		String[] tokens = this.getText().split("\\s+");
		for (String t: tokens){
			if (SyntaxHighlighting.highlightTable.containsKey(t)){
				Rectangle marker = new Rectangle();
				
		        marker.setFill(SyntaxHighlighting.highlightTable.get(t));
			}
		}
		for (Rectangle r: markers){
			this.getChildren().add(r);
		}
	}
}
