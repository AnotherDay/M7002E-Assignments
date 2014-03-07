package assignment4.actionListeners;

import assignment4.Constants;
import assignment4.Player;
import assignment4.gui.GuiManager;
import assignment4.objects.PlayerMarker;

import com.jme3.asset.AssetManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

public class MapModeActionListener implements ActionListener {
	
	private Player player;
	private Node rootNode, removedRoofs;
	private GuiManager guiManager;
	private Vector3f lastCameraDirection;
	private boolean inMapMode;
	private PlayerMarker playerMarker;
	
	public MapModeActionListener(Player player, Node rootNode, GuiManager guiManager, AssetManager assetManager)	{
		this.player = player;
		this.rootNode = rootNode;
		this.guiManager = guiManager;
		removedRoofs = new Node("RemovedRoofs");
		playerMarker = new PlayerMarker("PlayerMarker", assetManager);
	}
	
	@Override
	public void onAction(String name, boolean isPressed, float tpf) {
		if(name.equals(Constants.MAP_MODE) && !isPressed)	{
			if(inMapMode)	{
				reattachRoofs();
				rootNode.detachChild(playerMarker.getGeometry());
				player.setView(lastCameraDirection, Vector3f.UNIT_Y);
				player.attachCamera();
				inMapMode = false;
			}
			else	{
				detachRoofs();
				player.detachCamera();
				lastCameraDirection = player.getCameraDirection();
				rootNode.attachChild(playerMarker.getGeometry());
				playerMarker.translate(player.getLocation());
				player.setCameraLocation(new Vector3f(0, 150, 0));
				player.setView( Vector3f.UNIT_Y.mult(-1), Vector3f.UNIT_Z);
				inMapMode = true;
			}
		}
	}
	
	private void detachRoofs()	{
		for(String roofName : Constants.getRoofNames())	{
			removedRoofs.attachChild(rootNode.getChild(roofName));
			rootNode.detachChildNamed(roofName);
		}
	}
	
	private void reattachRoofs()	{
		for(Spatial roof : removedRoofs.getChildren())	{
			rootNode.attachChild(roof);
		}
		removedRoofs.detachAllChildren();
	}

}
