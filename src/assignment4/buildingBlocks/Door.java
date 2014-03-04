package assignment4.buildingBlocks;

import com.jme3.asset.AssetManager;

public class Door extends BuildingBlock {

	private static String materialTextureFile = "Textures/wood_door.png";
	private static String normalMapFile = "Textures/wood_door_normal.png";
	
	public Door(float xValue, float yValue, float zValue, String name, AssetManager assetManager) {
		super(xValue, yValue, zValue, name, assetManager, materialTextureFile);
		setMaterialWithNormalMap(normalMapFile, assetManager);
	}
}
