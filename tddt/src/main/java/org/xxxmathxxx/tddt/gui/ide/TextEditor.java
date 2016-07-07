package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JEditorPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;


import javafx.embed.swing.SwingNode;

public class TextEditor extends SwingNode {
	//DONT LOOK AT THIS CLASS FOR CLEAN PROGRAMMING OR GOOD CODESTYLE, BTW F*** JAVAFX
	
	private JEditorPane editor;
	
	public TextEditor(){
		editor = new JEditorPane();
		editor.getDocument().addDocumentListener(
			new DocumentListener() {

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					System.out.println("TEST SYNTAX");
					checkHighlighting();
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					System.out.println("TEST SYNTAX");
					checkHighlighting();
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					System.out.println("TEST SYNTAX");
					checkHighlighting();
				}
	
		}
		);
		this.setContent(editor);
	}

	public void checkHighlighting(){
        try {
            Highlighter hilite = editor.getHighlighter();
            Document doc = editor.getDocument();
            
            for (String keyword: SyntaxHighlighting.highlightTable.keySet() ){
                String text = doc.getText(0, doc.getLength());
                int pos = 0;
                while ((pos = text.indexOf(keyword, pos)) >= 0) {
                    hilite.addHighlight(pos, pos + keyword.length(), SyntaxHighlighting.highlightTable.get(keyword));
                    pos += keyword.length();
                }
            }

        } catch (BadLocationException e) {
        	
        }

	}

	public void setLocation(int x, int y) {
		this.relocate(x, y);
	}

	public void setBounds(int i, int j, int k, int l) {
		editor.setBounds(i, j, k, l);
	}

	public void setText(String rawText) {
		editor.setText(rawText);
	}

	public String getText() {
		return editor.getText();
	}
	

}
