package assignment2.actionListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CanvasMouseListener implements MouseListener {

	private float panelHeight, panelWidth, openGLWindowHeight, openGLWindowWidth;
	
	public CanvasMouseListener(int panelHeight, int panelWidth)	{
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
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
		//The following conversion from Lecture 2 slide 57
		float yInv = panelHeight - e.getY();								//1. Invert y to switch origin to bottom left
		float yUnit = yInv/panelHeight; float xUnit = e.getX()/panelWidth; //2. Rescale to unit box
		//3. Rescale to OpenGL width & height
		//4. Offset to OpenGL origin
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
