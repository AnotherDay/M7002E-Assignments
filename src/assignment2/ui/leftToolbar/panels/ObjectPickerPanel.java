package assignment2.ui.leftToolbar.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import assignment2.globals.Constants;

@SuppressWarnings("serial")
public class ObjectPickerPanel extends JPanel {
	
	private JComboBox<String> comboBox;
	
	public ObjectPickerPanel()	{
		super(new FlowLayout(FlowLayout.LEFT));
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		
		for(String objectString : Constants.getObjectList())	{
			model.addElement(objectString);
		}
		
		comboBox = new JComboBox<String>(model);
		this.add(comboBox);
		this.setBackground(Constants.backgroundColor);
	}
	
	public void addActionListener(ActionListener actionListener)	{
		comboBox.addActionListener(actionListener);
	}
	
	public String getCurrentPolygon()	{
		return (String)comboBox.getSelectedItem();
	}
}
