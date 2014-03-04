package assignment4;

import com.jme3.asset.AssetManager;
import com.jme3.asset.BlenderKey;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;

public abstract class Abstract3dObject {

	protected Geometry geometry;
	protected RigidBodyControl physics;
	
	public Abstract3dObject(Mesh mesh, String geometryName, float mass)	{
		geometry = new Geometry(geometryName, mesh);
		physics = new RigidBodyControl(mass);
		geometry.addControl(physics);
		geometry.setShadowMode(RenderQueue.ShadowMode.Receive);
	}
	
	public Abstract3dObject(Geometry geometry, String geometryName, float mass)	{
		this.geometry = geometry;
		physics = new RigidBodyControl(mass);
		geometry.addControl(physics);
		geometry.setShadowMode(RenderQueue.ShadowMode.Receive);
	}
	
	public Abstract3dObject(BlenderKey modelKey, AssetManager assetManager, String geometryName, float mass)	{
		Node node = ((Node)((Node) assetManager.loadModel(modelKey)).getChild(1));
		geometry = ((Geometry) node.getChild(0));
		physics = new RigidBodyControl(mass);
		geometry.addControl(physics);
		geometry.setShadowMode(RenderQueue.ShadowMode.CastAndReceive);
	}
	
	public Geometry getGeometry()	{
		return geometry;
	}
	
	public RigidBodyControl getPhysics()	{
		return physics;
	}
	
	public void translate(Vector3f location)	{
		physics.setPhysicsLocation(location);
	}
	
	public void translate(float x, float y, float z)	{
		physics.setPhysicsLocation(new Vector3f(x, y, z));
	}
	
	public void scale(float scale)	{
		geometry.scale(scale);
	}
	
	/**
	 * Note that values above 1 will cause the texture to tile, while values below 1 will cause the 
	 * texture to stretch.
	 */
	public void scaleTexture(Vector2f vector2f)	{
		geometry.getMesh().scaleTextureCoordinates(vector2f);
	}
}
