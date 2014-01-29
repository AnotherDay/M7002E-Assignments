package assignment2.ui.panels;

import java.awt.FlowLayout;
import java.awt.Panel;

import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.LableText;

public class StarSpecificationPanel extends Panel {
	public StarSpecificationPanel()	{
		LableText spanLable = new LableText("Span:");
		JTextField spanInput = new JTextField(3);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Constants.backgroundColor);
		this.add(spanLable);
		this.add(spanInput);
	}
}
