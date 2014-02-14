package assignment3.buildingBlocks;

import com.jme3.asset.AssetManager;

public class Wall extends BuildingBlock {

	private static String materialDefinitionFile = "Common/MatDefs/Misc/Unshaded.j3md";
	private static String materialTextureFile = "Textures/Terrain/BrickWall/BrickWall.jpg";
	
	public Wall(float xValue, float yValue, float zValue, AssetManager assetManager) {
		super(xValue, yValue, zValue, assetManager, materialDefinitionFile, materialTextureFile);
	}

}
