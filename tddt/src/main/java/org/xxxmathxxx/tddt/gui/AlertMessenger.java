package org.xxxmathxxx.tddt.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

/**
 * @author xxxMathxxx 2016
 * Class that includes different Alert Messages for different purposes.
 */
public class AlertMessenger {

	/**
	 * This Error message says that the name is already used.
	 */
	public static void duplicateNameError(){
		Alert noTextDialog = new Alert(AlertType.ERROR);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText("The name is already in use!");
		String s ="In order to proceed you need to enter a name that is unused!";
		noTextDialog.setContentText(s);
		//the following line is pure BS but javafx is still buggy
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();	
	}
	
	/**
	 * This Error message says that the user forgot to enter a name.
	 */
	public static void missingNameError(){
		Alert noTextDialog = new Alert(AlertType.ERROR);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText("You haven't entered a name yet!");
		String s ="In order to proceed you need to enter a name for your profile!";
		noTextDialog.setContentText(s);
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();	
	}
	
	/**
	 * This Error message says that the user entered a symbol which was not allowed in the naming convention.
	 */
	public static void unallowedSymbolError(){
		Alert noTextDialog = new Alert(AlertType.ERROR);
		noTextDialog.setTitle("Info");
		noTextDialog.setHeaderText("Your name contains an unconventional Symbol!");
		String s ="In order to proceed you have to remove the unallowed Symbols!";
		noTextDialog.setContentText(s);
		noTextDialog.getDialogPane().getChildren().stream().filter(node -> node instanceof Label).forEach(node -> ((Label)node).setMinHeight(Region.USE_PREF_SIZE));
		noTextDialog.showAndWait();	
	}
}

