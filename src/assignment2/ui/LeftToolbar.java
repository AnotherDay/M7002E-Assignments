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
import assignment2.ui.panels.CoordinatesPanel;
import assignment2.ui.panels.PolygonPickerPanel;
import assignment2.ui.panels.PyramidSpecificationPanel;
import assignment2.ui.panels.SquareSpecificationPanel;
import assignment2.ui.panels.StarSpecificationPanel;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class LeftToolbar extends Panel implements ActionListener {
	
	private Color backgroundColor = Constants.backgroundColor;
	
	private PolygonPickerPanel polygonPickerPanel = new PolygonPickerPanel();
	private CoordinatesPanel coordinatesPanel = new CoordinatesPanel();

	private PyramidSpecificationPanel pyramidSpecPanel = new PyramidSpecificationPanel();
	private SquareSpecificationPanel squareSpecPanel = new SquareSpecificationPanel();
	private StarSpecificationPanel starSpecPanel = new StarSpecificationPanel();
	
	private JButton createPolygonButton;
	
	private final static int BOTTOM_MARGIN_SMALL = 10;
	private final static String START_OF_ILLEGALSTATE_MESSAGE = "Polygon not choosen by the user. Current polygon choosen: ";
	
	public LeftToolbar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(backgroundColor);
		
		polygonPickerPanel = new PolygonPickerPanel();
		polygonPickerPanel.addActionListener(this);
		polygonPickerPanel.setBorder(new EmptyBorder(0, 0, BOTTOM_MARGIN_SMALL, 0));
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
	
	public void addButtonActionListener(ActionListener actionLitstener)	{
		createPolygonButton.addActionListener(actionLitstener);
	}
	
	public String getActivePolygon()	{
		return polygonPickerPanel.getCurrentPolygon();
	}
	
	public float getXCoordinate() throws NumberFormatException	{
		return coordinatesPanel.getXValue();
	}
	
	public float getYCoordinate() throws NumberFormatException	{
		return coordinatesPanel.getYValue();
	}
	
	public float getZCoordinate() throws NumberFormatException	{
		return coordinatesPanel.getZValue();
	}
	
	public float getPyramidHeight()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.PYRAMID_POLYGON))	{
			return pyramidSpecPanel.getPyramidHeight();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
	}
	
	public float getPyramidBaseWidth()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.PYRAMID_POLYGON))	{
			return pyramidSpecPanel.getPyramidBaseWidth();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
	}
	
	public float getSquareEdgeLength()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.SQUARE_POLYGON))	{
			return squareSpecPanel.getEdgeLength();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
	}
	
	public float getStarSpan()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.PYRAMID_POLYGON))	{
			return starSpecPanel.getSpanValue();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
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
		else if(polygonName.equals(Constants.SQUARE_POLYGON))	{
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
