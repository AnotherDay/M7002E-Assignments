package assignment2.actionListeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import assignment2.openGL.EventListener;

public class CanvasMouseListener implements MouseListener {

	private int panelHeight, panelWidth;
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
		eventListener.registerMouseClick(e.getX(), e.getY(), panelHeight, panelWidth);
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
