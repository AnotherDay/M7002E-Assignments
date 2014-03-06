package assignment4;

import java.util.ArrayList;

import assignment4.actionListeners.InventoryListener;
import assignment4.actionListeners.MapModeActionListener;
import assignment4.actionListeners.MoveObjectListener;
import assignment4.buildingBlocks.Door;
import assignment4.buildingBlocks.Floor;
import assignment4.buildingBlocks.Roof;
import assignment4.buildingBlocks.Wall;
import assignment4.objects.Crate;
import assignment4.objects.Key;
import assignment4.objects.MagicWand;
import assignment4.objects.Torch;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.FileLocator;
import com.jme3.bullet.BulletAppState;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseAxisTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.plugins.blender.BlenderModelLoader;

public class Application extends SimpleApplication {

	private float halfWallHeight = 8.0f, halfFloorWidth = 20.0f, materialThickness = 1f;
	private Vector2f wallTextureScaleFactor = new Vector2f(7, 3);
	
	private BulletAppState bulletAppState;
	private Player player;
	private Node pickablesNode = new Node(), torchesNode = new Node();
	private MoveObjectListener moveObjectListener; 
	private GuiManager guiManager;
	private LightController lightController;
	private ArrayList<MagicWand> wandArray = new ArrayList<MagicWand>();
 
	@Override
	public void simpleInitApp() {
		/** Set up Physics Game */
		rootNode.detachAllChildren();
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		bulletAppState.getPhysicsSpace().setGravity(new Vector3f(0, -9.82f, 0));
		assetManager.registerLoader(BlenderModelLoader.class, "blend");

		assetManager.registerLocator("src/assignment4/assets", FileLocator.class);
		
		guiManager = new GuiManager(guiNode, settings.getWidth(), settings.getHeight(), assetManager);
		guiManager.attachCrossHairs();
		
		flyCam.setMoveSpeed(3f);
		
		player = new Player(inputManager, cam);
		bulletAppState.getPhysicsSpace().add(player.getCharacterControl());
		
		lightController = new LightController(rootNode, torchesNode);
		
		Key theKey = new Key("StartRoomKey", assetManager);
		theKey.translate(-2, 4, 0);
		theKey.scale(0.5f);
		pickablesNode.attachChild(theKey.getGeometry());
		bulletAppState.getPhysicsSpace().add(theKey.getPhysics());
		
		MagicWand magicWand = new MagicWand("LightRemover", assetManager);
		magicWand.translate(-5, 4, 0);
		pickablesNode.attachChild(magicWand.getGeometry());
		wandArray.add(magicWand);
		bulletAppState.getPhysicsSpace().add(magicWand.getPhysics());
		
		initRoom();
		initTorch();
		initInput();
		initBoxes();
		
		rootNode.attachChild(pickablesNode);
	}
	
	private void initRoom()	{
		Floor floor = new Floor(halfFloorWidth, materialThickness, halfFloorWidth, "StartRoomFloor", assetManager);
		floor.scaleTexture(new Vector2f(4, 4));
		
		Wall southWall = new Wall(halfFloorWidth, halfWallHeight, materialThickness, "StartRoomSouthWall", assetManager);
		southWall.translate(0, halfWallHeight, halfFloorWidth);
		southWall.scaleTexture(wallTextureScaleFactor);
		
		Wall northWall = new Wall(halfFloorWidth, halfWallHeight, materialThickness, "StartRoomSouthWall", assetManager);
		northWall.translate(0, halfWallHeight, -halfFloorWidth);
		northWall.scaleTexture(wallTextureScaleFactor);
		
		Wall westWall = new Wall(materialThickness, halfWallHeight, halfFloorWidth, "StartRoomWestWall", assetManager);
		westWall.translate(halfFloorWidth, halfWallHeight, 0);
		westWall.scaleTexture(wallTextureScaleFactor);
		
		Wall eastWall = new Wall(materialThickness, halfWallHeight, halfFloorWidth, "StartRoomEastWall", assetManager);
		eastWall.translate(-halfFloorWidth, halfWallHeight, 0);
		eastWall.scaleTexture(wallTextureScaleFactor);
		
		Roof roof = new Roof(halfFloorWidth, materialThickness, halfFloorWidth, Constants.START_ROOM_ROOF, assetManager);
		roof.translate(0, 2*halfWallHeight, 0);
		roof.scaleTexture(new Vector2f(4, 4));
		
		Door door = new Door(2, 4, 0.15f, "StartRoomDoor", assetManager);
		door.translate(2, 4, halfFloorWidth-materialThickness-0.15f);
		
		Abstract3dObject[] startRoomList = 
				new Abstract3dObject[]{floor, southWall, northWall, westWall, eastWall, roof, door};
		attachToRootNode(startRoomList);
		addPhysics(startRoomList);
	}
	
