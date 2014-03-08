package assignment4.objects;

import assignment4.Abstract3dObject;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.shape.Box;

public class PlayerMarker extends Abstract3dObject {
	
	private static String materialDefinitionFile = "Common/MatDefs/Misc/Unshaded.j3md";
	private static float boxRadius = 2f;
	
	private AssetManager assetManager;
	
	public PlayerMarker(String name, AssetManager assetManager) {
		super(new Box(boxRadius, boxRadius, boxRadius), name, 0);
		this.assetManager = assetManager;
		geometry.setShadowMode(RenderQueue.ShadowMode.Off);
		setMaterial();
	}

	private void setMaterial()	{
		Material markerMaterial = new Material(assetManager, materialDefinitionFile);
		markerMaterial.setColor("Color", ColorRGBA.Red);
		geometry.setMaterial(markerMaterial);
	}
}
