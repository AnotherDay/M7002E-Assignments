package assignment2.ui.leftToolbar.panels;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JTextField;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class SphereSpecificationPanel extends JPanel {
	
	private JTextField radiusInput;
	
	public SphereSpecificationPanel()	{
		LableText radiusInputLable = new LableText("Radius:");
		radiusInput = new JTextField(2);
		
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(Constants.backgroundColor);
		this.add(radiusInputLable);
		this.add(radiusInput);
	}
	
	public float getSphereRadius() throws NumberFormatException	{
		return Float.parseFloat(radiusInput.getText());
	}
	
	public Vector<Component> getFocusTraversalOrder()	{
		Vector<Component> order = new Vector<Component>();
		order.add(radiusInput);
		return order;
	}
	
	
}
