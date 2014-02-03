package assignment2.ui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import assignment2.globals.Constants;

@SuppressWarnings("serial")
public class Window extends Frame {
	
	public Window(String title)	{
		super(title);
		
		this.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		final Window windowForInnerClass = this;
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	new Thread()	{
					@Override
					public void run() {
						windowForInnerClass.dispose();
						System.exit(0);
					}
            	}.start();
            }
        });
		this.setVisible(true);
	}
}
