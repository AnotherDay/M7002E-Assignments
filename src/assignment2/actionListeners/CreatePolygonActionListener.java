package assignment2.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.globals.Constants;
import assignment2.globals.ObjectContainer;
import assignment2.shapes.Pyramid;
import assignment2.shapes.Square;
import assignment2.shapes.Star;
import assignment2.ui.LeftToolbar;

public class CreatePolygonActionListener implements ActionListener {

	private LeftToolbar leftToolbar;
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	public CreatePolygonActionListener(LeftToolbar leftToolbar)		{
		this.leftToolbar = leftToolbar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try	{
			String activePolygon = leftToolbar.getActivePolygon();
			if(activePolygon.equals(Constants.PYRAMID_POLYGON))		{
				theObjectContainer.addPyramid(new Pyramid(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getPyramidHeight(), leftToolbar.getPyramidBaseWidth()));
			}
			else if(activePolygon.equals(Constants.SQUARE_POLYGON))	{
				theObjectContainer.addSquare(new Square(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getSquareEdgeLength()));
			}
			else if(activePolygon.equals(Constants.STAR_POLYGON))	{
				theObjectContainer.addStar(new Star(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getStarSpan()));
			}
		}
		catch(NumberFormatException nfe)	{
			//TODO: make a error message in the leftToolbar panel
			System.out.println("Number format error");
		}
	}

}
