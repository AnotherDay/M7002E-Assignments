package assignment2.listeners.actionListeners.leftToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.awt.GLCanvas;

import assignment2.globals.Constants;
import assignment2.globals.ObjectContainer;
import assignment2.openGL.GLEntity;
import assignment2.openGL.GLLightSourceEntity;
import assignment2.openGL.shapes.GLPyramidEntity;
import assignment2.openGL.shapes.GLSquareEntity;
import assignment2.openGL.shapes.GLStarEntity;
import assignment2.ui.leftToolbar.LeftToolbar;

public class CreateActionListener implements ActionListener {

	private LeftToolbar leftToolbar;
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	public CreateActionListener(LeftToolbar leftToolbar, GLCanvas canvas)		{
		this.leftToolbar = leftToolbar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try	{
			String activePolygon = leftToolbar.getActivePolygon();
			String printOutMessage = "Created: ";
			
			GLEntity glEntity;
			float xPos = leftToolbar.getXCoordinate();
			float yPos = leftToolbar.getYCoordinate();
			float zPos = leftToolbar.getZCoordinate();
			if(activePolygon.equals(Constants.PYRAMID_POLYGON))		{
				glEntity = new GLPyramidEntity(xPos, yPos, zPos, 
						leftToolbar.getPyramidHeight(), leftToolbar.getPyramidBaseWidth());
				printOutMessage = printOutMessage + Constants.PYRAMID_POLYGON;
			}
			else if(activePolygon.equals(Constants.SQUARE_POLYGON))	{
				glEntity = new GLSquareEntity(xPos, yPos, zPos, 
						leftToolbar.getSquareEdgeLength());
				printOutMessage = printOutMessage + Constants.SQUARE_POLYGON;
			}
			else if(activePolygon.equals(Constants.STAR_POLYGON))	{
				glEntity = new GLStarEntity(xPos, yPos, zPos, 
						leftToolbar.getStarSpan());
				printOutMessage = printOutMessage + Constants.STAR_POLYGON;
			}
			else	{
				glEntity = new GLLightSourceEntity(xPos, yPos, zPos);
				printOutMessage = printOutMessage + Constants.LIGHT_SOURCE;
			}
			try	{
				glEntity.setAmbientRGBA(leftToolbar.ambientPanel.getRGBAValue());
				glEntity.setSpecularRGBA(leftToolbar.specualPanel.getRGBAValue());
				glEntity.setDiffuseRGBA(leftToolbar.diffusePanel.getRGBAValue());
			}
			catch(NumberFormatException nfe)	{
				System.out.println("Empty boxes in ambient, specular or diffuse. Using default values");
			}
			theObjectContainer.addGLEntity(glEntity);
			System.out.println(printOutMessage);
		}
		catch(NumberFormatException nfe)	{
			//TODO: make a error message in the leftToolbar panel
			System.out.println("Number format error");
		}
	}
	
}
