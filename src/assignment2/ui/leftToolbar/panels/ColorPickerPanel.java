package assignment2.ui.leftToolbar.panels;

import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class ColorPickerPanel extends JPanel {
	
	private JComboBox<String> comboBox;
	
	public ColorPickerPanel()	{
		super(new FlowLayout(FlowLayout.LEFT));
		
		LableText comboBoxLable = new LableText("Color:");
		this.add(comboBoxLable);
		
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(Constants.COLOR_DEFAULT);
		for(String color : Constants.getAllColorConstants())	{
			model.addElement(color);
		}
		comboBox = new JComboBox<String>(model);
		this.add(comboBox);
		this.setBackground(Constants.backgroundColor);
	}
	
	public String getCurrentColor()	{
		return (String)comboBox.getSelectedItem();
	}
}
