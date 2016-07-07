package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;



public final class SyntaxHighlighting {
	
	public static SimpleAttributeSet baseStyle = new SimpleAttributeSet();
	static{
	}
	
	private static SimpleAttributeSet keywordBase = new SimpleAttributeSet();
	static{
	    StyleConstants.setForeground(keywordBase, Color.MAGENTA);
	    StyleConstants.setBold(keywordBase, true);
	}
	
	@SuppressWarnings("serial")
	public static final HashMap<String,SimpleAttributeSet> highlightTable=new HashMap<String,SimpleAttributeSet>(){{
		put(" if ",keywordBase);
		put(" while ",keywordBase);
		put(" private ",keywordBase);
		put(" public ",keywordBase);
		put(" final ",keywordBase);
		put(" true ",keywordBase);
		put(" false ",keywordBase);
		put(" static ",keywordBase);
		put(" void ",keywordBase);
		put(" extends ",keywordBase);
		put(" implements ",keywordBase);
		put(" class ",keywordBase);

	}};
}
