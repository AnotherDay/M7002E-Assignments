package assignment2.ui.menuBar;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	
	JMenu applicationMenu, canvasMenu;
	JMenuItem quit, clearCanvas;
	
	public MenuBar()	{
		applicationMenu = new JMenu("Application");
		this.add(applicationMenu);
		
		quit = new JMenuItem("Quit");
		applicationMenu.add(quit);
		
		canvasMenu = new JMenu("Canvas");
		this.add(canvasMenu);
		
		clearCanvas = new JMenuItem("Clear");
		canvasMenu.add(clearCanvas);
	}
	
	public void addQuitActionListener(ActionListener actionListener)	{
		quit.addActionListener(actionListener);
	}
	
	public void addClearCanvasActionListener(ActionListener actionListener)	{
		clearCanvas.addActionListener(actionListener);
	}
}
