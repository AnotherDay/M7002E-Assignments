package assignment4;

import com.jme3.asset.AssetManager;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.collision.CollisionResults;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Line;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class MoveObjectListener implements AnalogListener, ActionListener {

	private Node shootablesNode;
	private Node rootNode;
	private Camera cam;
	private Geometry pickedObject;
	private Spatial highlighting;
	private Material highlightMaterial;
	private RigidBodyControl pickedObjectControll;
	private float pushForce = 8f;
	private boolean inGodMode = false;
	private GuiManager guiManger;
	
	private Vector3f gravityVector = new Vector3f(0, -9.82f, 0);
	private float distance = 0;
	
	public MoveObjectListener(Node shootablesNode, Camera cam, Node rootNode, GuiManager guiManager, AssetManager assetManager)	{
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
	public void onAnalog(String name, float value, float tpf) {
		try	{
			if(inGodMode)	{
				if(name.equals(Constants.PUSH_AWAY))	{
					pickedObjectControll.applyCentralForce(cam.getDirection().mult(pushForce));
				}
				else if(name.equals(Constants.PULL_TOWARDS))	{
					pickedObjectControll.applyCentralForce(cam.getDirection().mult(pushForce).negate());
				}
				else if(name.equals(Constants.PUSH_LEFT))	{
					pickedObjectControll.applyCentralForce(directionVectorRotatedAroundZAxis().mult(pushForce/2));
				}
				else if(name.equals(Constants.PUSH_RIGHT))	{
					pickedObjectControll.applyCentralForce(directionVectorRotatedAroundZAxis().mult(pushForce/2).negate());
				}
				else if(name.equals(Constants.PUSH_UP))	{
					pickedObjectControll.applyCentralForce(new Vector3f(0, 1.0f, 0).mult(pushForce));
				}
				else if(name.equals(Constants.PUSH_DOWN))	{
					pickedObjectControll.applyCentralForce(new Vector3f(0, 1.0f, 0).mult(pushForce).negate());
				}
			}
			else if(!inGodMode && name.equals(Constants.PICK_DRAG))	{
				pickedObjectControll.setPhysicsLocation(cam.getLocation().add(cam.getDirection().mult(distance)));
			}
		}
		catch(NullPointerException npe)	{
			//pickedObject not initialized, ignore
		}
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		if(name.equals(Constants.PICK_DRAG) && pickedObjectControll == null && !inGodMode)	{
			CollisionResults results = new CollisionResults();
			Ray ray = new Ray(cam.getLocation(), cam.getDirection());
			shootablesNode.collideWith(ray, results);
			if(results.size() > 0)	{
				pickedObject =  results.getClosestCollision().getGeometry();
				pickedObjectControll = pickedObject.getControl(RigidBodyControl.class);
				distance = pickedObjectControll.getPhysicsLocation().distance(cam.getLocation());
			}
		}
		else if(name.equals(Constants.PICK_DRAG) && !isPressed && pickedObject != null && !inGodMode)	{
			System.out.println("Exit drag mode");
			removeSelectedObject();
		}
		else if(name.equals(Constants.PICK) && inGodMode && !isPressed)	{
			pickObject();
		}
		else if(name.equals(Constants.GOD_MODE) && !isPressed)	{
			if(inGodMode)	{
				removeSelectedObject();
				guiManger.addEnterGodModeText();
				inGodMode = false;
			}
			else	{
				guiManger.addExitGodModeText();
				inGodMode = true;
			}
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
			if(highlighting != null && pickedObjectControll != null) 
				removeSelectedObject();
			pickedObject =  results.getClosestCollision().getGeometry();
			pickedObjectControll = pickedObject.getControl(RigidBodyControl.class);
			pickedObjectControll.setGravity(Vector3f.ZERO);
			
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
		if(pickedObject != null)	{ 
			pickedObjectControll.setGravity(gravityVector);
			pickedObjectControll.activate(); //Makes sure that the physic engine doesn't ignore the object when it has not moved
			pickedObjectControll = null;
		}
		if(highlighting != null) 
			rootNode.detachChild(highlighting);
		highlighting = null;
		pickedObject = null;
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
}
