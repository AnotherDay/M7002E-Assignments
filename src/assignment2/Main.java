package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JPanel;
import javax.swing.UIManager;

import assignment2.actionListeners.CreatePolygonActionListener;
import assignment2.actionListeners.SwitchPolygonActionListener;
import assignment2.globals.Constants;
import assignment2.openGL.EventListener;
import assignment2.ui.Window;
import assignment2.ui.leftToolbar.LeftToolbar;

public class Main {

	private static LeftToolbar leftToolbar;
	
	public static void main(String[] args) {
		
		//Source: http://stackoverflow.com/a/2592258
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		GLCanvas canvas = new GLCanvas(caps);
		
		leftToolbar = new LeftToolbar();

		JPanel leftPanel = new JPanel();
		leftPanel.add(leftToolbar);
		leftPanel.setPreferredSize(new Dimension(200, 200));
		leftPanel.setBackground(Constants.backgroundColor);
		
		Window window = new Window("M7002E - Lab 2");

		leftToolbar.addPolygonPickerActionListener(new SwitchPolygonActionListener(leftToolbar, window));
		leftToolbar.addButtonActionListener(new CreatePolygonActionListener(leftToolbar, canvas));
		
		canvas.addGLEventListener(new EventListener());
		window.add(leftPanel, BorderLayout.WEST);
		window.add(canvas);
	}
}
