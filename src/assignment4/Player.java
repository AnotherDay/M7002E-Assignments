package assignment4;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

public class Player implements ActionListener {
	
	private CapsuleCollisionShape capsuleShape = new CapsuleCollisionShape(1.5f,2f);
	private CharacterControl player = new CharacterControl(capsuleShape, 0.05f);
	private InputManager inputManager;
	private Camera cam;
	private boolean left, right, up, down, cameraDetached;
	
	public Player(InputManager inputManager, Camera cam)	{
		this.inputManager = inputManager;
		this.cam = cam;
		
		player.setJumpSpeed(20);
		player.setFallSpeed(30);
		player.setGravity(80);
		
		initKeys();
	}
	
	public void updateWalkingDirection()	{
		if(!cameraDetached)	{
			player.setWalkDirection(getWalkingDirection());
			cam.setLocation(player.getPhysicsLocation());
		}
	}
	
	public void setLocation(Vector3f location)	{
		player.setPhysicsLocation(location);
	}
	
	public void setView(Vector3f direction, Vector3f up)	{
		cam.lookAtDirection(direction, up);
	}
	
	public void setCameraLocation(Vector3f location)	{
		cam.setLocation(location);
	}
	
	public void turnOffCameraRotation()	{
	}
	
	public void detachCamera()	{
		cameraDetached = true;
	}
	
	public void attachCamera()	{
		cameraDetached = false;
	}
	
	public Vector3f getLocation()	{
		return cam.getLocation();
	}
	
	public CharacterControl getCharacterControl()	{
		return player;
	}
	
	public Vector3f getCameraDirection()	{
		return cam.getDirection();
	}
	
	public Vector3f getCameraLocation()	{
		return cam.getLocation();
	}
	
	private Vector3f getWalkingDirection()	{
		Vector3f camDir = new Vector3f();
		Vector3f camLeft = new Vector3f();
		Vector3f walkDirection = new Vector3f();
		camDir.set(cam.getDirection()).multLocal(0.6f);
		camLeft.set(cam.getLeft()).multLocal(0.4f);
		walkDirection.set(0, 0, 0);
		if (left) {
		    walkDirection.addLocal(camLeft);
		}
		if (right) {
		    walkDirection.addLocal(camLeft.negate());
		}
		if (up) {
		    walkDirection.addLocal(camDir);
		}
		if (down) {
		    walkDirection.addLocal(camDir.negate());
		}
		return walkDirection;
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		if (name.equals("Left"))
			left = isPressed;
		else if (name.equals("Right"))
			right= isPressed;
		else if (name.equals("Up"))
			up = isPressed;
		else if (name.equals("Down"))
			down = isPressed;
		else if (name.equals("Jump") && isPressed)
			player.jump();
	}
	
	private void initKeys() {
		inputManager.addMapping("Left", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("Right", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("Up", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("Down", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("Jump", new KeyTrigger(KeyInput.KEY_SPACE));
		inputManager.addListener(this, "Left");
		inputManager.addListener(this, "Right");
		inputManager.addListener(this, "Up");
		inputManager.addListener(this, "Down");
		inputManager.addListener(this, "Jump");
	}
}
