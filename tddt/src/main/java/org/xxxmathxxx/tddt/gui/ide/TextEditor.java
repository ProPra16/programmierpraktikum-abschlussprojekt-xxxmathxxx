package org.xxxmathxxx.tddt.gui.ide;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.text.StyledEditorKit;

import org.xxxmathxxx.tddt.logging.TDDTLogManager;

import javafx.application.Platform;
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
        editor.setEditorKit(new StyledEditorKit());
        
		editorWrapper = new JPanel(new BorderLayout());
		editorWrapper.add(editor);
		
		linePane = new LineNumberPane(editor); 
		editor.setDocument(new SyntaxDocument(linePane));
		
		scrollPane = new JScrollPane(editorWrapper);
		scrollPane.setRowHeaderView(linePane);
		
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				setContent(scrollPane);			
			}
		});
		
		fixWindowsGraphicsBugs();
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
		scrollPane.setPreferredSize(new Dimension(width,height));
	}
	
	/**Sets the text of the editor, call is piped through to the actual text component
	 * @param rawText The text that should be displayed in the editor
	 */
	public void setText(String rawText) {
		editor.removeAll();
		editor.setText(rawText);
	}

	/**JavaFX and Swing is weird and behavior varies depending on OS
	 * This is a simple helper function that provides a workaround graphical tearing on windows
	 */
	public void fixWindowsGraphicsBugs() {
		//don't even think about what is happening here or why it is working
		SwingUtilities.invokeLater(
				new Runnable()
				{
					@Override
					public void run() {
						TDDTLogManager.getInstance().logMessage("Graphics Fix for Windows applied @ ");
						scrollPane.requestFocus();
						scrollPane.revalidate();
						scrollPane.repaint();
					}
				}
		);
	}

	/**This gets the currently displayed text as a raw string, useful for compiling etc.
	 * @return The editor text as String
	 */
	public String getText() {
		return editor.getText();
	}	

}
