package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;

/** This class describes a label that can be attached to a JTextPane as paragraphView to display line numbers
 * @author xxxMathxxx 2016
 *
 */
@SuppressWarnings("serial")//can be supressed because this is never serialized
public class LineNumberPane extends JLabel {

	/**
	 * The associated JTextPaneNoWrap for which the line numbers are displayed
	 * @see JTextPaneNoWrap
	 */
	private JTextPaneNoWrap textPane;
	
	/**
	 * The font height, this is required to calculate the space between line numbers
	 */
	
	private int fontHeight;
	/**
	 * The current number of lines in the text pane
	 */
	private int lines;
	
	/**
	 * As our software uses a fixed resolution this is a fixed value too. This might lead to problems with insanely large line numbers.
	 */
	private static int LINE_NUMBER_BAR_WIDTH = 20;

	/**Default constructor, creates a new LineNumberPane and links it with a JTextPaneNoWrap
	 * @param textPane The linked JTextPaneNoWrap
	 */
	public LineNumberPane(JTextPaneNoWrap textPane) {
		super();
		this.textPane = textPane;
		// determine line height
		Font font = textPane.getFont();
		FontMetrics fontMetrics = textPane.getFontMetrics(font);
		fontHeight = fontMetrics.getAscent() + fontMetrics.getDescent();
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		for (int i=0;i<=lines;i++){
			g.drawString(""+(i), 0, i*fontHeight);
		}
	}

	/**
	 * This updates the number of lines and then repaints the label.
	 */
	public void updateNumbers() {
		
		lines = textPane.getText().split("\n").length;
		
		this.setPreferredSize(new Dimension(LINE_NUMBER_BAR_WIDTH,lines*fontHeight));
		this.repaint();

	}

}
