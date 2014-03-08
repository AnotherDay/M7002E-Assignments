package assignment4.gui;

import java.util.ArrayList;

import assignment4.Constants;
import assignment4.ObjectPicker;
import assignment4.Player;
import assignment4.exceptions.NoObjectFoundException;
import assignment4.items.Item;
import assignment4.items.Key;
import assignment4.items.MagicWand;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.light.DirectionalLight;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

public class GuiManager {
	
	private Node guiNode;
	private BitmapText crossHairsText; 
	private Picture handPicClosed, handPicOpen;
	private int windowWidth, windowHeight;
	private JediModeGui jediModeGui;
	private ArrayList<Geometry> attachedGemetries = new ArrayList<Geometry>();
	private TextMessage keyMessage, wandMessage;
	private boolean pickingAllowed = true;
	
	public GuiManager(Node guiNode, int windowWidth, int windowHeight, AssetManager assetManager)	{
		this.guiNode = guiNode;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		guiNode.detachAllChildren();
		
		jediModeGui = new JediModeGui(windowWidth, windowHeight, assetManager);
		
		BitmapFont crossHairFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		crossHairsText = new BitmapText(crossHairFont, false);
		crossHairsText.setSize(crossHairFont.getCharSet().getRenderedSize() * 2);
		crossHairsText.setText("+");
		crossHairsText.setLocalTranslation( // center
				windowWidth / 2 - crossHairFont.getCharSet().getRenderedSize() / 3 * 2,
				windowHeight / 2 + crossHairsText.getLineHeight() / 2, 
				0);
		
		handPicClosed = new Picture("Closed Hand Picture");
		handPicClosed.setImage(assetManager, "Other/cursor_drag_hand.png", true);
		handPicClosed.scale(20f);
		handPicClosed.setLocalTranslation(
				(windowWidth / 2) - 30,
				(windowHeight / 2f + 10), 
				0);

		handPicOpen = new Picture("Open Hand Picture");
		handPicOpen.setImage(assetManager, "Other/cursor_pick_hand.png", true);
		handPicOpen.scale(18f);
		handPicOpen.setLocalTranslation(
				(windowWidth / 2) + 15,
				(windowHeight / 2.2f) - 5, 
				0);		
		
		DirectionalLight guiLight = new DirectionalLight();
		guiLight.setDirection(new Vector3f(0, 0, -1.0f));
		guiLight.setColor(Constants.TORCH_LIGHT_COLOR);
		guiNode.addLight(guiLight);

		keyMessage = new TextMessage("There is something strange with this key", assetManager);
		keyMessage.setLocalTranslation(
				windowWidth/2 - keyMessage.getLineWidth()/2, 
				windowHeight/1.5f,
				0);

		wandMessage = new TextMessage("This controls the torches, how convenient", assetManager);
		wandMessage.setLocalTranslation(
				windowWidth/2 - keyMessage.getLineWidth()/2, 
				windowHeight/1.5f,
				0);
	}
	
	public void attachGeometry(Geometry geometry)	{
		guiNode.attachChild(geometry);
	}
	
	public void detachGeometry(Geometry geometry)	{
		guiNode.detachChild(geometry);
	}
	
	public void detachChild(String name)	{
		guiNode.detachChildNamed(name);
	}
	
	public void attachJediGui()	{
		guiNode.attachChild(jediModeGui);
	}
	
	public void detachJediGui()	{
		guiNode.detachChild(jediModeGui);
	}
	
	public void leaveJediMode()	{
		jediModeGui.jediModeOff();
	}
	
	public void enterJediMode()	{
		jediModeGui.jediModeOn();
	}
	
	public void attachGrabIcon()	{
		guiNode.attachChild(handPicClosed);
	}
	
	public void detachGrabIcon()	{
		try	{
			guiNode.detachChild(handPicClosed);
		}
		catch(NullPointerException npe)	{
			//handPick not attach to guiNode, ignore
		}
	}
	
	public void attachPickIcon()	{
		guiNode.attachChild(handPicOpen);
	}
	
	public void detachPickIcon()	{
		try	{
			guiNode.detachChild(handPicOpen);
		}
		catch(NullPointerException npe)	{
			//handPick not attach to guiNode, ignore
		}
	}
	
	public void attachCrossHairs()	{
		guiNode.attachChild(crossHairsText);
	}
	
	public void removeCrossHairs()	{
		guiNode.detachChild(crossHairsText);
	}
	
	public void attachWand(Geometry wand)	{
		Geometry wandClone = wand.clone();
		guiNode.attachChild(wandClone);
		attachedGemetries.add(wandClone);
		
		Quaternion rotateX = new Quaternion(); 
		rotateX.fromAngleAxis(FastMath.PI/2, new Vector3f(1, 0, 0));  

		Quaternion rotateZ = new Quaternion(); 
		rotateZ.fromAngleAxis(FastMath.PI/12, new Vector3f(0, 1, 0));  

		wandClone.setLocalRotation(rotateX);
		wandClone.rotate(rotateZ);
		
		wandClone.setLocalScale(150);
		wandClone.setLocalTranslation(windowWidth/1.5f, windowHeight/5, 0);
	}
	
	public void detachAllItems()	{
		for(Geometry geometry : attachedGemetries)	{
			guiNode.detachChild(geometry);
		}
	}
	
	public void turnOffPickingInidcator()	{
		pickingAllowed = false;
	}
	
	public void turnOnPickingIndicator()	{
		pickingAllowed = true;
	}
	
	public void updateActionIndicators(Player player, Node pickablesNode, Item... items)	{
		detachItemMessages();
		detachPickIcon();
		detachGrabIcon();
		if(pickingAllowed)
			try {
				Geometry closestGeometry = new ObjectPicker(player).pickClosestGeometry(pickablesNode);
				if(closestGeometry.getLocalTranslation().distance(player.getLocation()) <= Constants.PICKING_DISTANCE)	{
					attachGrabIcon();
					for(Item item : items)	{
						if(item.getGeometry().equals(closestGeometry))	{
							attachPickIcon();
							if(item instanceof MagicWand)	{
								guiNode.attachChild(wandMessage);
							}
							else if(item instanceof Key)	{
								guiNode.attachChild(keyMessage);
							}
							break;
						}
					}
				}
			} catch (NoObjectFoundException e) {}
	}
	
	private void detachItemMessages()	{
		try	{
			guiNode.detachChild(wandMessage);
		} catch(NullPointerException npe) {}
		try	{
			guiNode.detachChild(keyMessage);
		} catch(NullPointerException npe) {}
	}
}
