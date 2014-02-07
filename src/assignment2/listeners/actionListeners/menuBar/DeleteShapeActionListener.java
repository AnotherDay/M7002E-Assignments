package assignment2.listeners.actionListeners.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.listeners.mouseListeners.CanvasMouseListener;

public class DeleteShapeActionListener implements ActionListener {

	private CanvasMouseListener canvasMouseListener;
	
	public DeleteShapeActionListener(CanvasMouseListener canvasMouseListener)	{
		this.canvasMouseListener = canvasMouseListener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		canvasMouseListener.changeToDeleteState();
	}

}
