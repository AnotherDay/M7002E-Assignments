package assignment2.ui.panels;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import assignment2.globals.Constants;

@SuppressWarnings("serial")
public class PolygonPickerPanel extends JPanel {
	
	private JComboBox<String> comboBox;
	
	public PolygonPickerPanel()	{
		super(new FlowLayout(FlowLayout.LEFT));
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		model.addElement(Constants.PYRAMID_POLYGON);
		model.addElement(Constants.RECTANGLE_POLYGON);
		model.addElement(Constants.STAR_POLYGON);
		
		comboBox = new JComboBox<String>(model);
		this.add(comboBox);
		this.setBackground(Constants.backgroundColor);
	}
	
	public void addActionListener(ActionListener actionListener)	{
		comboBox.addActionListener(actionListener);
	}
}
