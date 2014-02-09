package assignment2.listeners.actionListeners.leftToolbar;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import assignment2.globals.Constants;
import assignment2.ui.CustomFocusTraversalPolicy;
import assignment2.ui.leftToolbar.LeftToolbar;

public class SwitchObjectActionListener implements ActionListener {

	private LeftToolbar leftToolbar;
	private Frame window;
	private CustomFocusTraversalPolicy traversalPolicy;
	
	public SwitchObjectActionListener(LeftToolbar leftToolbar, Frame window)	{
		this.window = window;
		this.leftToolbar = leftToolbar;
		traversalPolicy = new CustomFocusTraversalPolicy(leftToolbar.getFocusTraversalOrder());
		window.setFocusTraversalPolicy(traversalPolicy);
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
		else if(polygonName.equals(Constants.LIGHT_SOURCE))	{
			leftToolbar.changeToLightSpecificationPanel();
		}
		else if(polygonName.equals(Constants.SPHERE_POLYGON))	{
			leftToolbar.changeToSphereSpecificationPanel();
		}
		else	{
			leftToolbar.changeToUnspecifiedPolygonLable();
		}
		traversalPolicy.updateOrder(leftToolbar.getFocusTraversalOrder());
		window.setFocusTraversalPolicy(traversalPolicy);
	}

}
