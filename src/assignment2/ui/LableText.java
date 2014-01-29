package assignment2.ui;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import assignment2.globals.Constants;

public class LableText extends JTextPane {
	public LableText(String text)	{
		super();
		DefaultStyledDocument document = new DefaultStyledDocument();
		StyleContext context = new StyleContext();
		// build a style
		Style style = context.addStyle("test", null);
		// set some style properties
		StyleConstants.setForeground(style, Constants.textColor);
		// add some data to the document
		try {
			document.insertString(0, text, style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDocument(document);
		this.setBackground(Constants.backgroundColor);
	}
}
