package assignment2.globals;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class TextStyleDocument extends DefaultStyledDocument {
	public TextStyleDocument()	{
		super();
		StyleContext context = new StyleContext();
		Style style = context.addStyle("test", null);
		StyleConstants.setForeground(style, Constants.textColor);
		try {
			this.insertString(0, "", style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
}
