package assignment3;

import java.util.ArrayList;
import java.util.Arrays;

import assignment3.buildingBlocks.Floor;
import assignment3.buildingBlocks.Roof;
import assignment3.buildingBlocks.Wall;
import assignment3.objects.Crate;
import assignment3.objects.Torch;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

public class Application extends SimpleApplication {

	private float halfWallHeight = 8.0f, halfFloorWidth = 20.0f, materialThickness = 0.2f;
	private Vector2f wallTextureScaleFactor = new Vector2f(7, 3);
	
	private BulletAppState bulletAppState;
	private Player player;
	private ArrayList<Torch> torchList = new ArrayList<Torch>();
	private Node shootablesNode = new Node();
	private CombinedActionListener actionListener;
 
	@Override
	public void simpleInitApp() {
		/** Set up Physics Game */
		rootNode.detachAllChildren();
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, -9.82f, 0));

		assetManager.registerLocator("src/assignment3/assets", FileLocator.class);
		
		flyCam.setMoveSpeed(3f);
		
		player = new Player(inputManager, cam);
		bulletAppState.getPhysicsSpace().add(player.getCharacterControl());
		initRoom();
		initTorch();
		initCrossHairs();
		initPicker();
		initBoxes();
	}
	
	private void initRoom()	{
		Floor floor = new Floor(halfFloorWidth, materialThickness, halfFloorWidth, assetManager);
		floor.scaleTexture(new Vector2f(4, 4));
		
		Wall southWall = new Wall(halfFloorWidth, halfWallHeight, materialThickness, assetManager);
		southWall.translate(0, halfWallHeight, halfFloorWidth);
		southWall.scaleTexture(wallTextureScaleFactor);
		
		Wall northWall = new Wall(halfFloorWidth, halfWallHeight, materialThickness, assetManager);
		northWall.translate(0, halfWallHeight, -halfFloorWidth);
		northWall.scaleTexture(wallTextureScaleFactor);
		
		Wall westWall = new Wall(materialThickness, halfWallHeight, halfFloorWidth, assetManager);
		westWall.translate(halfFloorWidth, halfWallHeight, 0);
		westWall.scaleTexture(wallTextureScaleFactor);
		
		Wall eastWall = new Wall(materialThickness, halfWallHeight, halfFloorWidth, assetManager);
		eastWall.translate(-halfFloorWidth, halfWallHeight, 0);
		eastWall.scaleTexture(wallTextureScaleFactor);
		
		Roof roof = new Roof(halfFloorWidth, materialThickness, halfFloorWidth, assetManager);
		roof.translate(0, 2*halfWallHeight, 0);
		roof.scaleTexture(new Vector2f(4, 4));
		
		Abstract3dObject[] roomList = new Abstract3dObject[]{floor, southWall, northWall, westWall, eastWall, roof};
		attachToRootNode(roomList);
		addPhysics(roomList);
	}
	
	private void initTorch()	{
		Torch northTorch = new Torch("NorthTorch", assetManager);
		northTorch.translate(0, 5, -halfFloorWidth+0.5f);
		
		Torch southTorch = new Torch("SouthTorch", assetManager);
		southTorch.rotate(0, (float)Math.PI, 0);
		southTorch.translate(0, 5, halfFloorWidth-0.5f);
		
		Torch westTorch = new Torch("EastTorch", assetManager);
		westTorch.rotate(0, (float)(Math.PI/2), 0);
		westTorch.translate(-halfFloorWidth+0.5f, 5, 0);
		
		Torch eastTorch = new Torch("WestTorch", assetManager);
		eastTorch.rotate(0, -(float)(Math.PI/2), 0);
		eastTorch.translate(halfFloorWidth-0.5f, 5, 0);
		
		torchList.addAll(Arrays.asList(northTorch, southTorch, westTorch, eastTorch));
		for(Torch torch : torchList)	{
			rootNode.attachChild(torch.getNode());
			rootNode.addLight(torch.getLight());
		}
	}
	
	/** A plus sign used as crosshairs to help the player with aiming.*/
	private void initCrossHairs() {
		guiNode.detachAllChildren();
		guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
		BitmapText ch = new BitmapText(guiFont, false);
		ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
		ch.setText("+");        // fake crosshairs :)
		ch.setLocalTranslation( // center
		settings.getWidth() / 2 - guiFont.getCharSet().getRenderedSize() / 3 * 2,
		settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
		guiNode.attachChild(ch);
	}
	
	private void initBoxes()	{
		Crate crate1 = new Crate("Crate1", 2, 2, 2, 1, assetManager);
		crate1.translate(0, 2, -10);
		shootablesNode.attachChild(crate1.getGeometry());
		rootNode.attachChild(shootablesNode);
		addPhysics(crate1);
		
		Crate crate2 = new Crate("Crate2", 2, 2, 2, 1, assetManager);
		crate2.translate(10, 2, -10);
		shootablesNode.attachChild(crate2.getGeometry());
		rootNode.attachChild(shootablesNode);
		addPhysics(crate2);
		
		Crate crate3 = new Crate("Crate3", 2, 2, 2, 1, assetManager);
		crate3.translate(-10, 2, -10);
		shootablesNode.attachChild(crate3.getGeometry());
		rootNode.attachChild(shootablesNode);
		addPhysics(crate3);
	}
	
	private void initPicker()	{
	    inputManager.addMapping(Constants.PICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
	    inputManager.addMapping(Constants.PUSH_AWAY, new KeyTrigger(KeyInput.KEY_I));
	    inputManager.addMapping(Constants.PULL_TOWARDS, new KeyTrigger(KeyInput.KEY_K));
	    inputManager.addMapping(Constants.PUSH_LEFT, new KeyTrigger(KeyInput.KEY_J));
	    inputManager.addMapping(Constants.PUSH_RIGHT, new KeyTrigger(KeyInput.KEY_L));
	    actionListener = new CombinedActionListener(shootablesNode, cam, rootNode, assetManager, bulletAppState); 
	    inputManager.addListener(actionListener, Constants.getMappingNames());
	}
	
	/**
	 * For debugging, updated by simpleUpdate()
	 */
//	private void updateCoordinatesDisplay()	{
//		guiNode.detachAllChildren();
//		BitmapText ch = new BitmapText(guiFont, false);
//		ch.setSize(guiFont.getCharSet().getRenderedSize() * 2);
//		ch.setText("X: " + cam.getLocation().getX() + "\n Y: " + cam.getLocation().getY()); // fake crosshairs :)
//		ch.setLocalTranslation( 
//		- guiFont.getCharSet().getRenderedSize() / 3 * 2,
//		settings.getHeight() / 2 + ch.getLineHeight() / 2, 0);
//		guiNode.attachChild(ch);
//	}
	
	private void attachToRootNode(Abstract3dObject... abstractBoxList)	{
		for(Abstract3dObject abstractBox : abstractBoxList)	{
			rootNode.attachChild(abstractBox.getGeometry());
		}
	}
	
	private void addPhysics(Abstract3dObject... abstractBoxArray)	{
		for(Abstract3dObject abstractBox : abstractBoxArray)	{
			bulletAppState.getPhysicsSpace().add(abstractBox.getPhysics());
		}
	}
  
  /**
   * This is the main event loop--walking happens here.
   * We check in which direction the player is walking by interpreting
   * the camera direction forward (camDir) and to the side (camLeft).
   * The setWalkDirection() command is what lets a physics-controlled player walk.
   * We also make sure here that the camera moves with player.
   */
	@Override
	public void simpleUpdate(float tpf) {
		player.updateWalkingDirection();
		actionListener.updateHighlightingPosition();
//		updateCoordinatesDisplay();
	}
}