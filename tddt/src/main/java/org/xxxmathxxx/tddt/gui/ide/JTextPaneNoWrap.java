package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;

/**Simple extension of the basic swing JTextPane that doesn't wrap lines
 * @author Philipp Spohr, Jul 9, 2016
 *
 */
@SuppressWarnings("serial")
public class JTextPaneNoWrap extends JTextPane {

	/* (non-Javadoc)
	 * @see javax.swing.JEditorPane#getScrollableTracksViewportWidth()
	 */
	public boolean getScrollableTracksViewportWidth() {
	    Component parent = getParent();
	    ComponentUI ui = getUI();
    	if (parent != null){
    		return (ui.getPreferredSize(this).width <= parent.getSize().width);
    	}
    	else{
    		return true;
    	}
	}
	
	public void setText(String text){
		super.setText(text);
		//clean the first line that somehow appears
		Element root = getStyledDocument().getDefaultRootElement();
		Element first = root.getElement(0);
		try {
			getDocument().remove(first.getStartOffset(), first.getEndOffset());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
