package org.xxxmathxxx.tddt.gui;

import java.util.regex.Pattern;

import javafx.scene.control.TextField;

public class RestrictedTextField extends TextField{
	
	private static Pattern p = Pattern.compile("[^a-zA-Z0-9]");
	private static int maxSize = 20;

    @Override public void replaceText(int start, int end, String text) {
    	String oldText = getText();
        if (!p.matcher(text).find()) {
            super.replaceText(start, end, text);
        }
        if (getText().length() > maxSize){
        	setText(oldText);
        }
    }
  
    @Override public void replaceSelection(String text) {
    	String oldText = getText();
        if (!p.matcher(text).find()) {
            super.replaceSelection(text);
        }
        if (getText().length() > maxSize){
        	setText(oldText);
        }
    }
}
