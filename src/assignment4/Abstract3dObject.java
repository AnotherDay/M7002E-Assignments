package assignment4;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

public abstract class Abstract3dObject {

	protected Mesh mesh;
	protected Geometry meshGeometry;
	protected RigidBodyControl meshPhysics;
	
	public Abstract3dObject(Mesh mesh, String geometryName, float mass)	{
		this.mesh = mesh;
		meshGeometry = new Geometry(geometryName, mesh);
		meshPhysics = new RigidBodyControl(mass);
		meshGeometry.addControl(meshPhysics);
		meshGeometry.setShadowMode(RenderQueue.ShadowMode.Receive);
	}
	
	public Geometry getGeometry()	{
		return meshGeometry;
	}
	
	public RigidBodyControl getPhysics()	{
		return meshPhysics;
	}
	
	public void translate(Vector3f location)	{
		meshPhysics.setPhysicsLocation(location);
	}
	
	public void translate(float x, float y, float z)	{
		meshPhysics.setPhysicsLocation(new Vector3f(x, y, z));
	}
	
	/**
	 * Note that values above 1 will cause the texture to tile, while values below 1 will cause the 
	 * texture to stretch.
	 */
	public void scaleTexture(Vector2f vector2f)	{
		mesh.scaleTextureCoordinates(vector2f);
	}
}
