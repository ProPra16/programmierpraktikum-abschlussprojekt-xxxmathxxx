package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class SyntaxHighlighting {
	
	
	private static SyntaxHighlighting instance;
		
	private static SimpleAttributeSet baseStyle = new SimpleAttributeSet();
	static{
	}
	
	private static SimpleAttributeSet keywordStyle = new SimpleAttributeSet();
	static{
	    StyleConstants.setForeground(keywordStyle, Color.MAGENTA);
	    StyleConstants.setBold(keywordStyle, true);
	}
	
	private static SimpleAttributeSet stringStyle = new SimpleAttributeSet();
	static{
	    StyleConstants.setForeground(stringStyle, Color.BLUE);
	}
	
	private static SimpleAttributeSet commentaryStyle = new SimpleAttributeSet();
	static{
	    StyleConstants.setForeground(commentaryStyle, Color.green);
	}
	
	private static SimpleAttributeSet atMarkerStyle = new SimpleAttributeSet();
	static{
	    StyleConstants.setForeground(atMarkerStyle, Color.DARK_GRAY);
	    StyleConstants.setItalic(atMarkerStyle, true);
	}
	private SyntaxHighlighting(){}; //hide constructor
	
	public static SyntaxHighlighting getInstance(){
		if (instance == null){
			instance = new SyntaxHighlighting();
		}
		return instance;
	}

	
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
						"catch",
						"import",
						"class",
						"void",
						"int"
						)
				);
	}

	public void checkHighlighting(StyledDocument doc){
        try {
        	resetStyle(doc);
            String text = doc.getText(0, doc.getLength());

            //STEP 1: Simple keywords
            for (String keyword: keywordTable ){
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
                		applyStyle(doc,pos,keyword.length(),keywordStyle);
                	}
                    pos += keyword.length();
                }
            }
            
            //STEP 2: @Marker
            int pos = 0;
            
            while ((pos = text.indexOf("@", pos)) >= 0) {
            	
            	int nextPos = pos+1;
            	while (nextPos < text.length()-1){
                	char next = text.charAt(nextPos);
                	if(next == '\t' || next == ' ' || next == '\n'){
                		applyStyle(doc,pos,nextPos-pos,atMarkerStyle);
                    	pos ++;
                		break;
                	}
                	nextPos++;
            	}
            	
            	pos ++;
            }
            
            //Step 3: Strings
            
            pos = 0;
            
            while (pos >= 0) {
            	Pattern p = Pattern.compile("\"(.*?)\"");
            	Matcher m = p.matcher(text);

            	if (m.find(pos)) {
            		applyStyle(doc,m.start(),m.end()-m.start(),stringStyle);
            		pos = m.end();
            	}
            	else{
            		break;
            	}
            }
            
            
            //Step 4: Single-Line-Commentary
            
            pos = 0;
            
            while (pos >= 0) {
            	Pattern p = Pattern.compile("//(.*?)\n");
            	Matcher m = p.matcher(text);

            	if (m.find(pos)) {
            		applyStyle(doc,m.start(),m.end()-m.start(),commentaryStyle);
            		pos = m.end();
            	}
            	else{
            		break;
            	}
            }

        } catch (BadLocationException e) {
        	//TODO: handle
        }

	}
	
	private void applyStyle(StyledDocument doc, int start, int length, SimpleAttributeSet s) {
		doc.setCharacterAttributes(start, length, s, false);
	}
	
	private void resetStyle(StyledDocument doc) {
		applyStyle(doc,0,doc.getLength(),baseStyle);

	}


	
}
