package org.xxxmathxxx.tddt.gui.ide;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javafx.embed.swing.SwingNode;

public class TextEditor extends SwingNode {
	//DONT LOOK AT THIS CLASS FOR CLEAN PROGRAMMING OR GOOD CODESTYLE, BTW F*** JAVAFX
	
	private JScrollPane scrollPane;
	private LineNumberPane linePane;
	private JTextPaneNoWrap editor;
	private JPanel editorWrapper;
		
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

	public void setLocation(int x, int y) {
		this.relocate(x, y);
	}

	public void setBounds(int x, int y, int width, int height) {
		scrollPane.setSize(width,height);
	}
	
	public void setText(String rawText) {
		editor.setText(rawText);
	}

	public String getText() {
		return editor.getText();
	}	

}