	private void initTorch()	{
		Torch northTorch = new Torch("NorthTorch", assetManager);
		northTorch.translate(0, 5, -halfFloorWidth+materialThickness+0.5f);
		northTorch.setFlushQueues(false);
		torchesNode.attachChild(northTorch);
		
		Torch westTorch = new Torch("EastTorch", assetManager);
		westTorch.rotate(0, (float)(Math.PI/2), 0);
		westTorch.translate(-halfFloorWidth+materialThickness+0.5f, 5, 0);
		westTorch.setFlushQueues(false);
		torchesNode.attachChild(westTorch);
		
		Torch eastTorch = new Torch("WestTorch", assetManager);
		eastTorch.rotate(0, -(float)(Math.PI/2), 0);
		eastTorch.translate(halfFloorWidth-materialThickness-0.5f, 5, 0);
		torchesNode.attachChild(eastTorch);
		
		rootNode.attachChild(torchesNode);
		for(Spatial torchSpatial : torchesNode.getChildren())	{
			Torch torch = (Torch) torchSpatial;
			lightController.addTorches(torch);
			viewPort.addProcessor(torch.getShadowRenderer());
			rootNode.addLight(torch.getLight());
		}
	}

	private void initBoxes()	{
		Crate crate1 = new Crate("Crate1", 2, 2, 2, 1, assetManager);
		crate1.translate(0, 2+materialThickness, -10);
		pickablesNode.attachChild(crate1.getGeometry());
		addPhysics(crate1);
		
		Crate crate2 = new Crate("Crate2", 2, 2, 2, 1, assetManager);
		crate2.translate(10, 2+materialThickness, -10);
		pickablesNode.attachChild(crate2.getGeometry());
		addPhysics(crate2);
		
		Crate crate3 = new Crate("Crate3", 2, 2, 2, 1, assetManager);
		crate3.translate(-10, 2+materialThickness, -10);
		pickablesNode.attachChild(crate3.getGeometry());
		addPhysics(crate3);
	}
	
	private void initInput()	{
		inputManager.addMapping(Constants.PICK, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
	    inputManager.addMapping(Constants.JEDI_PUSH_AWAY, new KeyTrigger(KeyInput.KEY_I));
	    inputManager.addMapping(Constants.JEDI_PULL_TOWARDS, new KeyTrigger(KeyInput.KEY_K));
	    inputManager.addMapping(Constants.JEDI_PUSH_LEFT, new KeyTrigger(KeyInput.KEY_J));
	    inputManager.addMapping(Constants.JEDI_PUSH_RIGHT, new KeyTrigger(KeyInput.KEY_L));
	    inputManager.addMapping(Constants.PUSH_UP, new KeyTrigger(KeyInput.KEY_U));
	    inputManager.addMapping(Constants.PUSH_DOWN, new KeyTrigger(KeyInput.KEY_O));
	    inputManager.addMapping(Constants.JEDI_MODE, new KeyTrigger(KeyInput.KEY_G));
	    inputManager.addMapping(Constants.MOUSE_MOVEMENT, new MouseAxisTrigger(MouseInput.AXIS_X, false));
	    inputManager.addMapping(Constants.MOUSE_MOVEMENT, new MouseAxisTrigger(MouseInput.AXIS_Y, false));
	    moveObjectListener = new MoveObjectListener(pickablesNode, cam, rootNode, guiManager, assetManager);
	    inputManager.addListener(moveObjectListener, Constants.getMappingNames());
	    
	    inputManager.addMapping(Constants.MAP_MODE, new KeyTrigger(KeyInput.KEY_M));
	    MapModeActionListener mapModeActionListener = new MapModeActionListener(player, rootNode, guiManager, assetManager);
	    inputManager.addListener(mapModeActionListener, Constants.MAP_MODE);
	    
	    inputManager.addMapping(Constants.PUT_IN_INVENTORY, new KeyTrigger(KeyInput.KEY_E));
	    InventoryListener inventoryListener = new InventoryListener(pickablesNode ,lightController, player, moveObjectListener, guiManager);
	    inventoryListener.addWands(wandArray.toArray(new MagicWand[wandArray.size()]));
	    inputManager.addListener(inventoryListener, Constants.PUT_IN_INVENTORY, Constants.PICK);
	}
	
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
		moveObjectListener.updateHighlightingPosition();
		moveObjectListener.checkDistanceToObject();
	}
}