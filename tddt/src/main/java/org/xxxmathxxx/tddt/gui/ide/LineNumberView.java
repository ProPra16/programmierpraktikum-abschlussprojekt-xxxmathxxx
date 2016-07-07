package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;

import javax.swing.event.DocumentEvent;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;


public class LineNumberView extends ParagraphView {

	private static short width= 30; //width of the line number part in pixel

	public LineNumberView(Element e) {
		super(e);
		super.setInsets((short)0, (short) (0 + width), (short)0, (short)0);
	}
	
    public void removeUpdate(DocumentEvent e, Shape a, ViewFactory f) {
        super.removeUpdate(e, a, f);
        resetBreakSpots();
    }
    public void insertUpdate(DocumentEvent e, Shape a, ViewFactory f) {
        super.insertUpdate(e, a, f);
        resetBreakSpots();
    }
 
    private void resetBreakSpots() {
        for (int i=0; i<layoutPool.getViewCount(); i++) {
            View v=layoutPool.getView(i);
            if (v instanceof LineLabelView) {
                ((LineLabelView)v).resetBreakSpots();
            }
        }
    }


	public void paintChild(Graphics g, Rectangle r, int n) {
		super.paintChild(g, r, n);
		int llc = getLastLineCount();
		int numberX = r.x - getLeftInset();
		int numberY = r.y + r.height - 5;
		g.drawString(Integer.toString(llc + n + 1), numberX, numberY);
	}

	private int getLastLineCount() {
		int lineCount = 0;
		View parent = this.getParent();
		int count = parent.getViewCount();
		for (int i = 0; i < count; i++) {
			if (parent.getView(i) == this) {
				break;
			} 
			else 
			{
				lineCount += parent.getView(i).getViewCount();
			}
		}
		return lineCount;
	}
}
