package assignment4.gui;

import java.util.ArrayList;
import java.util.Arrays;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.ui.Picture;

public class JediModeGui extends Node {

	private BitmapText jediModeText;
	private ArrayList<BitmapText> instructionsList = new ArrayList<BitmapText>();
	private Picture jediModeEffect;
	
	private String enterJediModeTextString = "Press G to enter jedi mode"; 
	private String exitJediModeTextString = "Press G to exit jedi mode";
	
	public JediModeGui(int windowWidth, int windowHeight, AssetManager assetManager)	{
		super();
		BitmapFont jediModeFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		jediModeText = new BitmapText(jediModeFont, false);
		jediModeText.setLocalTranslation(jediModeFont.getCharSet().getRenderedSize(), 
				windowHeight, 
				0);
		jediModeText.setText(enterJediModeTextString);
		this.attachChild(jediModeText);
		
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
		jediInstructionsRight.setText("L : Push right");
		
		instructionsList.addAll(Arrays.asList(
				jediInstructionsUp, jediInstructionsDown, jediInstructionsForward, jediInstructionsBackward,
				jediInstructionsLeft, jediInstructionsRight));
		
		Vector3f lastLocalTranslation = jediModeText.getLocalTranslation();
		for(BitmapText instructions : instructionsList)		{
			instructions.setLocalTranslation(lastLocalTranslation.subtract(textHeight));
			lastLocalTranslation = instructions.getLocalTranslation();
		}
		
		jediModeEffect = new Picture("God Mode Effect");
		jediModeEffect.setImage(assetManager, "Other/god_mode_effect.png", true);
		jediModeEffect.setWidth(windowWidth);
		jediModeEffect.setHeight(windowHeight);
	}
	
	public void jediModeOff()	{
		jediModeText.setText(enterJediModeTextString);
		this.attachChild(jediModeText);
		for(BitmapText instructions : instructionsList)	{
			this.detachChild(instructions);
		}
		this.detachChild(jediModeEffect);
	}
	
	public void jediModeOn()	{
		jediModeText.setText(exitJediModeTextString);
		this.attachChild(jediModeText);
		for(BitmapText instructions : instructionsList)	{
			this.attachChild(instructions);
		}
		this.attachChild(jediModeEffect);
	}
}
