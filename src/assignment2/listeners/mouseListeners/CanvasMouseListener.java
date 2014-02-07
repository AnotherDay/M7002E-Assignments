package assignment2.listeners.mouseListeners;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.media.opengl.awt.GLCanvas;

import assignment2.openGL.EventListener;

public class CanvasMouseListener implements MouseListener, MouseMotionListener {

	private int panelHeight, panelWidth;
	private float widthToHeightRatio;
	private static final int SELECT_SHAPE = 0, SELECT_SHAPE_TO_MOVE = 1, SELECT_NEW_POSITION= 2, DELETE_SHAPE = 3;
	private int state = SELECT_SHAPE;
	private EventListener eventListener;
	private GLCanvas panel;
	
	
	public CanvasMouseListener(int panelHeight, int panelWidth, EventListener eventListener, GLCanvas canvas)	{
		this.panelHeight = panelHeight;
		this.panelWidth = panelWidth;
		this.eventListener = eventListener;
		canvas.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		this.panel = canvas;
		this.widthToHeightRatio = (float)panelWidth/(float)panelHeight;
	}
	
	public void changeToMoveState()		{
		panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		state = SELECT_SHAPE_TO_MOVE;
	}
	
	public void changeToSelectState()	{
		panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		state = SELECT_SHAPE;
	}
	
	public void changeToDeleteState()	{
		panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		state = DELETE_SHAPE;
	}
	
	private void changeToSelectNewPositionState()	{
		state = SELECT_NEW_POSITION;
		panel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
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
		switch(state)	{
			case SELECT_SHAPE: 
				eventListener.registerMouseClick(e.getX(), e.getY());
				break;
			case SELECT_SHAPE_TO_MOVE:
				eventListener.registerMouseClick(e.getX(), e.getY());
				changeToSelectNewPositionState();
				break;
			case SELECT_NEW_POSITION:
				float newX = widthToHeightRatio*(e.getX()/(float)panelWidth);
				float newY = ((float)panelHeight - e.getY())/(float)panelHeight;
				eventListener.changeSelectedObjectPosition(newX, newY);
				changeToSelectState();
				break;
			case DELETE_SHAPE:
				eventListener.markObjectForDeletion(e.getX(), e.getY());
				changeToSelectState();
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
