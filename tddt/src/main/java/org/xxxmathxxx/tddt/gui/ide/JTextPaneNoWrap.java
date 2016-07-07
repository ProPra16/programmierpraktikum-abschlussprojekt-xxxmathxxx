package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Component;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;

@SuppressWarnings("serial")
public class JTextPaneNoWrap extends JTextPane {

	public boolean getScrollableTracksViewportWidth() {
	    Component parent = getParent();
	    ComponentUI ui = getUI();

	    return parent != null ? (ui.getPreferredSize(this).width <= parent
	        .getSize().width) : true;
	}

}
