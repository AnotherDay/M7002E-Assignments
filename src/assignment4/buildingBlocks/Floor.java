package assignment4.buildingBlocks;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector2f;

public class Floor extends BuildingBlock {
	
	private static String materialTextureFile = "Textures/wood_floor.png";

	public Floor(float xValue, float yValue, float zValue, String name, AssetManager assetManager) {
		super(xValue, yValue, zValue, name, assetManager, materialTextureFile);
		scaleTexture(new Vector2f(2,2));
		physics.setFriction(1.0f);
	}
}
