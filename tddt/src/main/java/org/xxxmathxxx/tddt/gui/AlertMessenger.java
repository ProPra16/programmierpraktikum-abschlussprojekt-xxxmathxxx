package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Region;

/**
 * @author xxxMathxxx 2016
 * Class that includes different Alert Messages for different purposes.
 */
public class AlertMessenger {
	
	/**
	 * This method displays a message to the user.
	 * @param A header for the Error message
	 * @param The desired text for the Error message
	 */
	public static void showErrorMessage(String header, String text){
		Alert noTextDialog = new Alert(AlertType.ERROR);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText(header);
		String s = text;
		noTextDialog.setContentText(s);
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();	
	}
	
	public static Boolean showQuestionMessage(String header, String text){
		Alert noTextDialog = new Alert(AlertType.CONFIRMATION);
		noTextDialog.getDialogPane().getStylesheets().add("org/xxxmathxxx/tddt/gui/MenuStyle.css");
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

