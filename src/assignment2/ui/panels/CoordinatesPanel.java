package assignment2.ui.panels;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class CoordinatesPanel extends JPanel {
	
	private final static int numberOfColumns = 2;
	private JTextField xInput, yInput, zInput;
	
	public CoordinatesPanel()	{
		LableText xInputLable = new LableText("X:");
		xInput = new JTextField("", numberOfColumns);

		LableText yInputLable = new LableText("Y:");
		yInput = new JTextField("", numberOfColumns);
		
		LableText zInputLable = new LableText("Z:");
		zInput = new JTextField("", numberOfColumns);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Constants.backgroundColor);
		this.add(xInputLable);
		this.add(xInput);
		this.add(yInputLable);
		this.add(yInput);
		this.add(zInputLable);
		this.add(zInput);
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
}
