package assignment4;

import com.jme3.math.ColorRGBA;

public class Constants {

	//Player actions
	public final static String
		PICK = "Pick",
		PICK_DRAG = "PickDrag",
		PUSH_AWAY = "PushAway",
		PULL_TOWARDS = "PushTowards",
		PUSH_LEFT = "PushLeft",
		PUSH_RIGHT = "PushRight",
		JEDI_MODE = "JediMode",
		PUSH_UP = "PushUp",
		PUSH_DOWN = "PushDown",
		MOUSE_MOVEMENT = "MouseMovement", 
		MAP_MODE = "MapMode",
		PUT_IN_INVENTORY = "PutInInventory";
	
	public final static String
		START_ROOM_ROOF = "StartRoomRoof";
	
	public final static ColorRGBA
		TORCH_LIGHT_COLOR = new ColorRGBA(1.0f, 0.6f, 0, 1.0f);
	
	public static String[] getMappingNames()	{
		return new String[]{PICK, PICK_DRAG, PUSH_AWAY, PULL_TOWARDS, PUSH_LEFT, PUSH_RIGHT, JEDI_MODE, 
				PUSH_UP, PUSH_DOWN, MOUSE_MOVEMENT, MAP_MODE, PUT_IN_INVENTORY};
	}
	
	public static String[] getRoofNames()	{
		return new String[]{START_ROOM_ROOF};
	}
}
