package assignment3;

import java.util.ArrayList;
import java.util.Arrays;

import assignment3.buildingBlocks.BuildingBlock;
import assignment3.buildingBlocks.Crate;
import assignment3.buildingBlocks.Floor;
import assignment3.buildingBlocks.Roof;
import assignment3.buildingBlocks.Wall;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.font.BitmapText;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

public class Application extends SimpleApplication {

	private float halfWallHeight = 5.0f, halfFloorWidth = 20.0f, materialThickness = 0.2f;
	private Vector2f wallTextureScaleFactor = new Vector2f(7, 2f);
	
	private BulletAppState bulletAppState;
	private Player player;
	private ArrayList<BuildingBlock> boxList = new ArrayList<BuildingBlock>();
 
	@Override
	public void simpleInitApp() {
		/** Set up Physics Game */
		bulletAppState = new BulletAppState();
		stateManager.attach(bulletAppState);
		//bulletAppState.getPhysicsSpace().enableDebug(assetManager);
		
		flyCam.setMoveSpeed(3f);
		cam.setLocation(new Vector3f(0f, 4f, 0f));
		
		player = new Player(inputManager, cam);
		bulletAppState.getPhysicsSpace().add(player.getCharacterControl());
		initRoom();
		initCrossHairs();
		
//		Crate crate = new Crate(5, 5, 5, assetManager);
//		crate.translate(0, 10, 5);
//		boxList.add(crate);
		
		addPhysics(boxList.toArray(new BuildingBlock[boxList.size()]));
		attachToRootNode(boxList.toArray(new BuildingBlock[boxList.size()]));
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
		
		boxList.addAll(Arrays.asList(floor, southWall, northWall, westWall, eastWall, roof));
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
	
	private void attachToRootNode(BuildingBlock... abstractBoxList)	{
		for(BuildingBlock abstractBox : abstractBoxList)	{
			rootNode.attachChild(abstractBox.getGeometry());
		}
	}
	
	private void addPhysics(BuildingBlock... abstractBoxList)	{
		for(BuildingBlock abstractBox : abstractBoxList)	{
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
	}
}