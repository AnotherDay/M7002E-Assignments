package assignment4.items;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.shape.Cylinder;
import com.jme3.texture.Texture;

public class MagicWand extends Item {

	private static float mass = 1f;
	private static String materialDefinitionFile = "Common/MatDefs/Light/Lighting.j3md";
	private static String materialTextureFile = "Textures/bomber_metal.jpg";
	
	private AssetManager assetManager;
	
	public MagicWand(String geometryName, AssetManager assetManager) {
		super(new Cylinder(10, 50, 0.1f, 3f, true), geometryName, mass);
		this.assetManager = assetManager;
		setMaterial();
		geometry.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
		CollisionShape boxShape = CollisionShapeFactory.createBoxShape(geometry);
		physics = new RigidBodyControl(boxShape, mass); //Give it a square collisions shape so that it doesn't roll away
		geometry.removeControl(RigidBodyControl.class);
		geometry.addControl(physics);
		physics.setFriction(1.0f);
	}
	
	private void setMaterial()	{
		Material wandMaterial = new Material(assetManager, materialDefinitionFile);
	    TextureKey textureKey = new TextureKey(materialTextureFile);
	    textureKey.setGenerateMips(true);
	    Texture tex3 = assetManager.loadTexture(textureKey);
	    wandMaterial.setTexture("DiffuseMap", tex3);
	    wandMaterial.setColor("Diffuse",ColorRGBA.White);
	    wandMaterial.setColor("Specular",ColorRGBA.White);
	    wandMaterial.setFloat("Shininess", 64f);  // [0,128]
	    wandMaterial.setBoolean("UseMaterialColors",true); 
	    geometry.setMaterial(wandMaterial);
	}

}
