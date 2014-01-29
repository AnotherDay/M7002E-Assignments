package assignment2.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import assignment2.globals.Constants;
import assignment2.globals.TextStyleDocument;
import assignment2.ui.panels.PolygonPickerPanel;
import assignment2.ui.panels.PyramidSpecificationPanel;
import assignment2.ui.panels.SquareSpecificationPanel;
import assignment2.ui.panels.StarSpecificationPanel;

@SuppressWarnings("serial")
public class CreatePolygonToolbar extends Panel implements ActionListener {
	
	private Color backgroundColor = Constants.backgroundColor;
	
	private PolygonPickerPanel polygonPickerPanel; //Consists of a single JCombobox
	private JPanel coordinatesPanel; //JTextField for coordinates x, y and z are at index 1, 3 and 5 respectively
	private JButton createPolygonButton;

	private PyramidSpecificationPanel pyramidSpecPanel = new PyramidSpecificationPanel();
	private SquareSpecificationPanel squareSpecPanel = new SquareSpecificationPanel();
	private StarSpecificationPanel starSpecPanel = new StarSpecificationPanel();
	
	private TextStyleDocument textStyle = new TextStyleDocument();
	
	private final static int BOTTOM_MARGIN_SMALL = 10;
	
	public CreatePolygonToolbar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		this.setLayout(new CardLayout());
		this.setBackground(backgroundColor);
		
		polygonPickerPanel = new PolygonPickerPanel();
		polygonPickerPanel.addActionListener(this);
		polygonPickerPanel.setBorder(new EmptyBorder(0, 0, BOTTOM_MARGIN_SMALL, 0));
		coordinatesPanel = getCoordinatesPanel(this.backgroundColor, 2);
		coordinatesPanel.setBorder(new EmptyBorder(0, 0, BOTTOM_MARGIN_SMALL, 0));
		
		createPolygonButton = new JButton("Create");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(createPolygonButton);
		buttonPanel.setBackground(backgroundColor);
		
		this.add(polygonPickerPanel);
		this.add(coordinatesPanel);
		this.add(pyramidSpecPanel);
		this.add(buttonPanel);
	}
	
	private JPanel getCoordinatesPanel(Color backgrouColor, int numberColumns)	{
		LableText xInputLable = new LableText("X:");
		JTextField xInput = new JTextField("", numberColumns);

		LableText yInputLable = new LableText("Y:");
		JTextField yInput = new JTextField("", numberColumns);
		
		LableText zInputLable = new LableText("Z:");
		JTextField zInput = new JTextField("", numberColumns);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panel.setBackground(backgroundColor);
		panel.add(xInputLable);
		panel.add(xInput);
		panel.add(yInputLable);
		panel.add(yInput);
		panel.add(zInputLable);
		panel.add(zInput);
		return panel;
	}
	
	private JPanel getPyramidSpecPanel(Color backgroundColor)	{
		LableText baseWidthLable = new LableText("Base Width:");
		JTextField baseWidthInput = new JTextField("", 2);
		
		JPanel baseWidthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		baseWidthPanel.setBackground(backgroundColor);
		baseWidthPanel.add(baseWidthLable);
		baseWidthPanel.add(baseWidthInput);
		
		LableText heightLable = new LableText("Height:");
		heightLable.setBorder(new EmptyBorder(0, 0, 0, 36)); //Makes both the boxes leveled
		JTextField heightInput = new JTextField("", 2);
		
		JPanel heightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		heightPanel.setBackground(backgroundColor);
		heightPanel.add(heightLable);
		heightPanel.add(heightInput);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(backgroundColor);
		panel.add(baseWidthPanel);
		panel.add(heightPanel);
		return panel;
	}
	
	/**
	 * Used for the polygon switcher 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> comboBox = (JComboBox<String>)e.getSource(); 
		this.remove(2);
		String polygonName = (String) comboBox.getSelectedItem();
		if(polygonName.equals(Constants.PYRAMID_POLYGON))	{
			this.add(pyramidSpecPanel, 2);
		}
		else if(polygonName.equals(Constants.RECTANGLE_POLYGON))	{
			this.add(squareSpecPanel, 2);
		}
		else if(polygonName.equals(Constants.STAR_POLYGON))	{
			this.add(starSpecPanel, 2);
		}
		else	{
			this.add(new LableText("Unspecified Polygon"), 2);
		}
		this.repaint();
		this.revalidate();
	}
}
