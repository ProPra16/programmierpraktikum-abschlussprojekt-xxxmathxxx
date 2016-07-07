package org.xxxmathxxx.tddt.gui.ide;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javafx.embed.swing.SwingNode;

public class TextEditor extends SwingNode {
	//DONT LOOK AT THIS CLASS FOR CLEAN PROGRAMMING OR GOOD CODESTYLE, BTW F*** JAVAFX
	
	private JTextPane editor;
	
	private JPanel frame;
		
	private JScrollPane scrollPane;
	
	private JTextArea lineNumbers;
	
	public TextEditor(){
		editor = new JTextPane();
		
		frame = new JPanel( new BorderLayout() );
		
		editor.setEditorKit(new javax.swing.text.StyledEditorKit());
        lineNumbers = new JTextArea("1\n2\n3");
		scrollPane = new JScrollPane( frame );

        scrollPane.setRowHeaderView(lineNumbers );
		
		frame.add( editor );
		
		
		editor.getDocument().addDocumentListener(new ChangeListener());
		
		this.setContent(scrollPane);
	}
	
	private void updateLineNumbers() {
	}
	
	private class ChangeListener implements DocumentListener{

		@Override
		public void insertUpdate(DocumentEvent e) {
			SyntaxHighlighting.getInstance().checkHighlighting(editor.getStyledDocument());		
			updateLineNumbers();
		}



		@Override
		public void removeUpdate(DocumentEvent e) {
			SyntaxHighlighting.getInstance().checkHighlighting(editor.getStyledDocument());
			updateLineNumbers();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
			updateLineNumbers();
		}
		
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
