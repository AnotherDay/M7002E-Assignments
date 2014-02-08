package assignment2.ui.menuBar;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jogamp.opengl.glu.mipmap.ScaleInternal;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	private JMenu applicationMenu, canvasMenu, polygonMenu;
	
	//Application Menu
	private JMenuItem quit;
	//Polygon Menu
	private JMenuItem movePolygon, deleteObject, resizeObject;
	//Canvas Menu
	private JMenuItem clearCanvas, clearLightSources, printAllShapeInfo; 

	private int menuMarginSides = 5, menuMarginTop = 7, menuMarginBottom = 7;
	private Insets menuInsets = new Insets(menuMarginTop, menuMarginSides, menuMarginBottom, menuMarginTop);
	
	public MenuBar()	{
		createApplicationMenu();
		createPolygonMenu();
		createCanvasMenu();
	}
	
	private void createApplicationMenu()	{
		applicationMenu = new JMenu("Application");
		applicationMenu.setMargin(new Insets(menuMarginTop, 2, menuMarginBottom, menuMarginSides));
		this.add(applicationMenu);
			quit = new JMenuItem("Quit");
			applicationMenu.add(quit);
	}
	
	private void createPolygonMenu()	{
		polygonMenu = new JMenu("Polygon");
		polygonMenu.setMargin(menuInsets);
		this.add(polygonMenu);
			movePolygon = new JMenuItem("Move");
			polygonMenu.add(movePolygon);
			deleteObject = new JMenuItem("Delete");
			polygonMenu.add(deleteObject);
			resizeObject = new JMenuItem("Rezise");
			polygonMenu.add(resizeObject);
	}
	
	private void createCanvasMenu()	{
		canvasMenu = new JMenu("Canvas");
		canvasMenu.setMargin(menuInsets);
		this.add(canvasMenu);
			clearCanvas = new JMenuItem("Clear Objects");
			canvasMenu.add(clearCanvas);
			clearLightSources = new JMenuItem("Clear Light Sources");
			canvasMenu.add(clearLightSources);
			printAllShapeInfo = new JMenuItem("Print All Shapes");
			canvasMenu.add(printAllShapeInfo);
	}
	
	public void addQuitActionListener(ActionListener actionListener)	{
		quit.addActionListener(actionListener);
	}
	
	public void addMovePolygonActionListener(ActionListener actionListener)	{
		movePolygon.addActionListener(actionListener);
	}
	
	public void addClearCanvasActionListener(ActionListener actionListener)	{
		clearCanvas.addActionListener(actionListener);
	}
	
	public void addPrintAllShapeActionListener(ActionListener actionListener)	{
		printAllShapeInfo.addActionListener(actionListener);
	}
	
	public void addDeleteShapeActionListener(ActionListener actionListener)		{
		deleteObject.addActionListener(actionListener);
	}
	
	public void addClearLightSourcesActionListener(ActionListener actionListener)	{
		clearLightSources.addActionListener(actionListener);
	}
	
	public void addResizeObjectActionListener(ActionListener actionListener)	{
		resizeObject.addActionListener(actionListener);
	}
}
