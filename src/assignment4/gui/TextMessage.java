package assignment4.gui;

import com.jme3.asset.AssetManager;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.scene.Node;

public class TextMessage extends Node {

	private BitmapText text;
	private BitmapFont textFont;
	
	public TextMessage(String message, AssetManager assetManager) {
		super();
		textFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		text = new BitmapText(textFont);
		this.attachChild(text);
		text.setText(message);
	}
	
	public BitmapFont getFont()	{
		return textFont;
	}
	
	public float getLineWidth()	{
		return text.getLineWidth();
	}
	
}
