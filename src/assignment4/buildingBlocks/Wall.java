package assignment4.buildingBlocks;

import com.jme3.asset.AssetManager;

public class Wall extends BuildingBlock {

	private static String materialTextureFile = "Textures/Terrain/BrickWall/BrickWall.jpg";
	
	public Wall(float xValue, float yValue, float zValue, String name, AssetManager assetManager) {
		super(xValue, yValue, zValue, name, assetManager, materialTextureFile);
	}

}
