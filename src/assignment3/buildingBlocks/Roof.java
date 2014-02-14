package assignment3.buildingBlocks;

import com.jme3.asset.AssetManager;

public class Roof extends BuildingBlock {

	private static String materialDefinitionFile = "Common/MatDefs/Misc/Unshaded.j3md";
	private static String materialTextureFile = "Textures/Sky/Lagoon/lagoon_up.jpg";
	
	public Roof(float xLength, float yLength, float zLength, AssetManager assetManager)	{
		super(xLength, yLength, zLength, assetManager, materialDefinitionFile, materialTextureFile);
	}
}
