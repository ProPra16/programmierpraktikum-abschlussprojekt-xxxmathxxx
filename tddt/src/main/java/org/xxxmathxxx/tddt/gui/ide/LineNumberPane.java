package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class LineNumberPane extends JLabel {

	private JTextPaneNoWrap textPane;
	private int fontHeight;
	private int lines;
	
	private static int lnWidth = 20;

	public LineNumberPane(JTextPaneNoWrap textPane) {
		super();
		this.textPane = textPane;
		// determine line height
		Font font = textPane.getFont();
		FontMetrics fontMetrics = textPane.getFontMetrics(font);
		fontHeight = fontMetrics.getAscent() + fontMetrics.getDescent();
	}
	
	public void paintComponent(Graphics g){
		for (int i=0;i<lines;i++){
			g.drawString(""+(i), 0, i*fontHeight);
		}
	}

	public void updateNumbers() {
		
		lines = textPane.getText().split("\n").length;
		
		this.setPreferredSize(new Dimension(lnWidth,lines*fontHeight));
		this.repaint();

	}

}
