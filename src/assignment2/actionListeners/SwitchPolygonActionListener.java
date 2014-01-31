package assignment2.actionListeners;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.globals.Constants;
import assignment2.ui.LeftToolbar;

public class SwitchPolygonActionListener implements ActionListener {

	LeftToolbar leftToolbar;
	Frame window;
	
	public SwitchPolygonActionListener(LeftToolbar leftToolbar, Frame window)	{
		this.window = window;
		this.leftToolbar = leftToolbar;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String polygonName = leftToolbar.getActivePolygon();
		if(polygonName.equals(Constants.PYRAMID_POLYGON))	{
			leftToolbar.changeToPyramidSpecificationPanel();
		}
		else if(polygonName.equals(Constants.SQUARE_POLYGON))	{
			leftToolbar.changeToSquareSpecificationPanel();
		}
		else if(polygonName.equals(Constants.STAR_POLYGON))	{
			leftToolbar.changeToStarSpecificationPanel();
		}
		else	{
			leftToolbar.changeToUnspecifiedPolygonLable();
		}
	}

}
