package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import javax.swing.text.SimpleAttributeSet;


import javafx.embed.swing.SwingNode;

public class TextEditor extends SwingNode {
	//DONT LOOK AT THIS CLASS FOR CLEAN PROGRAMMING OR GOOD CODESTYLE, BTW F*** JAVAFX
	
	private JTextPane editor;
	
	public TextEditor(){
		editor = new JTextPane();
		editor.getDocument().addDocumentListener(new ChangeListener());
		this.setContent(editor);
	}
	
	private class ChangeListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			SyntaxHighlighting.getInstance().checkHighlighting(editor.getStyledDocument());			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			SyntaxHighlighting.getInstance().checkHighlighting(editor.getStyledDocument());
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}
		
	}

	public void setLocation(int x, int y) {
		this.relocate(x, y);
	}

	public void setBounds(int i, int j, int width, int height) {
		editor.setBounds(i, j, width, height);
		//JAVAFX is 100% bug-free and probably uses TDD
		editor.setSize(width,height);
		editor.setMinimumSize(new Dimension(width,height));
	}
	
	public void setText(String rawText) {
		editor.setText(rawText);
	}

	public String getText() {
		return editor.getText();
	}	

}
