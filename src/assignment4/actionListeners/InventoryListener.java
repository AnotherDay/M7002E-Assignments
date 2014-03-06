package assignment4.actionListeners;

import java.util.ArrayList;

import assignment4.Constants;
import assignment4.GuiManager;
import assignment4.LightController;
import assignment4.ObjectPicker;
import assignment4.Player;
import assignment4.exceptions.NoObjectFoundException;
import assignment4.objects.MagicWand;
import assignment4.objects.Torch;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

public class InventoryListener implements ActionListener {

	private float pickingDistance = 10f;
	
	private Node itemsNode;
	private Geometry inventoryItem;
	private RigidBodyControl itemController;
	private LightController lightController;
	private GuiManager guiManager;
	private ObjectPicker objectPicker;
	private MoveObjectListener moveObjectListener;
	private Player player;
	private ArrayList<MagicWand> wandList = new ArrayList<MagicWand>();
	private boolean idleState;
	
	public InventoryListener(Node itemsNode, LightController lightController, Player player, MoveObjectListener moveObjectListener, GuiManager guiManager)	{
		this.itemsNode = itemsNode;
		this.player = player;
		this.lightController = lightController;
		this.moveObjectListener = moveObjectListener;
		this.guiManager = guiManager;
		
		this.objectPicker = new ObjectPicker(player);
	}
	
	public void addWands(MagicWand... wands)	{
		for(MagicWand wand : wands)	{
			wandList.add(wand);
		}
	}
	
	public void removeWand(MagicWand... wands)		{
		for(MagicWand wand : wands)	{
			wandList.remove(wand);
		}
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		if(name.equals(Constants.PUT_IN_INVENTORY) && !isPressed && !idleState)	{
			if(inventoryItem == null)	{
				pickUpItem();
			}
			else	{
				removeItem();
			}
		}
		else if(name.equals(Constants.PICK) && !isPressed && !idleState)	{
			if(inventoryItem != null)	{
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
			inventoryItem = objectPicker.pickClosestGeometry(itemsNode);
			for(MagicWand wand : wandList)	{
				if(wand.getGeometry().equals(inventoryItem) && 
						player.getLocation().distance(inventoryItem.getLocalTranslation()) <= pickingDistance)	{
					itemController = inventoryItem.getControl(RigidBodyControl.class);
					inventoryItem.removeControl(RigidBodyControl.class);
					itemsNode.detachChild(inventoryItem);
					moveObjectListener.turnOff();
					guiManager.attachWand(inventoryItem);
					break;
				}
				else {
					inventoryItem = null;
				}
			}
		} catch(NoObjectFoundException e)	{}
	}
	
	private void removeItem()	{
		if(inventoryItem != null)	{
			guiManager.detachAllItems();
			inventoryItem.addControl(itemController);
			Vector3f newItemLocation = player.getCameraLocation().add(player.getCameraDirection().mult(2.0f));
			itemController.setPhysicsLocation(newItemLocation);
			itemController.activate();
			itemsNode.attachChild(inventoryItem);
			moveObjectListener.turnOn();
			inventoryItem = null;
		}
	}
}
