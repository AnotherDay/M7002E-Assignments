package assignment4.objects;

import assignment4.Abstract3dObject;

import com.jme3.asset.AssetManager;
import com.jme3.asset.BlenderKey;

public class Key extends Abstract3dObject {

	private static String filePath = "Models/basicBomb.blend";
	private static float mass = 0.2f;
	
	private static BlenderKey blenderKey = new BlenderKey(filePath);
	
	public Key(String keyName, AssetManager assetManager) {
		super(blenderKey, assetManager, keyName, mass);
	}

}
