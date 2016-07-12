package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextPane;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.BadLocationException;


/**Simple extension of the basic swing JTextPane that doesn't wrap lines
 * @author Philipp Spohr, Jul 9, 2016
 *
 */
@SuppressWarnings("serial")
public class JTextPaneNoWrap extends JTextPane {
		
	public JTextPaneNoWrap(){
		String system = System.getProperty("os.name").toLowerCase();
		if (system.indexOf("win") >= 0){
			this.addKeyListener(windowsBugListener);
		}
	}

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
	
	KeyListener windowsBugListener = new KeyListener(){
		
		//did i mention that SwingNode javafx swing mashing is buggy as ... 

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.isAltDown() && e.getKeyChar() == '7'){
				try {
					getDocument().insertString(getCaretPosition(), "{", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.isAltDown() && e.getKeyChar() == '0'){
				try {
					getDocument().insertString(getCaretPosition(), "}", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.isAltDown() && e.getKeyChar() == '8'){
				try {
					getDocument().insertString(getCaretPosition(), "[", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
			else if (e.isAltDown() && e.getKeyChar() == '9'){
				try {
					getDocument().insertString(getCaretPosition(), "]", null);
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	};


}
