package assignment2.ui.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class SquareSpecificationPanel extends JPanel {
	
	private JTextField edgeLenghtInput;
	
	public SquareSpecificationPanel()	{
		LableText edgeLenghtLable = new LableText("Edge Lenght:");
		edgeLenghtInput = new JTextField(2);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Constants.backgroundColor);
		this.add(edgeLenghtLable);
		this.add(edgeLenghtInput);
	}
	
	public float getEdgeLength() throws NumberFormatException	{
		return Float.parseFloat(edgeLenghtInput.getText());
	}
	
}
