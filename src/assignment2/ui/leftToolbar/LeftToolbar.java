package assignment2.ui.leftToolbar;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import assignment2.globals.Constants;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class LeftToolbar extends Panel{
	
	private Color backgroundColor = Constants.backgroundColor;
	
	private PolygonPickerPanel polygonPickerPanel = new PolygonPickerPanel();
	private CoordinatesPanel coordinatesPanel = new CoordinatesPanel();

	private PyramidSpecificationPanel pyramidSpecPanel = new PyramidSpecificationPanel();
	private SquareSpecificationPanel squareSpecPanel = new SquareSpecificationPanel();
	private StarSpecificationPanel starSpecPanel = new StarSpecificationPanel();
	
	private JButton createPolygonButton;
	
	private Vector<Component> order = new Vector<Component>();
	
	private final static int BOTTOM_MARGIN_SMALL = 10;
	private final static String START_OF_ILLEGALSTATE_MESSAGE = "Polygon not choosen by the user. Current polygon choosen: ";
	
	public LeftToolbar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(backgroundColor);
		
		polygonPickerPanel = new PolygonPickerPanel();
		polygonPickerPanel.setBorder(new EmptyBorder(0, 0, BOTTOM_MARGIN_SMALL, 0));
		coordinatesPanel.setBorder(new EmptyBorder(0, 0, BOTTOM_MARGIN_SMALL, 0));
		
		createPolygonButton = new JButton("Create");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(createPolygonButton);
		buttonPanel.setBackground(backgroundColor);
		
		this.updateFocusTraversalOrder();
		
		this.add(polygonPickerPanel);
		this.add(coordinatesPanel);
		this.add(pyramidSpecPanel);
		this.add(buttonPanel);
	}
	
	public void addButtonActionListener(ActionListener actionLitstener)	{
		createPolygonButton.addActionListener(actionLitstener);
	}
	
	public void addPolygonPickerActionListener(ActionListener actionListener)	{
		polygonPickerPanel.addActionListener(actionListener);
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
	
	public Vector<Component> getFocusTraversalOrder()	{
		return order;
	}
	
	public void changeToPyramidSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.PYRAMID_POLYGON))	{
			this.remove(2);
			this.add(pyramidSpecPanel, 2);
			updatePanel();
		}
	}
	
	public void changeToSquareSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.SQUARE_POLYGON))	{
			this.remove(2);
			this.add(squareSpecPanel, 2);
			updatePanel();
		}
	}
	
	public void changeToStarSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.STAR_POLYGON))	{
			this.remove(2);
			this.add(starSpecPanel, 2);
			updatePanel();
		}
	}
	
	public void changeToUnspecifiedPolygonLable()	{
		this.remove(2);
		this.add(new LableText("Unspecified Polygon"), 2);
		this.updatePanel();
	}
	
	private void updatePanel()	{
		updateFocusTraversalOrder();
		this.repaint();
		this.revalidate();
	}
	
	private void updateFocusTraversalOrder()	{
		order = new Vector<Component>();
		order.addAll(coordinatesPanel.getFocusTraversalOrder());
		switch(polygonPickerPanel.getCurrentPolygon())	{
			case Constants.PYRAMID_POLYGON:
				order.addAll(pyramidSpecPanel.getFocusTraversalOrder());
			case Constants.SQUARE_POLYGON:
				order.addAll(squareSpecPanel.getFocusTraversalOrder());
			case Constants.STAR_POLYGON:
				order.addAll(starSpecPanel.getFocusTraversalOrder());
		}
	}
	
}