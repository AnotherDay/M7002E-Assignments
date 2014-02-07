package assignment2.listeners.actionListeners.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.globals.ObjectContainer;
import assignment2.openGL.shapes.GLEntity;

public class PrintShapeInfoActionListener implements ActionListener {

	ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(GLEntity glEntity : theObjectContainer.getGLEntityList())	{
			glEntity.printInfo();
		}
	}

}
