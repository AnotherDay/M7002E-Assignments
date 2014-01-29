package assignment2.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

import assignment2.globals.Constants;

public class Toolbar extends Panel {

	private JToggleButton moveButton, deleteButton;
	private JButton addPolygonButton;
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
	private JComboBox<String> comboBox;
	private Color backgroundColor = Constants.backgroundColor;
	
	public Toolbar()	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBackground(backgroundColor);
		
		CommandButtonGroup buttonGroup = new CommandButtonGroup();
		buttonGroup.add(moveButton);
		buttonGroup.add(deleteButton);
		buttonGroup.add(addPolygonButton);
		moveButton = new JToggleButton("Move");
		deleteButton = new JToggleButton("Delete");
		
		JPanel separatorPanel = new JPanel();
		addSeparator(separatorPanel);
		separatorPanel.setBackground(backgroundColor);
		
		addPolygonButton = new JButton("Place");
		
		model.addElement(Constants.PYRAMID_POLYGON);
		model.addElement(Constants.RECTANGLE_POLYGON);
		model.addElement(Constants.STAR_POLYGON);
		comboBox = new JComboBox<String>(model);

		JTextPane xInputLable = new JTextPane();
		xInputLable.setText("X:");
		xInputLable.setBackground(backgroundColor);
		JTextField xInput = new JTextField("", 3);

		JTextPane yInputLable = new JTextPane();
		yInputLable.setText("Y:");
		yInputLable.setBackground(backgroundColor);
		JTextField yInput = new JTextField("", 3);
		
		JTextPane zInputLable = new JTextPane();
		zInputLable.setText("Z:");
		zInputLable.setBackground(backgroundColor);
		JTextField zInput = new JTextField("", 3);
		
		this.add(moveButton);
		this.add(deleteButton);
		this.add(separatorPanel);
		this.add(comboBox);
		this.add(yInputLable);
		this.add(yInput);
		this.add(xInputLable);
		this.add(xInput);
		this.add(zInputLable);
		this.add(zInput);
		this.add(addPolygonButton);
	}
	
	public void addMoveButtonActionListner(ActionListener actionListner)	{
		moveButton.addActionListener(actionListner);
	}
	
	public void addDeleteButtonActionListner(ActionListener actionListner)	{
		deleteButton.addActionListener(actionListner);
	}

	public String getActivePolygon()	{
		return (String)comboBox.getSelectedItem();
	}
	
	private void addSeparator(JPanel panel)		{
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(new JSeparator(SwingConstants.VERTICAL));
		panel.add(new JSeparator(SwingConstants.VERTICAL));
	}
}
