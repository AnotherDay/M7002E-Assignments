package assignment4.actionListeners;

import java.util.ArrayList;

import assignment4.Constants;
import assignment4.LightController;
import assignment4.ObjectPicker;
import assignment4.Player;
import assignment4.exceptions.NoObjectFoundException;
import assignment4.gui.GuiManager;
import assignment4.items.Item;
import assignment4.items.Key;
import assignment4.items.MagicWand;
import assignment4.objects.Torch;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

public class InventoryListener implements ActionListener {

	private float pickingDistance = 10f;
	
	private Node itemsNode;
	private Geometry itemGeometry;
	private RigidBodyControl itemController;
	private LightController lightController;
	private GuiManager guiManager;
	private ObjectPicker objectPicker;
	private MoveObjectListener moveObjectListener;
	private Player player;
	private boolean idleState;
	private ArrayList<Item> itemsList = new ArrayList<Item>();
	
	public InventoryListener(Node itemsNode, LightController lightController, Player player, MoveObjectListener moveObjectListener, GuiManager guiManager)	{
		this.itemsNode = itemsNode;
		this.player = player;
		this.lightController = lightController;
		this.moveObjectListener = moveObjectListener;
		this.guiManager = guiManager;
		
		this.objectPicker = new ObjectPicker(player);
	}
	
	public void addItems(Item... items)	{
		for(Item item: items)	{
			itemsList.add(item);
		}
	}
	
	public void removeItems(Item... items)	{
		for(Item item: items)	{
			itemsList.remove(item);
		}
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		if(name.equals(Constants.PUT_IN_INVENTORY) && !isPressed && !idleState)	{
			if(itemGeometry == null)	{
				pickUpItem();
			}
			else	{
				removeItem();
			}
		}
		else if(name.equals(Constants.PICK) && !isPressed && !idleState)	{
			if(itemGeometry != null)	{
				try {
					Torch pickedTorch = (Torch) objectPicker.pickClosestGeometry(lightController.getTorchesNode()).getParent().getParent();
					lightController.turnTorchLightSwitch(pickedTorch);
				} catch (NoObjectFoundException e) {}
			}
		}
	}
	
	public void turnOff()	{
		idleState = true;
	}
	
	public void turnOn()	{
		idleState = false;
	}
	
	private void pickUpItem()	{
		try	{
			itemGeometry = objectPicker.pickClosestGeometry(itemsNode);
			for(Item item : itemsList)	{
				if(item.getGeometry().equals(itemGeometry) &&
						player.getLocation().distance(itemGeometry.getLocalTranslation()) <= pickingDistance)	{
					if(item instanceof MagicWand)	{
						itemController = itemGeometry.getControl(RigidBodyControl.class);
						itemGeometry.removeControl(RigidBodyControl.class);
						itemsNode.detachChild(itemGeometry);
						guiManager.attachWand(itemGeometry);
						guiManager.turnOffPickingInidcator();
						break;
					}
					else if(item instanceof Key)	{
						itemsNode.detachChild(itemGeometry);
						moveObjectListener.enableJediMode();
						guiManager.attachJediGui();
					}
					
				}
			}
		} catch(NoObjectFoundException e)	{}
	}
	
	private void removeItem()	{
		if(itemGeometry != null)	{
			guiManager.detachAllItems();
			guiManager.turnOnPickingIndicator();
			itemGeometry.addControl(itemController);
			Vector3f newItemLocation = player.getCameraLocation().add(player.getCameraDirection().mult(2.0f));
			itemController.setPhysicsLocation(newItemLocation);
			itemController.activate();
			itemsNode.attachChild(itemGeometry);
			moveObjectListener.turnOn();
			itemGeometry = null;
		}
	}
}
