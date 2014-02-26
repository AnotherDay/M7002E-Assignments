package assignment4;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

public class GuiManager {
	
	private Node guiNode;
	private BitmapText crossHairsText, godModeText;
	private Picture handPic;
	
	private String enterGodModeTextString = "Press G to enter god mode"; 
	private String exitGodModeTextString = "Press G to exit god mode";
	
	public GuiManager(Node guiNode, int windowWidth, int windowHeight, AssetManager assetManager)	{
		this.guiNode = guiNode;
		
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
		handPic.setLocalTranslation( // center
				(windowWidth / 2) + 2,
				(windowHeight / 2) + 2, 
				0);
	}
	
	public void attachEnterGodModeText()	{
		godModeText.setText(enterGodModeTextString);
		guiNode.attachChild(godModeText);
	}
	
	public void detachExitGodModeText()	{
		godModeText.setText(exitGodModeTextString);
		guiNode.attachChild(godModeText);
	}
	
	public void removeGodModeText()	{
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
}
