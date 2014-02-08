package assignment2.listeners.actionListeners.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.listeners.mouseListeners.CanvasMouseListener;

public class ResizeObjectActionListener implements ActionListener {

	private CanvasMouseListener canvasMouseListener;
	
	public ResizeObjectActionListener(CanvasMouseListener canvasMouseListener)	{
		this.canvasMouseListener = canvasMouseListener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		canvasMouseListener.changeToResizeObjectState();
	}

}
