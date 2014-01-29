package assignment2.ui.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.LableText;

public class SquareSpecificationPanel extends JPanel {
	public SquareSpecificationPanel()	{
		LableText edgeLenghtLable = new LableText("Edge Lenght:");
		JTextField edgeLenghtInput = new JTextField(2);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Constants.backgroundColor);
		this.add(edgeLenghtLable);
		this.add(edgeLenghtInput);
	}
}
