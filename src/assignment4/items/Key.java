package assignment4.items;

import com.jme3.asset.AssetManager;
import com.jme3.asset.BlenderKey;

public class Key extends Item {

	private static String filePath = "Models/key.blend";
	private static float mass = 0.2f;
	
	private static BlenderKey blenderKey = new BlenderKey(filePath);
	
	public Key(String keyName, AssetManager assetManager) {
		super(blenderKey, assetManager, keyName, mass);
	}

}
