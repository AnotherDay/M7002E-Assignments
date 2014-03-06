package assignment4;

import java.util.ArrayList;

import assignment4.objects.Crate;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

public class GuiManager {
	
	private Node guiNode;
	private BitmapText crossHairsText, godModeText;
	private Picture handPic, godModeEffect;
	private int windowWidth, windowHeight;
	private ArrayList<Geometry> attachedGemetries = new ArrayList<Geometry>();
	
	private String enterJediModeTextString = "Press G to enter jedi mode"; 
	private String exitJediModeTextString = "Press G to exit jedi mode";
	
	public GuiManager(Node guiNode, int windowWidth, int windowHeight)	{
		this.guiNode = guiNode;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		
		guiNode.detachAllChildren();
		BitmapFont godModeFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		godModeText = new BitmapText(godModeFont, false);
		godModeText.setSize(godModeFont.getCharSet().getRenderedSize() * 1.5f);
		godModeText.setLocalTranslation(
				windowWidth - godModeFont.getCharSet().getRenderedSize()*18, 
				windowHeight, 
				0);
		
		
		BitmapFont crossHairFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		crossHairsText = new BitmapText(crossHairFont, false);
		crossHairsText.setSize(crossHairFont.getCharSet().getRenderedSize() * 2);
		crossHairsText.setText("+");
		crossHairsText.setLocalTranslation( // center
				windowWidth / 2 - crossHairFont.getCharSet().getRenderedSize() / 3 * 2,
				windowHeight / 2 + crossHairsText.getLineHeight() / 2, 
				0);
		
		handPic = new Picture("Hand Picture");
		handPic.setImage(assetManager, "Other/cursor_drag_hand.png", true);
		handPic.setWidth(20f);
		handPic.setHeight(20f);
		handPic.setLocalTranslation(
				(windowWidth / 2) + 2,
				(windowHeight / 2) + 2, 
				0);
		
		godModeEffect = new Picture("God Mode Effect");
		godModeEffect.setImage(assetManager, "Other/god_mode_effect.png", true);
		godModeEffect.setWidth(windowWidth);
		godModeEffect.setHeight(windowHeight);
		
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
	
	public void attachEnterJediModeText()	{
		godModeText.setText(enterJediModeTextString);
		guiNode.attachChild(godModeText);
		guiNode.detachChild(godModeEffect);
	}
	
	public void detachExitJediModeText()	{
		godModeText.setText(exitJediModeTextString);
		guiNode.attachChild(godModeText);
		guiNode.attachChild(godModeEffect);
	}
	
	public void removeJediModeText()	{
		guiNode.detachChild(godModeText);
	}
	
	public void attachGrabIcon()	{
		guiNode.attachChild(handPic);
	}
	
	public void detachGrabIcon()	{
		try	{
			guiNode.detachChild(handPic);
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
}
