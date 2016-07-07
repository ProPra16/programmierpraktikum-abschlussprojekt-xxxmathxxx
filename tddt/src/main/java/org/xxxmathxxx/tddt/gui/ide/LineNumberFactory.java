package org.xxxmathxxx.tddt.gui.ide;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.IconView;
import javax.swing.text.LabelView;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class LineNumberFactory implements ViewFactory {
	public View create(Element e) {
		String s = e.getName();
		if (s != null)
			if (s.equals(AbstractDocument.ContentElementName)) {
				return new LabelView(e);
			} else if (s.equals(AbstractDocument.ParagraphElementName)) {
				return new LineNumberView(e);
			} else if (s.equals(AbstractDocument.SectionElementName)) {
				return new BoxView(e, View.Y_AXIS);
			} else if (s.equals(StyleConstants.ComponentElementName)) {
				return new ComponentView(e);
			} else if (s.equals(StyleConstants.IconElementName)) {
				return new IconView(e);
			}
		return new LabelView(e);
	}
}
