package assignment4;

import assignment4.exceptions.NoObjectFoundException;

import com.jme3.collision.CollisionResults;
import com.jme3.math.Ray;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;

public class ObjectPicker {

	private Player player;
	
	public ObjectPicker(Player player)	{
		this.player = player;
	}
	
	/**
	 * Implements picking with ray tracing
	 * 
	 * @param shootablesNode, the objects that are pickable
	 * @return the closest geometry from the camera
	 * @throws NoObjectFoundException if no object could be picked
	 */
	public Geometry pickClosestGeometry(Node shootablesNode) throws NoObjectFoundException	{
		CollisionResults results = getRayTraceResult(shootablesNode);
		if(results.size() > 0)	{
			return results.getClosestCollision().getGeometry();
		}
		else	{
			throw new NoObjectFoundException("No object could be selected");
		}
	}
	
	private CollisionResults getRayTraceResult(Node shootables)	{
		Ray ray = new Ray(player.getCameraLocation(), player.getCameraDirection());
		CollisionResults results = new CollisionResults();
		shootables.collideWith(ray, results);
		return results;
	}
}
