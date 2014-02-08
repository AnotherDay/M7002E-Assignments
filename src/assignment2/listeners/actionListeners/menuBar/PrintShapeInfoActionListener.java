package assignment2.listeners.actionListeners.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.globals.ObjectContainer;
import assignment2.openGL.GLEntity;

public class PrintShapeInfoActionListener implements ActionListener {

	ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("-------------------Start---------------------");
		for(GLEntity glEntity : theObjectContainer.getGLEntityList())	{
			glEntity.printInfo();
		}
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("-------------------End-----------------------");
	}

}
