package assignment2.ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.util.Vector;

/**
 * Source http://docs.oracle.com/javase/tutorial/uiswing/misc/focus.html 
 * 
 * @author David Eriksson
 *
 */
public class CustomFocusTraversalPolicy extends FocusTraversalPolicy {

	private Vector<Component> order;
	
	public CustomFocusTraversalPolicy(Vector<Component> order)	{
		this.order = new Vector<Component>(order.size());
        this.order.addAll(order);
	}
	
	public void updateOrder(Vector<Component> order)	{
		this.order = order;
	}
	
	@Override
	public Component getComponentAfter(Container aContainer, Component aComponent) {
		int idx = order.indexOf(aComponent) + 1;
        return order.get(idx);
	}

	@Override
	public Component getComponentBefore(Container aContainer, Component aComponent) {
		int idx = order.indexOf(aComponent) - 1;
        if (idx < 0) {
            idx = order.size() - 1;
        }
        return order.get(idx);
	}

	@Override
	public Component getFirstComponent(Container aContainer) {
		return order.get(0);
	}

	@Override
	public Component getLastComponent(Container aContainer) {
		return order.lastElement();
	}

	@Override
	public Component getDefaultComponent(Container aContainer) {
		return order.get(0);
	}

}
