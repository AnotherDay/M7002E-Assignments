package assignment2.listeners.actionListeners.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.awt.GLCanvas;

import assignment2.globals.ObjectContainer;

public class ClearCanvasActionListener implements ActionListener {

	private GLCanvas canvas;
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	public ClearCanvasActionListener(GLCanvas canvas)	{
		this.canvas = canvas;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		theObjectContainer.clearObjectContainer();
		canvas.display();
	}

}
