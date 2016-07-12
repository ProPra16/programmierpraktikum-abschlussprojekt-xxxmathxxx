package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Region;

public class QuestionMessenger
{
	public static Boolean showErrorMessage(String header, String text){
		Alert noTextDialog = new Alert(AlertType.CONFIRMATION);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText(header);
		String s = text;
		noTextDialog.setContentText(s);
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();	
		
		if(noTextDialog.getResult().getButtonData()==ButtonData.OK_DONE)
		{
			return true;
		}
		return false;
	}
}
