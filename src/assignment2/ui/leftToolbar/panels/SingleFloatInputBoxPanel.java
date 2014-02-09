package assignment2.ui.leftToolbar.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

public class SingleFloatInputBoxPanel extends JPanel {

	private JTextField input;
	
	public SingleFloatInputBoxPanel(String inputLableText, String defaultInput)	{
			LableText inputLable = new LableText(inputLableText);
			input = new JTextField(defaultInput, 2);
			
			this.setLayout(new FlowLayout(FlowLayout.LEFT));
			this.setBackground(Constants.backgroundColor);
			this.add(inputLable);
			this.add(input);
	}
	
	public float getValue() throws NumberFormatException	{
		return Float.parseFloat(input.getText());
	}
	
	public Vector<Component> getFocusTraversalOrder()	{
		Vector<Component> order = new Vector<Component>();
		order.add(input);
		return order;
	}
}
