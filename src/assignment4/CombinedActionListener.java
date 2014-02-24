package assignment4;

import com.jme3.asset.AssetManager;
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
	private boolean godMode = false;
	private GuiManager guiManger;
	private Vector3f oldGravityValue = Vector3f.UNIT_X;
	
	public CombinedActionListener(Node shootablesNode, Camera cam, Node rootNode, GuiManager guiManager, AssetManager assetManager)	{
		this.shootablesNode = shootablesNode;
		this.cam = cam;
		this.rootNode = rootNode;
		this.guiManger = guiManager;
		
		guiManager.addEnterGodModeText();
		
		highlightMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		highlightMaterial.setColor("Color", ColorRGBA.Yellow);
		highlightMaterial.getAdditionalRenderState().setWireframe(true);
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		try	{
			if(godMode && !isPressed)	{
				if(name.equals(Constants.PICK))	{
					pickObject();
				}
				else if(name.equals(Constants.PUSH_AWAY))	{
					pickedObjectControll.applyImpulse(cam.getDirection().mult(pushForce), Vector3f.ZERO);
				}
				else if(name.equals(Constants.PULL_TOWARDS))	{
					pickedObjectControll.applyImpulse(cam.getDirection().mult(pushForce).negate(), Vector3f.ZERO);
				}
				else if(name.equals(Constants.PUSH_LEFT))	{
					pickedObjectControll.applyImpulse(directionVectorRotatedAroundZAxis().mult(pushForce/2), Vector3f.ZERO);
				}
				else if(name.equals(Constants.PUSH_RIGHT))	{
					pickedObjectControll.applyImpulse(directionVectorRotatedAroundZAxis().mult(pushForce/2).negate(), Vector3f.ZERO);
				}
				else if(name.equals(Constants.PUSH_UP))	{
					oldGravityValue = pickedObjectControll.getGravity();
					pickedObjectControll.setGravity(Vector3f.ZERO);
					pickedObjectControll.applyImpulse(directionVectorRotatedAroundXAxis().mult(pushForce/10), Vector3f.ZERO);
				}
				else if(name.equals(Constants.PUSH_DOWN))	{
					pickedObjectControll.setGravity(oldGravityValue);
				}
			}
			else if(name.equals(Constants.GOD_MODE) && isPressed)	{
				if(godMode)	{
					removeSelectedObject();
					guiManger.addEnterGodModeText();
					godMode = false;
				}
				else	{
					guiManger.addExitGodModeText();
					godMode = true;
				}
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
	
	private void pickObject()	{
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
			removeSelectedObject();
		}
	}
	
	private void removeSelectedObject()	{
		rootNode.detachChild(highlighting);
		highlighting = null;
		pickedObject = null;
		pickedObjectControll = null;
	}
	
	/**
	 * Rotates the vector between the camera and the selected object 90 degrees around the z-axis
	 * 
	 * @return the direction vector rotated 90 around the z-axis
	 */
	private Vector3f directionVectorRotatedAroundZAxis()	{
		float x = pickedObject.getLocalTranslation().x - cam.getLocation().x;
		float y = pickedObject.getLocalTranslation().y - cam.getLocation().y;
		float z = pickedObject.getLocalTranslation().z - cam.getLocation().z;
		
		float rX = z/10;
		float rY = y/10;
		float rZ = -x/10;
		
		return new Vector3f(rX, rY, rZ);
	}
	
	/**
	 * Rotates the vector between the camera and the selected object 90 degrees around the x-axis
	 * 
	 * @return the direction vector rotated 90 around the x-axis
	 */
	private Vector3f directionVectorRotatedAroundXAxis()	{
		float x = pickedObject.getLocalTranslation().x - cam.getLocation().x;
		float y = pickedObject.getLocalTranslation().y - cam.getLocation().y;
		float z = pickedObject.getLocalTranslation().z - cam.getLocation().z;
		
		float rX = x/10;
		float rY = -z/10;
		float rZ = y/10;
		
		return new Vector3f(rX, rY, rZ);
	}
}
