package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Region;

/**Class that generates different Alert Message popups for different purposes.
 * @author xxxMathxxx 2016
 * 
 */
public class AlertMessenger {
	
	/**
	 * This method displays a message to the user.
	 * @param header A header for the Error message
	 * @param text The desired text for the Error message
	 */
	public static void showErrorMessage(String header, String text){
		Alert noTextDialog = new Alert(AlertType.ERROR);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText(header);
		noTextDialog.getDialogPane().getStylesheets().add(GraphicsHelper.getResourcePath("/MenuStyle.css"));
		String s = text;
		noTextDialog.setContentText(s);
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();	
	}
	
	/**Shows a basic yes-no dialog and returns the users choice
	 * @param header The header for the dialog
	 * @param text The text to be displayed
	 * @return true if the user pressed "Ok", false otherwise
	 */
	public static Boolean showQuestionMessage(String header, String text){
		Alert noTextDialog = new Alert(AlertType.CONFIRMATION);
		noTextDialog.getDialogPane().getStylesheets().add(GraphicsHelper.getResourcePath("/MenuStyle.css"));
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

