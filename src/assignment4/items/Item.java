package assignment4.items;

import com.jme3.asset.AssetManager;
import com.jme3.asset.BlenderKey;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

import assignment4.Abstract3dObject;

public abstract class Item extends Abstract3dObject {

	public Item(Mesh mesh, String geometryName, float mass) {
		super(mesh, geometryName, mass);
	}

	public Item(Geometry geometry, String geometryName, float mass) {
		super(geometry, geometryName, mass);
	}

	public Item(BlenderKey modelKey, AssetManager assetManager,
			String geometryName, float mass) {
		super(modelKey, assetManager, geometryName, mass);
	}

}
