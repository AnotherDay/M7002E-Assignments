package assignment2.ui.panels;

import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

public class StarSpecificationPanel extends Panel {
	
	private JTextField spanInput;
	
	public StarSpecificationPanel()	{
		LableText spanLable = new LableText("Span:");
		spanInput = new JTextField(3);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Constants.backgroundColor);
		this.add(spanLable);
		this.add(spanInput);
	}
	
	public float getSpanValue() throws NumberFormatException	{
		return Float.parseFloat(spanInput.getText());
	}
}
