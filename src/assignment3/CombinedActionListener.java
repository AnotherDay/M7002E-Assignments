package assignment3;

import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix3f;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class CombinedActionListener implements ActionListener {

	private Node shootablesNode;
	private Node rootNode;
	private Camera cam;
	private Geometry pickedObject;
	private Spatial highlighting;
	private Material highlightMaterial;
	private RigidBodyControl pickedObjectControll;
	private float pushForce = 8f;
	
	public CombinedActionListener(Node shootablesNode, Camera cam, Node rootNode, AssetManager assetManager, BulletAppState bulletAppState)	{
		this.shootablesNode = shootablesNode;
		this.cam = cam;
		this.rootNode = rootNode;
		
		highlightMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		highlightMaterial.setColor("Color", ColorRGBA.Yellow);
		highlightMaterial.getAdditionalRenderState().setWireframe(true);
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		try	{
			if(name.equals(Constants.PICK) && !isPressed)	{
				CollisionResults results = new CollisionResults();
				Ray ray = new Ray(cam.getLocation(), cam.getDirection());
				shootablesNode.collideWith(ray, results);
				if(results.size() > 0 && !results.getClosestCollision().getGeometry().equals(pickedObject))	{
					if(highlighting != null) rootNode.detachChild(highlighting);
					pickedObject =  results.getClosestCollision().getGeometry();
					pickedObjectControll = pickedObject.getControl(RigidBodyControl.class);
					
					highlighting = results.getClosestCollision().getGeometry().clone();
					highlighting.setLocalScale(1.01f);
					highlighting.setMaterial(highlightMaterial);
					rootNode.attachChild(highlighting);
				}
				else	{
					rootNode.detachChild(highlighting);
					highlighting = null;
					pickedObject = null;
					pickedObjectControll = null;
				}
			}
			else if(name.equals(Constants.PUSH_AWAY) && !isPressed)	{
				pickedObjectControll.applyImpulse(cam.getDirection().mult(pushForce), new Vector3f(0,0,0));
			}
			else if(name.equals(Constants.PULL_TOWARDS) && !isPressed)	{
				pickedObjectControll.applyImpulse(cam.getDirection().mult(pushForce).negate(), new Vector3f(0,0,0));
			}
			else if(name.equals(Constants.PUSH_LEFT) && !isPressed)	{
				pickedObjectControll.applyImpulse(getRotatedCameraDirection().mult(pushForce/2), new Vector3f(0,0,0));
			}
			else if(name.equals(Constants.PUSH_RIGHT) && !isPressed)	{
				pickedObjectControll.applyImpulse(getRotatedCameraDirection().mult(pushForce/2).negate(), new Vector3f(0,0,0));
			}
		}
		catch(NullPointerException npe)	{
			//pickedObjectControll not instantiated
		}
	}
	
	public void updateHighlightingPosition()	{
		try	{
			highlighting.setLocalTranslation(pickedObject.getLocalTranslation());
			highlighting.setLocalRotation(pickedObject.getLocalRotation());
		}
		catch(NullPointerException npe)	{
			//Mark or vector objects not initialized, ignore it
		}
	}
	
	/**
	 * Rotates the vector between the camera and the selected object 90 degrees around the z-axis
	 * 
	 * @return the direction vector rotated 90 around the z-axis
	 */
	private Vector3f getRotatedCameraDirection()	{
		float x = pickedObject.getLocalTranslation().x - cam.getLocation().x;
		float y = pickedObject.getLocalTranslation().y - cam.getLocation().y;
		float z = pickedObject.getLocalTranslation().z - cam.getLocation().z;
		
		float rX = z/10;
		float rY = y/10;
		float rZ = -x/10;
		
		return new Vector3f(rX, rY, rZ);
	}

}
