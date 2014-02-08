package assignment2.ui.dialogBoxes;

import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class ResizeDialogBox extends JPanel {
	
	private Frame frame;
	private JTextField scaleFactorInput;
	
	public ResizeDialogBox(Frame frame)	{
		this.frame = frame;
		
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JTextPane scaleFactorInputLable = new JTextPane();
		scaleFactorInputLable.setText("Scaling Factor:");
		scaleFactorInput = new JTextField("1.0", 2);
		inputPanel.add(scaleFactorInputLable);
		inputPanel.add(scaleFactorInput);
		this.add(inputPanel);
	}
	
	public float getDialogValue() throws NumberFormatException	{
		JOptionPane.showMessageDialog(frame , this, "Object Scaling", JOptionPane.QUESTION_MESSAGE);
		return Float.valueOf(scaleFactorInput.getText());
	}
}
