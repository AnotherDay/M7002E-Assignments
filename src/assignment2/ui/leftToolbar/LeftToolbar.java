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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import assignment2.globals.Constants;
import assignment2.ui.leftToolbar.panels.CoordinatesPanel;
import assignment2.ui.leftToolbar.panels.ObjectPickerPanel;
import assignment2.ui.leftToolbar.panels.PyramidSpecificationPanel;
import assignment2.ui.leftToolbar.panels.RGBAPanel;
import assignment2.ui.leftToolbar.panels.SingleFloatInputBoxPanel;
import assignment2.ui.uiParts.LableText;

@SuppressWarnings("serial")
public class LeftToolbar extends Panel{
	
	private Color backgroundColor = Constants.backgroundColor;
	
	private ObjectPickerPanel polygonPickerPanel = new ObjectPickerPanel();
	private CoordinatesPanel coordinatesPanel = new CoordinatesPanel();
	public RGBAPanel ambientPanel = new RGBAPanel("Ambient");
	public RGBAPanel specualPanel = new RGBAPanel("Specular");
	public RGBAPanel diffusePanel = new RGBAPanel("Diffuse");

	private PyramidSpecificationPanel pyramidSpecPanel = new PyramidSpecificationPanel();
	private SingleFloatInputBoxPanel starSpecPanel = new SingleFloatInputBoxPanel("Span: ", "");
	private SingleFloatInputBoxPanel squareSpecPanel = new SingleFloatInputBoxPanel("Edge Lenght: ", "");
	private SingleFloatInputBoxPanel sphereSpecPanel = new SingleFloatInputBoxPanel("Radius: ", "");
	private SingleFloatInputBoxPanel cubeSpecPanel = new SingleFloatInputBoxPanel("Edge Lenght: ", "");
	
	private JButton createPolygonButton;
	
	private Vector<Component> order = new Vector<Component>();
	
	private final static int SPECIFICATION_PANEL_INDEX = 6;
	private final static int BOTTOM_MARGIN_SMALL = 4;
	private final static String START_OF_ILLEGALSTATE_MESSAGE = "Polygon not choosen by the user. Current polygon choosen: ";
	
	public LeftToolbar() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(backgroundColor);
		
		EmptyBorder panelBorder = new EmptyBorder(0, 0, BOTTOM_MARGIN_SMALL, 0);
		polygonPickerPanel.setBorder(panelBorder);
		coordinatesPanel.setBorder(panelBorder);
		ambientPanel.setBorder(panelBorder);
		specualPanel.setBorder(panelBorder);
		diffusePanel.setBorder(panelBorder);
		
		createPolygonButton = new JButton("Create");
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(createPolygonButton);
		buttonPanel.setBackground(backgroundColor);
		
		this.updateFocusTraversalOrder();
		
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
		this.add(polygonPickerPanel);
		this.add(coordinatesPanel);
		this.add(ambientPanel);
		this.add(specualPanel);
		this.add(diffusePanel);
		this.add(pyramidSpecPanel);
		this.add(buttonPanel);
		this.add(new JSeparator(SwingConstants.HORIZONTAL));
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
			return squareSpecPanel.getValue();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
	}
	
	public float getStarSpan()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.STAR_POLYGON))	{
			return starSpecPanel.getValue();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
	}
	
	public float getSphereRadius()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.SPHERE_POLYGON))	{
			return sphereSpecPanel.getValue();
		}
		else	{
			throw new IllegalStateException(START_OF_ILLEGALSTATE_MESSAGE + polygonPickerPanel.getCurrentPolygon());
		}
	}
	
	public float getCubeEdgeLenght()	throws IllegalStateException	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.CUBE_POLYGON))	{
			return cubeSpecPanel.getValue();
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
			this.remove(SPECIFICATION_PANEL_INDEX);
			this.add(pyramidSpecPanel, SPECIFICATION_PANEL_INDEX);
			updatePanel();
		}
	}
	
	public void changeToSquareSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.SQUARE_POLYGON))	{
			this.remove(SPECIFICATION_PANEL_INDEX);
			this.add(squareSpecPanel, SPECIFICATION_PANEL_INDEX);
			updatePanel();
		}
	}
	
	public void changeToStarSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.STAR_POLYGON))	{
			this.remove(SPECIFICATION_PANEL_INDEX);
			this.add(starSpecPanel, SPECIFICATION_PANEL_INDEX);
			updatePanel();
		}
	}
	
	public void changeToSphereSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.SPHERE_POLYGON))	{
			this.remove(SPECIFICATION_PANEL_INDEX);
			this.add(sphereSpecPanel, SPECIFICATION_PANEL_INDEX);
			updatePanel();
		}
	}
	
	public void changeToCubeSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.CUBE_POLYGON))	{
			this.remove(SPECIFICATION_PANEL_INDEX);
			this.add(cubeSpecPanel, SPECIFICATION_PANEL_INDEX);
			updatePanel();
		}
	}
	
	public void changeToLightSpecificationPanel()	{
		if(polygonPickerPanel.getCurrentPolygon().equals(Constants.LIGHT_SOURCE))	{
			this.remove(SPECIFICATION_PANEL_INDEX);
			JPanel placeHolder = new JPanel();
			placeHolder.setBackground(backgroundColor);
			this.add(placeHolder, SPECIFICATION_PANEL_INDEX); 
			updatePanel();
		}
	}
	
	public void changeToUnspecifiedPolygonLable()	{
		this.remove(SPECIFICATION_PANEL_INDEX);
		this.add(new LableText("Unspecified Polygon"), SPECIFICATION_PANEL_INDEX);
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
		order.addAll(ambientPanel.getFocusTraversalOrder());
		order.addAll(specualPanel.getFocusTraversalOrder());
		order.addAll(diffusePanel.getFocusTraversalOrder());
		switch(polygonPickerPanel.getCurrentPolygon())	{
			case Constants.PYRAMID_POLYGON:
				order.addAll(pyramidSpecPanel.getFocusTraversalOrder());
			case Constants.SQUARE_POLYGON:
				order.addAll(squareSpecPanel.getFocusTraversalOrder());
			case Constants.STAR_POLYGON:
				order.addAll(starSpecPanel.getFocusTraversalOrder());
			case Constants.SPHERE_POLYGON:
				order.addAll(sphereSpecPanel.getFocusTraversalOrder());
			case Constants.CUBE_POLYGON:
				order.addAll(cubeSpecPanel.getFocusTraversalOrder());
		}
	}
	
}
