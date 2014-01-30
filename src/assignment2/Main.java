package assignment2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;
import javax.swing.JPanel;
import javax.swing.UIManager;

import assignment2.globals.Constants;
import assignment2.ui.LeftToolbar;
import assignment2.ui.Toolbar;
import assignment2.ui.Window;

public class Main implements ActionListener {

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
		GLCanvas canvas = new GLCanvas(caps);
		
		Toolbar toolbar = new Toolbar();
		leftToolbar = new LeftToolbar();
		
		JPanel leftPanel = new JPanel();
		leftPanel.add(leftToolbar);
		leftPanel.setPreferredSize(new Dimension(200, 200));
		leftPanel.setBackground(Constants.backgroundColor);
		
		Window window = new Window("M7002E - Lab 2");
		window.add(leftPanel, BorderLayout.WEST);
		window.add(canvas);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println();
	}
}
