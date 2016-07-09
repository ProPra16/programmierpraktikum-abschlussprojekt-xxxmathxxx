package org.xxxmathxxx.tddt.gui.ide;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javafx.embed.swing.SwingNode;

/**Swing-based IDE-styled java-editor
 * This class uses tons of ugly hackfixes and workarounds. It is based on Swing because JavaFX does not enable the user to modify text-attributes.
 * @author Philipp Spohr, Jul 9, 2016
 * 
 */
public class TextEditor extends SwingNode {
	
	/**
	 * The scroll pane containing the editor, enabling large files without line wrapping
	 */
	private JScrollPane scrollPane;
	/**
	 * The pane that displays line numbers
	 */
	private LineNumberPane linePane;
	/**
	 * The actual text input field that enables writing of code
	 */
	private JTextPaneNoWrap editor;
	/**
	 * An additional Panel that prevents the editor from wrapping lines
	 */
	private JPanel editorWrapper;
		
	/**
	 * Default constructor
	 */
	public TextEditor(){
		editor = new JTextPaneNoWrap();
        editor.setEditorKit(new javax.swing.text.StyledEditorKit());
		
		editorWrapper = new JPanel(new BorderLayout());
		editorWrapper.add(editor);
		
		linePane = new LineNumberPane(editor); 
		editor.setDocument(new SyntaxDocument(linePane));
		
		scrollPane = new JScrollPane(editorWrapper);
		scrollPane.setRowHeaderView(linePane);

		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setContent(scrollPane);
			
	}

	/**Translates this function to an equivalent Swing function
	 * @see setLocation in JavaFX
	 * @param x The x position in pixel
	 * @param y The y position in pixel
	 */
	public void setLocation(int x, int y) {
		this.relocate(x, y);
	}

	/**Translates this function to an equivalent Swing function
	 * @param width the new width in pixel
	 * @param height the new height in pixel
	 */
	public void setSize(int width, int height) {
		scrollPane.setMinimumSize(new Dimension(width,height));
		scrollPane.setSize(width,height);
	}
	
	/**Sets the text of the editor, call is piped through to the actual text component
	 * @param rawText
	 */
	public void setText(String rawText) {
		editor.setText(rawText);
	}

	/**This gets the currently displayed text as a raw string, useful for compiling etc.
	 * @return The editor text as String
	 */
	public String getText() {
		return editor.getText();
	}	

}
