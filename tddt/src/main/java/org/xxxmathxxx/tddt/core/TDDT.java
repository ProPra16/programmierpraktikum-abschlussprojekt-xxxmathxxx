package org.xxxmathxxx.tddt.core;


import org.xxxmathxxx.tddt.gui.hints.HintCollection;

import javafx.application.Application;
import javafx.stage.Stage;

public class TDDT extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		HintCollection.createStartupInfo().show();
	}

}
