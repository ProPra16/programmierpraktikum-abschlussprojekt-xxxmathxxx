package org.xxxmathxxx.tddt.gui.ide;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;

@SuppressWarnings("serial")
public class SyntaxDocument extends DefaultStyledDocument {

	LineNumberPane linePane;

	public SyntaxDocument(LineNumberPane linePane) {
		this.linePane = linePane;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		super.insertString(offs, str, a);
		updateDocument();
	}

	@Override
	public void remove(int offs, int len) throws BadLocationException {
		super.remove(offs, len);
		updateDocument();
	}

	void updateDocument() {
		SyntaxHighlighting.getInstance().checkHighlighting(this);
		if (linePane != null) {
			linePane.updateNumbers();
		}
	}
}
