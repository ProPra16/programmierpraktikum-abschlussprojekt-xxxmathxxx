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
						"int",
						"new",
						"return",
						"else",
						"true",
						"false",
						"this",
						"boolean"
						)
				);
	}

	public void checkHighlighting(StyledDocument doc){
        try {
        	
        	resetStyle(doc);
        	
            String text = doc.getText(0, doc.getLength());       
            
            Pattern p;
            Matcher m;

            //STEP 1: Simple keywords
            for (String keyword: keywordTable ){
            	p = Pattern.compile("\\b"+keyword+"\\b");
            	m = p.matcher(text);
                while (m.find()) {
                	applyStyle(doc,m.start(),m.end()-m.start(),keywordStyle);
                }
            }
            //STEP 2: @Marker            
        	p = Pattern.compile("@\\w+[\\W^$]");
        	m = p.matcher(text);
            while (m.find()) {
            	applyStyle(doc,m.start(),m.end()-m.start(),atMarkerStyle);
            }
            
            //Step 3: Strings
        	p = Pattern.compile("\"\\w+\"");
        	m = p.matcher(text);
            while (m.find()) {
            	applyStyle(doc,m.start(),m.end()-m.start(),stringStyle);
            }
            //Step 4: Single-Line-Commentary
        	p = Pattern.compile("\\/\\/.*[\\r\\n$]");
        	m = p.matcher(text);
            while (m.find()) {
            	applyStyle(doc,m.start(),m.end()-m.start(),commentaryStyle);
            }
            //Step 5: Multi-Line-Commentary
        	p = Pattern.compile("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)");
        	m = p.matcher(text);
            while (m.find()) {
            	applyStyle(doc,m.start(),m.end()-m.start(),commentaryStyle);
            }
        } catch (BadLocationException e) {
        	//TODO: handle
        }

	}
	
	private void applyStyle(StyledDocument doc, int start, int length, SimpleAttributeSet s) {
		doc.setCharacterAttributes(start, length, s, true);
	}
	
	private void resetStyle(StyledDocument doc) {
		applyStyle(doc,0,doc.getLength(),baseStyle);
	}


	
}
