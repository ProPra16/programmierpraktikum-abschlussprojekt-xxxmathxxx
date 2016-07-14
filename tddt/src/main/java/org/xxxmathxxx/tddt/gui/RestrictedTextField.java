package org.xxxmathxxx.tddt.gui;

import java.util.regex.Pattern;

import javafx.scene.control.TextField;

/**An extension of the classical TextField that only allows alphanumerical input and is limited to 20 characters
 * @author xxxMathxxx 2016
 *
 */
public class RestrictedTextField extends TextField{
	
	/**
	 * The pattern (as RegEx) that is not allowed in this test field (note the negation)
	 */
	private static Pattern notAllowedPattern = Pattern.compile("[^a-zA-Z0-9]");
	
	/**
	 * The maximum number of characters that can be inserted into this text field
	 */
	private static int maxSize = 20;

    @Override 
    public void replaceText(int start, int end, String text) {
    	String oldText = getText();
        if (!notAllowedPattern.matcher(text).find()) {
            super.replaceText(start, end, text);
        }
        if (getText().length() > maxSize){
        	setText(oldText);
        }
    }
  
    @Override public void replaceSelection(String text) {
    	String oldText = getText();
        if (!notAllowedPattern.matcher(text).find()) {
            super.replaceSelection(text);
        }
        if (getText().length() > maxSize){
        	setText(oldText);
        }
    }
}
