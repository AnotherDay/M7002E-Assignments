package assignment3.buildingBlocks;

import com.jme3.asset.AssetManager;

public class Roof extends BuildingBlock {

	private static String materialTextureFile = "Textures/wood_roof.jpg";
	
	public Roof(float xLength, float yLength, float zLength, AssetManager assetManager)	{
		super(xLength, yLength, zLength, assetManager, materialTextureFile);
	}
}
