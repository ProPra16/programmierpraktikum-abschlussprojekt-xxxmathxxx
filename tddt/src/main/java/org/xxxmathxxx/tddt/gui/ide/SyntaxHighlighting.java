package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;




public class SyntaxHighlighting {
	
	private ArrayList<Highlight> marker = new ArrayList<Highlight>();
	private static SyntaxHighlighting instance;
	
	private static SimpleAttributeSet baseStyle = new SimpleAttributeSet();
	static{
	}
	
	private static SimpleAttributeSet keywordBase = new SimpleAttributeSet();
	static{
	    StyleConstants.setForeground(keywordBase, Color.MAGENTA);
	    StyleConstants.setBold(keywordBase, true);
	}
	
	private SyntaxHighlighting(){}; //hide constructor
	
	public static SyntaxHighlighting getInstance(){
		if (instance == null){
			instance = new SyntaxHighlighting();
		}
		return instance;
	}
	
	

	

	
	@SuppressWarnings("serial")
	private static ArrayList<String> keywordTable=new ArrayList<String>();
	static{
		keywordTable.addAll(
				Arrays.asList(
						"if",
						"while",
						"do",
						"static",
						"public",
						"private",
						"protected",
						"for",
						"try",
						"catch"
						)
				);
	}

	public void checkHighlighting(StyledDocument doc){
        try {
        	removeAllMarker(doc);
            
            for (String keyword: keywordTable ){
                String text = doc.getText(0, doc.getLength());
                //STEP 1: Simple keywords
                int pos = 0;
                
                while ((pos = text.indexOf(keyword, pos)) >= 0) {
                	//check conditions
                    boolean validKeywordFound = true;
                    
                    //check pre
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
                    //check post
                    int nextPos = pos+keyword.length();
                    if (nextPos < text.length()-1){
                        if (!
                        		(
                    			text.charAt(nextPos) == ' ' 
                    			|| text.charAt(nextPos) == ')' 
                    			|| text.charAt(nextPos) == '}' 
                    			|| text.charAt(nextPos) == '\t'
                        		)
                    		)
                    	{
                    		validKeywordFound = false;
                    	}
                    }
 
                	//add marker
                	if (validKeywordFound)
                	{
                    	marker.add(new Highlight(pos,keyword.length(),keywordBase));
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
                	doc.setCharacterAttributes(h.start, h.length,h.set , true);
                }
            };       
            SwingUtilities.invokeLater(doHighlight);
        }

	}
	
	private void removeAllMarker(StyledDocument doc) {
		for (Highlight h: marker){
            Runnable doHighlight = new Runnable() {
                @Override
                public void run() {
                	doc.setCharacterAttributes(h.start, h.length, SyntaxHighlighting.baseStyle, false);
                }
            };       
            SwingUtilities.invokeLater(doHighlight);
		}
		marker = new ArrayList<Highlight>();
	}

	class Highlight{
		int start;
		int length;
		SimpleAttributeSet set;
		public Highlight(int start, int length, SimpleAttributeSet set){
			this.start = start;
			this.length = length;
			this.set = set;
		}
	}
	
}
