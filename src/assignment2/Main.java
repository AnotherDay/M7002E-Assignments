package assignment2;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

public class Main {

	private static int WINDOW_WIDTH = 600;
	private static int WINDOW_HEIGHT = 400;
	
	public static void main(String[] args) {
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		GLCanvas canvas = new GLCanvas(caps);
		
		Frame frame = new Frame("M7002E - Lab 2");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.add(canvas);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	new Thread()	{
					@Override
					public void run() {
						System.exit(0);
					}
            	}.start();
            }
        });
		
	}
}
