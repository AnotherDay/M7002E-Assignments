package assignment2.ui.leftToolbar.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Vector;

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
	
	public Vector<Component> getFocusTraversalOrder()	{
		Vector<Component> order = new Vector<Component>();
		order.add(edgeLenghtInput);
		return order;
	}
}
