package assignment3.buildingBlocks;

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

public abstract class BuildingBlock {

	protected String materialDefinitionFile;
	protected String materialTextureFile;
	
	protected Box box;
	protected Geometry boxGeometry;
	protected RigidBodyControl boxPhysics;
	
	public BuildingBlock(float xValue, float yValue, float zValue, 
			AssetManager assetManager, String materialDefinitionFile, String materialTextureFile)	{
		this.materialDefinitionFile = materialDefinitionFile;
		this.materialTextureFile = materialTextureFile;
		box = new Box(xValue, yValue, zValue);
		boxGeometry = new Geometry("floor", box);
		boxPhysics = new RigidBodyControl(0);
		boxGeometry.addControl(boxPhysics);
		setMaterial(assetManager);
	}
	
	/**
	 * Note that values above 1 will cause the texture to tile, while values below 1 will cause the 
	 * texture to stretch.
	 */
	public void scaleTexture(Vector2f vector2f)	{
		box.scaleTextureCoordinates(vector2f);
	}
	
	public Geometry getGeometry()	{
		return boxGeometry;
	}
	
	public RigidBodyControl getPhysics()	{
		return boxPhysics;
	}
	
	public void translate(Vector3f location)	{
		boxPhysics.setPhysicsLocation(new Vector3f(0, 0, -10));
	}
	
	public void translate(float x, float y, float z)	{
		boxPhysics.setPhysicsLocation(new Vector3f(x, y, z));
	}
	
	private void setMaterial(AssetManager assetManager)	{
		Material boxMaterial = new Material(assetManager, materialDefinitionFile);
	    TextureKey textureKey = new TextureKey(materialTextureFile);
	    textureKey.setGenerateMips(true);
	    Texture tex3 = assetManager.loadTexture(textureKey);
	    tex3.setWrap(WrapMode.Repeat);
	    boxMaterial.setTexture("ColorMap", tex3);
	    boxGeometry.setMaterial(boxMaterial);
	}
}
