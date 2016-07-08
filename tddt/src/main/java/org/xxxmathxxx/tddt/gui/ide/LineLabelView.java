package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Shape;

import javax.swing.event.DocumentEvent;
import javax.swing.text.Element;
import javax.swing.text.LabelView;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class LineLabelView extends LabelView {

	boolean needsReset = false;

	public LineLabelView(Element elem) {
		super(elem);
	}

	public View breakView(int axis, int p0, float pos, float len) {
		if (axis == View.X_AXIS) {
			resetBreakSpots();
		}
		return super.breakView(axis, p0, pos, len);
	}

	public void resetBreakSpots() {
		needsReset = true;
		removeUpdate(null, null, null);
		needsReset = false;
	}

	public void removeUpdate(DocumentEvent e, Shape a, ViewFactory f) {
		super.removeUpdate(e, a, f);
	}

	public void preferenceChanged(View child, boolean width, boolean height) {
		if (!needsReset) {
			super.preferenceChanged(child, width, height);
		}
	}

}
