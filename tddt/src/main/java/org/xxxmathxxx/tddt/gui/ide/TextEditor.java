package org.xxxmathxxx.tddt.gui.ide;

import java.awt.Dimension;

import javax.swing.JScrollPane;


import javafx.embed.swing.SwingNode;

public class TextEditor extends SwingNode {
	//DONT LOOK AT THIS CLASS FOR CLEAN PROGRAMMING OR GOOD CODESTYLE, BTW F*** JAVAFX
	
	private JTextPaneNoWrap editor;
	
	private JScrollPane scrollPane;

	
	
	public TextEditor(){
		editor = new JTextPaneNoWrap();
        editor.setEditorKit(new javax.swing.text.StyledEditorKit());
		editor.setDocument(new SyntaxDocument());
		
		scrollPane = new JScrollPane(editor);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.setContent(scrollPane);
			
	}

	public void setLocation(int x, int y) {
		this.relocate(x, y);
	}

	public void setBounds(int i, int j, int width, int height) {
		editor.setBounds(i, j, width, height);
		//JAVAFX is 100% bug-free and probably uses TDD
		editor.setSize(width,height);
		editor.setMinimumSize(new Dimension(width,height));
	}
	
	public void setText(String rawText) {
		editor.setText(rawText);
	}

	public String getText() {
		return editor.getText();
	}	

}
