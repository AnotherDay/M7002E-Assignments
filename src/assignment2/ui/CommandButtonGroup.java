package assignment2.ui;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

@SuppressWarnings("serial")
public class CommandButtonGroup extends ButtonGroup  {

	@Override
	public void setSelected(ButtonModel m, boolean b)	{
		if(b)	{
			super.setSelected(m, b);
		}
		else	{
			clearSelection();
		}
	}
}