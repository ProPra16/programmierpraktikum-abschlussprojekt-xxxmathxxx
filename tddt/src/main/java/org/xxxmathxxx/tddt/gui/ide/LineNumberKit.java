package org.xxxmathxxx.tddt.gui.ide;

import javax.swing.text.StyledEditorKit;
import javax.swing.text.ViewFactory;

@SuppressWarnings("serial")
public class LineNumberKit extends StyledEditorKit {

	@Override
	public ViewFactory getViewFactory() {
		return new LineNumberFactory();
	}

}
