package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JPanel;
import javax.swing.UIManager;

import assignment2.actionListeners.CanvasMouseListener;
import assignment2.actionListeners.ClearCanvasActionListener;
import assignment2.actionListeners.CloseProgramActionListener;
import assignment2.actionListeners.CreatePolygonActionListener;
import assignment2.actionListeners.SwitchPolygonActionListener;
import assignment2.globals.Constants;
import assignment2.globals.ObjectContainer;
import assignment2.openGL.EventListener;
import assignment2.shapes.Square;
import assignment2.ui.Window;
import assignment2.ui.leftToolbar.LeftToolbar;
import assignment2.ui.menuBar.MenuBar;

public class Main {

	public static void main(String[] args) {
		ObjectContainer theObjectContainer = ObjectContainer.getInstance();
		theObjectContainer.addSquare(new Square(0, 0, -3, 1));
		
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
		canvas.setPreferredSize(new Dimension(1000, 500));
		
		MenuBar menuBar = new MenuBar();
		menuBar.addQuitActionListener(new CloseProgramActionListener());
		menuBar.addClearCanvasActionListener(new ClearCanvasActionListener(canvas));

		JPanel leftPanel = new JPanel();
		LeftToolbar leftToolbar = new LeftToolbar();
		leftPanel.add(leftToolbar);
		leftPanel.setPreferredSize(new Dimension(200, 500));
		leftPanel.setBackground(Constants.backgroundColor);
		
		Window window = new Window("M7002E - Lab 2");

		leftToolbar.addPolygonPickerActionListener(new SwitchPolygonActionListener(leftToolbar, window));
		leftToolbar.addButtonActionListener(new CreatePolygonActionListener(leftToolbar, canvas));
		
		canvas.addGLEventListener(new EventListener());
		window.add(leftPanel, BorderLayout.WEST);
		window.add(menuBar, BorderLayout.NORTH);
		window.add(canvas);
		window.pack();
		canvas.addMouseListener(new CanvasMouseListener(canvas.getHeight(), canvas.getWidth()));
	}
}
