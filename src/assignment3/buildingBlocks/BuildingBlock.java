package assignment3.buildingBlocks;

import assignment3.Abstract3dObject;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;

public abstract class BuildingBlock extends Abstract3dObject {

	protected String materialDefinitionFile = "Common/MatDefs/Light/Lighting.j3md";
	protected String materialTextureFile;
	
	public BuildingBlock(float xValue, float yValue, float zValue, 
			AssetManager assetManager, String materialDefinitionFile, String materialTextureFile)	{
		super(new Box(xValue, yValue, zValue), "Box", 0);
		this.materialDefinitionFile = materialDefinitionFile;
		this.materialTextureFile = materialTextureFile;
		setMaterial(assetManager);
	}
	
	public BuildingBlock(float xValue, float yValue, float zValue, 
			AssetManager assetManager, String materialTextureFile)	{
		super(new Box(xValue, yValue, zValue), "Box", 0);
		this.materialTextureFile = materialTextureFile;
		setMaterial(assetManager);
	}
	
	private void setMaterial(AssetManager assetManager)	{
		Material boxMaterial = new Material(assetManager, materialDefinitionFile);
	    TextureKey textureKey = new TextureKey(materialTextureFile);
	    textureKey.setGenerateMips(true);
	    Texture tex3 = assetManager.loadTexture(textureKey);
	    tex3.setWrap(WrapMode.Repeat);
	    boxMaterial.setTexture("DiffuseMap", tex3);
	    meshGeometry.setMaterial(boxMaterial);
	}
}
