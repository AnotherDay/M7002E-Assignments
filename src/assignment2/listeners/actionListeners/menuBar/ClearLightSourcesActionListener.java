package assignment2.listeners.actionListeners.menuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.globals.ObjectContainer;
import assignment2.openGL.EventListener;

public class ClearLightSourcesActionListener implements ActionListener {

	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	private EventListener eventListener;
	
	public ClearLightSourcesActionListener(EventListener eventListener)	{
		this.eventListener = eventListener;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		eventListener.removeAllLightSources();
	}

}
