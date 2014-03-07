package assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import assignment4.exceptions.NoObjectFoundException;
import assignment4.objects.MagicWand;

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
	private BitmapText crossHairsText, jediModeText; 
	private LinkedList<BitmapText> jediModeInstructions = new LinkedList<BitmapText>(); 
	private Picture handPicClosed, handPicOpen, jediModeEffect;
	private int windowWidth, windowHeight;
	private AssetManager assetManager;
	private ArrayList<Geometry> attachedGemetries = new ArrayList<Geometry>();
	private boolean pickingAllowed = true;
	
	private String enterJediModeTextString = "Press G to enter jedi mode"; 
	private String exitJediModeTextString = "Press G to exit jedi mode";
	
	public GuiManager(Node guiNode, int windowWidth, int windowHeight, AssetManager assetManager)	{
		this.guiNode = guiNode;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.assetManager = assetManager;
		
		guiNode.detachAllChildren();
		
		initJediModeTextAndEffects();
		
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
		handPicClosed.setWidth(20f);
		handPicClosed.setHeight(20f);
		handPicClosed.setLocalTranslation(
				(windowWidth / 2) + 5,
				(windowHeight / 2.2f), 
				0);

		handPicOpen = new Picture("Open Hand Picture");
		handPicOpen.setImage(assetManager, "Other/cursor_pick_hand.png", true);
		handPicOpen.setWidth(20f);
		handPicOpen.setHeight(20f);
		handPicOpen.setLocalTranslation(
				(windowWidth / 2) + 5,
				(windowHeight / 2) + 2, 
				0);		
		
		DirectionalLight guiLight = new DirectionalLight();
		guiLight.setDirection(new Vector3f(0, 0, -1.0f));
		guiLight.setColor(Constants.TORCH_LIGHT_COLOR);
		guiNode.addLight(guiLight);
		
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
	
	public void leaveJediMode()	{
		jediModeText.setText(enterJediModeTextString);
		guiNode.attachChild(jediModeText);
		for(BitmapText instructions : jediModeInstructions)	{
			guiNode.detachChild(instructions);
		}
		guiNode.detachChild(jediModeEffect);
	}
	
	public void enterJediMode()	{
		jediModeText.setText(exitJediModeTextString);
		guiNode.attachChild(jediModeText);
		for(BitmapText instructions : jediModeInstructions)	{
			guiNode.attachChild(instructions);
		}
		guiNode.attachChild(jediModeEffect);
	}
	
	public void removeJediModeText()	{
		guiNode.detachChild(jediModeText);
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
	
	public void updateActionIndicators(Player player, Node pickablesNode, ArrayList<MagicWand> wandList)	{
		detachPickIcon();
		detachGrabIcon();
		if(pickingAllowed)
			try {
				Geometry closestGeometry = new ObjectPicker(player).pickClosestGeometry(pickablesNode);
				if(closestGeometry.getLocalTranslation().distance(player.getLocation()) <= Constants.PICKING_DISTANCE)	{
					attachGrabIcon();
					for(MagicWand wand : wandList)	{
						if(wand.getGeometry().equals(closestGeometry))	{
							attachPickIcon();
							break;
						}
					}
				}
			} catch (NoObjectFoundException e) {}
	}
	
	private void initJediModeTextAndEffects()	{
		BitmapFont jediModeFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		jediModeText = new BitmapText(jediModeFont, false);
		jediModeText.setLocalTranslation(jediModeFont.getCharSet().getRenderedSize(), 
				windowHeight, 
				0);
		
		Vector3f textHeight = Vector3f.UNIT_Y.mult(jediModeFont.getCharSet().getLineHeight());
		
		BitmapText jediInstructionsUp = new BitmapText(jediModeFont, false);
		jediInstructionsUp.setText("U : Push up");
		
		BitmapText jediInstructionsDown = new BitmapText(jediModeFont, false);
		jediInstructionsDown.setText("O : Push down");
		
		BitmapText jediInstructionsForward = new BitmapText(jediModeFont, false);
		jediInstructionsForward.setText("I : Push forward");
		
		BitmapText jediInstructionsBackward = new BitmapText(jediModeFont, false);
		jediInstructionsBackward.setText("K : Push backward");
		
		BitmapText jediInstructionsLeft = new BitmapText(jediModeFont, false);
		jediInstructionsLeft.setText("J : Push left");
		
		BitmapText jediInstructionsRight = new BitmapText(jediModeFont, false);
		jediInstructionsRight.setText("L : Push object right");
		
		jediModeInstructions.addAll(Arrays.asList(
				jediInstructionsUp, jediInstructionsDown, jediInstructionsForward, jediInstructionsBackward,
				jediInstructionsLeft, jediInstructionsRight));
		
		Vector3f lastLocalTranslation = jediModeText.getLocalTranslation();
		for(BitmapText instructions : jediModeInstructions)		{
			instructions.setLocalTranslation(lastLocalTranslation.subtract(textHeight));
			lastLocalTranslation = instructions.getLocalTranslation();
		}
		
		jediModeEffect = new Picture("God Mode Effect");
		jediModeEffect.setImage(assetManager, "Other/god_mode_effect.png", true);
		jediModeEffect.setWidth(windowWidth);
		jediModeEffect.setHeight(windowHeight);
	}
}
