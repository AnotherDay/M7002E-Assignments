package assignment2.ui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.jogamp.opengl.util.Animator;

@SuppressWarnings("serial")
public class Window extends Frame {
	
	public Window(String title, final Animator animator)	{
		super(title);
		
		final Window windowForInnerClass = this;
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	new Thread()	{
					@Override
					public void run() {
						animator.stop();
						windowForInnerClass.dispose();
						System.exit(0);
					}
            	}.start();
            }
        });
		this.setVisible(true);
	}
}
