package assignment2.actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseProgramActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

}
