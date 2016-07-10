package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;

/**Simple extension of the basic swing JTextPane that doesn't wrap lines
 * @author Philipp Spohr, Jul 9, 2016
 *
 */
@SuppressWarnings("serial")
public class JTextPaneNoWrap extends JTextPane {

	/* (non-Javadoc)
	 * @see javax.swing.JEditorPane#getScrollableTracksViewportWidth()
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see javax.swing.JEditorPane#setText(java.lang.String)
	 */
	@Override
	public void setText(String text){
		//clean the blank lines that somehow appears
		text = text.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		super.setText(text);
	}

}
