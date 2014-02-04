package assignment2.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.awt.GLCanvas;

import assignment2.globals.Constants;
import assignment2.globals.ObjectContainer;
import assignment2.shapes.GLPyramidEntity;
import assignment2.shapes.GLSquareEntity;
import assignment2.shapes.GLStarEntity;
import assignment2.ui.leftToolbar.LeftToolbar;

public class CreatePolygonActionListener implements ActionListener {

	private LeftToolbar leftToolbar;
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	private GLCanvas canvas;
	
	public CreatePolygonActionListener(LeftToolbar leftToolbar, GLCanvas canvas)		{
		this.leftToolbar = leftToolbar;
		this.canvas = canvas;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try	{
			String activePolygon = leftToolbar.getActivePolygon();
			String printOutMessage = "Created: ";
			if(activePolygon.equals(Constants.PYRAMID_POLYGON))		{
				theObjectContainer.addPyramid(new GLPyramidEntity(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getPyramidHeight(), leftToolbar.getPyramidBaseWidth()));
				System.out.println(printOutMessage + "Pyramid");
			}
			else if(activePolygon.equals(Constants.SQUARE_POLYGON))	{
				theObjectContainer.addSquare(new GLSquareEntity(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getSquareEdgeLength()));
				System.out.println(printOutMessage + "Square");
			}
			else if(activePolygon.equals(Constants.STAR_POLYGON))	{
				theObjectContainer.addStar(new GLStarEntity(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getStarSpan()));
				System.out.println(printOutMessage + "Star");
			}
			canvas.display();
		}
		catch(NumberFormatException nfe)	{
			//TODO: make a error message in the leftToolbar panel
			System.out.println("Number format error");
		}
	}

}
