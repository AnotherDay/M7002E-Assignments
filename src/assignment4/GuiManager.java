package assignment4;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

public class GuiManager {
	
	private Node guiNode;
	private BitmapText crossHairsText, godModeText;
	
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
	}
	
	public void addEnterGodModeText()	{
		godModeText.setText(enterGodModeTextString);
		guiNode.attachChild(godModeText);
	}
	
	public void addExitGodModeText()	{
		godModeText.setText(exitGodModeTextString);
		guiNode.attachChild(godModeText);
	}
	
	public void removeGodModeText()	{
		guiNode.detachChild(godModeText);
	}
	
	public void addCrossHairs()	{
		guiNode.attachChild(crossHairsText);
	}
	
	public void removeCrossHairs()	{
		guiNode.detachChild(crossHairsText);
	}
}
