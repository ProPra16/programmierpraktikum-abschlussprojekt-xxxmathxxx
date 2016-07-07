package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.text.DefaultHighlighter;


public final class SyntaxHighlighting {
	
	@SuppressWarnings("serial")
	public static final HashMap<String,HLPainterCustom> highlightTable=new HashMap<String,HLPainterCustom>(){{
		put("if",new HLPainterCustom(Color.RED));
	}};


	static class HLPainterCustom extends DefaultHighlighter.DefaultHighlightPainter {

	    public HLPainterCustom(Color color) {
	        super(color);
	    }
	}
}
