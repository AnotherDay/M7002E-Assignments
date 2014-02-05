package assignment2.actionListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import assignment2.openGL.EventListener;

public class CanvasMouseListener implements MouseListener {

	private float panelHeight, panelWidth, openGLWindowHeight, openGLWindowWidth;
	private EventListener eventListener;
	
	public CanvasMouseListener(int panelHeight, int panelWidth, EventListener eventListener)	{
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		this.eventListener = eventListener;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		//THE FOLLOWING CONVERSION FROM LECTURE 2 SLIDE 57
//		FLOAT YINV = PANELHEIGHT - E.GETY();								//1. INVERT Y TO SWITCH ORIGIN TO BOTTOM LEFT
//		FLOAT YUNIT = YINV/PANELHEIGHT; FLOAT XUNIT = E.GETX()/PANELWIDTH; //2. RESCALE TO UNIT BOX
//		//3. RESCALE TO OPENGL WIDTH & HEIGHT
//		//4. OFFSET TO OPENGL ORIGIN
//		System.out.println("X-coordinate = " + xUnit + ", Y-Coordinate = " + yUnit);
		eventListener.registerMouseClick(e.getX(), e.getY(), (int)panelHeight, (int)panelWidth);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
