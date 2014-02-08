package assignment2.ui.leftToolbar.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class CoordinatesPanel extends JPanel {
	
	private JPanel lablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private final static int numberOfColumns = 2;
	private JTextField xInput, yInput, zInput;
	
	public CoordinatesPanel()	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Constants.backgroundColor);
		this.add(lablePanel);
		this.add(inputPanel);
		
		LableText coordinatesLable = new LableText("Coordinates");
		lablePanel.setBackground(Constants.backgroundColor);
		lablePanel.add(coordinatesLable);
		
		LableText xInputLable = new LableText("X:");
		xInput = new JTextField("", numberOfColumns);

		LableText yInputLable = new LableText("Y:");
		yInput = new JTextField("", numberOfColumns);
		
		LableText zInputLable = new LableText("Z:");
		zInput = new JTextField("", numberOfColumns);
		
		inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		inputPanel.setBackground(Constants.backgroundColor);
		inputPanel.add(xInputLable);
		inputPanel.add(xInput);
		inputPanel.add(yInputLable);
		inputPanel.add(yInput);
		inputPanel.add(zInputLable);
		inputPanel.add(zInput);
	}
	
	public float getXValue() throws NumberFormatException	{
		return Float.parseFloat(xInput.getText());
	}
	
	public float getYValue() throws NumberFormatException	{
		return Float.parseFloat(yInput.getText());
	}
	
	public float getZValue() throws NumberFormatException	{
		return Float.parseFloat(zInput.getText());
	}
	
	public Vector<Component> getFocusTraversalOrder()	{
		Vector<Component> order = new Vector<Component>();
		order.add(xInput);
		order.add(yInput);
		order.add(zInput);
		return order;
	}
}
