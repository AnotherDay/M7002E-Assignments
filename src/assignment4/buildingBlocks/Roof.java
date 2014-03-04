package assignment4.buildingBlocks;

import com.jme3.asset.AssetManager;

public class Roof extends BuildingBlock {

	private static String materialTextureFile = "Textures/wood_roof.jpg";
	
	public Roof(float xLength, float yLength, float zLength, String name, AssetManager assetManager)	{
		super(xLength, yLength, zLength, name, assetManager, materialTextureFile);
	}
}
