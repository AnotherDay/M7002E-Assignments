package assignment3.buildingBlocks;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector2f;

public class Floor extends BuildingBlock {
	
	private static String materialDefinitionFile = "Common/MatDefs/Misc/Unshaded.j3md";
	private static String materialTextureFile = "Textures/Terrain/Rock/Rock.PNG";

	public Floor(float xValue, float yValue, float zValue, AssetManager assetManager) {
		super(xValue, yValue, zValue, assetManager, materialDefinitionFile, materialTextureFile);
		scaleTexture(new Vector2f(2,2));
	}
}
