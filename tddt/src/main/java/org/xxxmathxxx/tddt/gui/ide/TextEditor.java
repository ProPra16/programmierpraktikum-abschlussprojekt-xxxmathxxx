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
	private ArrayList<Highlight> marker;
	
	public TextEditor(){
		marker = new ArrayList<Highlight>();
		editor = new JTextPane();
		editor.getDocument().addDocumentListener(new ChangeListener());
		this.setContent(editor);
	}
	
	private class ChangeListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			checkHighlighting();			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			checkHighlighting();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}
		
	}

	public void checkHighlighting(){
        try {
        	removeAllMarker();
            Document doc = editor.getDocument();
            
            for (String keyword: SyntaxHighlighting.highlightTable.keySet() ){
                String text = doc.getText(0, doc.getLength());
                //STEP 1: Simple keywords
                int pos = 0;
                
                while ((pos = text.indexOf(keyword, pos)) >= 0) {
                	//check conditions
                    boolean validKeywordFound = true;
                    
                    if (pos != 0){
                        if (!
                        		(
                    			text.charAt(pos-1) == ' ' 
                    			|| text.charAt(pos-1) == '(' 
                    			|| text.charAt(pos-1) == '{' 
                    			|| text.charAt(pos-1) == '\t'
                        		)
                    		)
                    	{
                    		validKeywordFound = false;
                    	}
                    }
 
                	//add marker
                	if (validKeywordFound)
                	{
                    	marker.add(new Highlight(pos,keyword.length(),SyntaxHighlighting.highlightTable.get(keyword)));
                	}
                    pos += keyword.length();
                }
            }

        } catch (BadLocationException e) {
        	
        }
        for (Highlight h: marker){
            Runnable doHighlight = new Runnable() {
                @Override
                public void run() {
                	editor.getStyledDocument().setCharacterAttributes(h.start, h.length,h.set , true);
                }
            };       
            SwingUtilities.invokeLater(doHighlight);
        }

	}

	private void removeAllMarker() {
		for (Highlight h: marker){
            Runnable doHighlight = new Runnable() {
                @Override
                public void run() {
                	editor.getStyledDocument().setCharacterAttributes(h.start, h.length, SyntaxHighlighting.baseStyle, false);
                }
            };       
            SwingUtilities.invokeLater(doHighlight);
		}
		marker = new ArrayList<Highlight>();
	}

	public class Highlight{
		int start;
		int length;
		SimpleAttributeSet set;
		public Highlight(int start, int length, SimpleAttributeSet set){
			this.start = start;
			this.length = length;
			this.set = set;
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
