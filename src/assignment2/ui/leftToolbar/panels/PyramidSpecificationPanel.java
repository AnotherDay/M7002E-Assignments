package assignment2.ui.leftToolbar.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class PyramidSpecificationPanel extends Panel {
	
	private final static int INPUT_COLUMNS = 3;
	
	private JTextField baseWidthInput, heightInput;
	
	public PyramidSpecificationPanel()	{
		LableText baseWidthLable = new LableText("Base Width:");
		baseWidthInput = new JTextField("", INPUT_COLUMNS);
		
		JPanel baseWidthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		baseWidthPanel.setBackground(Constants.backgroundColor);
		baseWidthPanel.add(baseWidthLable);
		baseWidthPanel.add(baseWidthInput);
		
		LableText heightLable = new LableText("Height:");
		heightLable.setBorder(new EmptyBorder(0, 0, 0, 36)); //Makes both the boxes leveled
		heightInput = new JTextField("", INPUT_COLUMNS);
		
		JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		heightPanel.setBackground(Constants.backgroundColor);
		heightPanel.add(heightLable);
		heightPanel.add(heightInput);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Constants.backgroundColor);
		this.add(baseWidthPanel);
		this.add(heightPanel);
	}
	
	public float getPyramidHeight() throws NumberFormatException	{
		return Float.parseFloat(heightInput.getText());
	}
	
	public float getPyramidBaseWidth() throws NumberFormatException	{
		return Float.parseFloat(baseWidthInput.getText());
	}
	
	public Vector<Component> getFocusTraversalOrder()	{
		Vector<Component> order = new Vector<Component>();
		order.add(baseWidthInput);
		order.add(heightInput);
		return order;
	}
}
