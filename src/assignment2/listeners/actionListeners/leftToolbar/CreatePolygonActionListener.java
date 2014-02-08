package assignment2.listeners.actionListeners.leftToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.awt.GLCanvas;

import assignment2.globals.Constants;
import assignment2.globals.ObjectContainer;
import assignment2.openGL.shapes.GLEntity;
import assignment2.openGL.shapes.GLPyramidEntity;
import assignment2.openGL.shapes.GLSquareEntity;
import assignment2.openGL.shapes.GLStarEntity;
import assignment2.ui.leftToolbar.LeftToolbar;

public class CreatePolygonActionListener implements ActionListener {

	private LeftToolbar leftToolbar;
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	public CreatePolygonActionListener(LeftToolbar leftToolbar, GLCanvas canvas)		{
		this.leftToolbar = leftToolbar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try	{
			String activePolygon = leftToolbar.getActivePolygon();
			String printOutMessage = "Created: ";
			GLEntity glEntity;
			if(activePolygon.equals(Constants.PYRAMID_POLYGON))		{
				glEntity = new GLPyramidEntity(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getPyramidHeight(), leftToolbar.getPyramidBaseWidth());
				System.out.println(printOutMessage + "Pyramid");
			}
			else if(activePolygon.equals(Constants.SQUARE_POLYGON))	{
				glEntity = new GLSquareEntity(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getSquareEdgeLength());
				System.out.println(printOutMessage + "Square");
			}
			else	{
				glEntity = new GLStarEntity(leftToolbar.getXCoordinate(), 
						leftToolbar.getYCoordinate(), leftToolbar.getZCoordinate(), 
						leftToolbar.getStarSpan());
				System.out.println(printOutMessage + "Star");
			}
			try	{
				glEntity.setAmbientRGBA(leftToolbar.ambientPanel.getRGBAValue());
				glEntity.setSpecularRGBA(leftToolbar.specualPanel.getRGBAValue());
				glEntity.setDiffuseRGBA(leftToolbar.diffusePanel.getRGBAValue());
			}
			catch(NumberFormatException nfe)	{
				System.out.println("Detected empty string, using default values");
			}
			theObjectContainer.addGLEntity(glEntity);
		}
		catch(NumberFormatException nfe)	{
			//TODO: make a error message in the leftToolbar panel
			System.out.println("Number format error");
		}
	}
	
}
