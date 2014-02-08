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
import assignment2.openGL.shapes.GLPyramidEntity;
import assignment2.openGL.shapes.GLSphereEntity;
import assignment2.openGL.shapes.GLSquareEntity;
import assignment2.ui.Window;
import assignment2.ui.leftToolbar.LeftToolbar;
import assignment2.ui.menuBar.MenuBar;

import com.jogamp.opengl.util.Animator;

public class Main {

	public static void main(String[] args) {
		ObjectContainer theObjectContainer = ObjectContainer.getInstance();
//		theObjectContainer.addGLEntity(new GLSquareEntity(0.5f, 0.5f, -1.0f, 0.5f));
//		theObjectContainer.addGLEntity(new GLPyramidEntity(0.5f, 0.5f, 0.5f, 0.5f, 0.5f));
		GLSphereEntity sphere = new GLSphereEntity(0.5f, 0.5f, -0.5f, 0.2f);
		theObjectContainer.addGLEntity(sphere);
		theObjectContainer.addLightSource(new GLLightSourceEntity(1, 1, 1));
		
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
		menuBar.addClearCanvasActionListener(new ClearCanvasActionListener());
		menuBar.addPrintAllShapeActionListener(new PrintShapeInfoActionListener());

		JPanel leftPanel = new JPanel();
		LeftToolbar leftToolbar = new LeftToolbar();
		leftPanel.add(leftToolbar);
		leftPanel.setPreferredSize(new Dimension(300, 500));
		leftPanel.setBackground(Constants.backgroundColor);
		
		Animator animator = new Animator(canvas);
		Window window = new Window("M7002E - Lab 2", animator);

		leftToolbar.addPolygonPickerActionListener(new SwitchObjectActionListener(leftToolbar, window));
		leftToolbar.addButtonActionListener(new CreateActionListener(leftToolbar, canvas));
		
		EventListener eventListener = new EventListener();
		canvas.addGLEventListener(eventListener);
		menuBar.addClearLightSourcesActionListener(new ClearLightSourcesActionListener(eventListener));
		
		window.add(leftPanel, BorderLayout.WEST);
		window.add(menuBar, BorderLayout.NORTH);
		window.add(canvas);
		window.pack();
		
		CanvasMouseListener canvasMouseListener = new CanvasMouseListener(canvas.getHeight(), canvas.getWidth(), eventListener, canvas, window); 
		canvas.addMouseListener(canvasMouseListener);
		menuBar.addMovePolygonActionListener(new MoveGLEntityActionListener(canvasMouseListener));
		menuBar.addDeleteShapeActionListener(new DeleteShapeActionListener(canvasMouseListener));
		menuBar.addResizeObjectActionListener(new ResizeObjectActionListener(canvasMouseListener));
		animator.start();
	}
}
