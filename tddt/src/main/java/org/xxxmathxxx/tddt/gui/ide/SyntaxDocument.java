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
		
		int tabCounter = 0;

		//keep indentation
		if (str.equals("\n") || str.equals("\r")){
			
			int start = offs-1;

			
			while (start > 0){
				char c = getText(start,1).charAt(0);
				if (c == '\t'){ //tab
					tabCounter++;
				}
				else if(c == '\n' || c == '\r' ){ //newline
					break;
				}
				start--;
			}
			//System.out.println(tabCounter);
			String modString = str;
			for (int i=0;i<tabCounter;i++){
				modString = modString + "\t";
			}
			super.insertString(offs, modString, a);
			return;
		}
		
		

		
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
