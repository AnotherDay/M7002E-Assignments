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
public class RGBAPanel extends JPanel {

	private JPanel lablePanel =  new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
	private final static int numberOfColumns = 2;
	private JTextField rInput, gInput, bInput, aInput;
	
	
	public RGBAPanel(String lable)	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(lablePanel);
		this.add(inputPanel);
		this.setBackground(Constants.backgroundColor);
		createLablePanel(lable);
		createInputPanel();
	}
	
	private void createLablePanel(String lable)	{
		LableText ambientLable = new LableText(lable);
		lablePanel.setBackground(Constants.backgroundColor);
		lablePanel.add(ambientLable);
	}
	
	private void createInputPanel()	{
		LableText rInputLable = new LableText("R:");
		rInput = new JTextField("", numberOfColumns);

		LableText gInputLable = new LableText("G:");
		gInput = new JTextField("", numberOfColumns);
		
		LableText bInputLable = new LableText("B:");
		bInput = new JTextField("", numberOfColumns);
		
		LableText aInputLable = new LableText("A:");
		aInput = new JTextField("", numberOfColumns);
		
		inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		inputPanel.setBackground(Constants.backgroundColor);
		inputPanel.add(rInputLable);
		inputPanel.add(rInput);
		inputPanel.add(gInputLable);
		inputPanel.add(gInput);
		inputPanel.add(bInputLable);
		inputPanel.add(bInput);
		inputPanel.add(aInputLable);
		inputPanel.add(aInput);
	}
	
	public float getRValue() throws NumberFormatException	{
		return Float.parseFloat(rInput.getText());
	}
	
	public float getGValue() throws NumberFormatException	{
		return Float.parseFloat(gInput.getText());
	}
	
	public float getBValue() throws NumberFormatException	{
		return Float.parseFloat(bInput.getText());
	}
	
	public float getAValue() throws NumberFormatException	{
		return Float.parseFloat(aInput.getText());
	}
	
	public float[] getRGBAValue() throws NumberFormatException	{
		return new float[] {getRValue(), getGValue(), getBValue(), getAValue()}; 
	}
	
	public Vector<Component> getFocusTraversalOrder()	{
		Vector<Component> order = new Vector<Component>();
		order.add(rInput);
		order.add(gInput);
		order.add(bInput);
		order.add(aInput);
		return order;
	}
}
