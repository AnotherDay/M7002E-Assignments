package assignment3.objects;

import assignment3.Abstract3dObject;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.material.Material;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

public class Crate extends Abstract3dObject {
	
	private static String materialDefinitionFile = "Common/MatDefs/Light/Lighting.j3md";
	private static String materialTextureFile = "Textures/crate.png"; //In the project asset manager folder

	public Crate(float xLength, float yLength, float zLength, float mass, AssetManager assetManager) {
		super(new Box(xLength, yLength, zLength), "Crate", mass);
		setMaterial(assetManager);
	}
	
	private void setMaterial(AssetManager assetManager)	{
		Material boxMaterial = new Material(assetManager, materialDefinitionFile);
	    TextureKey textureKey = new TextureKey(materialTextureFile);
	    textureKey.setGenerateMips(true);
	    Texture tex3 = assetManager.loadTexture(textureKey);
	    boxMaterial.setTexture("DiffuseMap", tex3);
	    meshGeometry.setMaterial(boxMaterial);
	}

}
