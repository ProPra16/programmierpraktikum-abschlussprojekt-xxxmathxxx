package org.xxxmathxxx.tddt.gui.ide;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

@SuppressWarnings("serial")
public class SyntaxDocument extends DefaultStyledDocument{
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
	    super.insertString(offs, str, a);
		SyntaxHighlighting.getInstance().checkHighlighting(this);
    }
}
