package org.xxxmathxxx.tddt.gui.scenes;

import org.xxxmathxxx.tddt.editorpanes.TestEditPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Editor extends Scene {

	TestEditPane tep;
	
	
	public Editor(Pane pane) {

		super(pane);
		
		
		
	}
	
	
	
	private final class menuButtonHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			if (true){
				System.out.println("menuButtonhandlerTest");
			}
		}
	}
}
