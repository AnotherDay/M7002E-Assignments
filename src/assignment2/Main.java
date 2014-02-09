package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JPanel;
import javax.swing.UIManager;

import assignment2.globals.Constants;
import assignment2.globals.ObjectContainer;
import assignment2.listeners.actionListeners.leftToolbar.CreateActionListener;
import assignment2.listeners.actionListeners.leftToolbar.SwitchObjectActionListener;
import assignment2.listeners.actionListeners.menuBar.ClearCanvasActionListener;
import assignment2.listeners.actionListeners.menuBar.ClearLightSourcesActionListener;
import assignment2.listeners.actionListeners.menuBar.CloseProgramActionListener;
import assignment2.listeners.actionListeners.menuBar.DeleteShapeActionListener;
import assignment2.listeners.actionListeners.menuBar.MoveGLEntityActionListener;
import assignment2.listeners.actionListeners.menuBar.PrintShapeInfoActionListener;
import assignment2.listeners.actionListeners.menuBar.ResizeObjectActionListener;
import assignment2.listeners.mouseListeners.CanvasMouseListener;
import assignment2.openGL.EventListener;
import assignment2.openGL.GLLightSourceEntity;
import assignment2.openGL.shapes.GLSphereEntity;
import assignment2.ui.Window;
import assignment2.ui.leftToolbar.LeftToolbar;
import assignment2.ui.menuBar.MenuBar;

import com.jogamp.opengl.util.Animator;

public class Main {

	private static GLCanvas canvas;
	private static EventListener eventListener = new EventListener();
	
	private static MenuBar menuBar;
	private static JPanel leftPanel;
	private static LeftToolbar leftToolbar;
	private static Window window;
	
	private static CanvasMouseListener canvasMouseListener;
	
	public static void main(String[] args) {
		
		ObjectContainer theObjectContainer = ObjectContainer.getInstance();
		theObjectContainer.addLightSource(new GLLightSourceEntity(1, 1, 1));
		theObjectContainer.addGLEntity(new GLSphereEntity(0.5f, 0.5f, 0.5f, 0.2f));
		
		//Source: http://stackoverflow.com/a/2592258
		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		GLProfile glp = GLProfile.getDefault();
		GLCapabilities caps = new GLCapabilities(glp);
		caps.setDoubleBuffered(true);
		canvas = new GLCanvas(caps);
		canvas.setPreferredSize(new Dimension(1000, 500));
		canvas.addGLEventListener(eventListener);

		leftToolbar = new LeftToolbar();
		leftPanel = new JPanel();
		leftPanel.add(leftToolbar);
		leftPanel.setPreferredSize(new Dimension(300, 500));
		leftPanel.setBackground(Constants.backgroundColor);
		
		menuBar = new MenuBar();
		
		Animator animator = new Animator(canvas);
		window = new Window("M7002E - Lab 2", animator);
		window.add(leftPanel, BorderLayout.WEST);
		window.add(menuBar, BorderLayout.NORTH);
		window.add(canvas);
		window.pack();
		
		canvasMouseListener = new CanvasMouseListener(canvas.getHeight(), canvas.getWidth(), eventListener, canvas, window); 
		canvas.addMouseListener(canvasMouseListener);
		
		addMenuBarActionListeners();
		addLeftToolbarActionListeners();
		animator.start();
	}
	
	private static void addMenuBarActionListeners()	{
		menuBar.addQuitActionListener(new CloseProgramActionListener());
		menuBar.addClearCanvasActionListener(new ClearCanvasActionListener());
		menuBar.addPrintAllShapeActionListener(new PrintShapeInfoActionListener());
		menuBar.addClearLightSourcesActionListener(new ClearLightSourcesActionListener(eventListener));
		menuBar.addMovePolygonActionListener(new MoveGLEntityActionListener(canvasMouseListener));
		menuBar.addDeleteShapeActionListener(new DeleteShapeActionListener(canvasMouseListener));
		menuBar.addResizeObjectActionListener(new ResizeObjectActionListener(canvasMouseListener));
	}
	
	private static void addLeftToolbarActionListeners()	{
		leftToolbar.addPolygonPickerActionListener(new SwitchObjectActionListener(leftToolbar, window));
		leftToolbar.addButtonActionListener(new CreateActionListener(leftToolbar, canvas));
	}
}
