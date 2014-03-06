package assignment4;

import com.jme3.math.ColorRGBA;

public class Constants {

	//Player actions
	public final static String
		PICK = "Pick",
		JEDI_PUSH_AWAY = "PushAway",
		JEDI_PULL_TOWARDS = "PushTowards",
		JEDI_PUSH_LEFT = "PushLeft",
		JEDI_PUSH_RIGHT = "PushRight",
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
		return new String[]{PICK, JEDI_PUSH_AWAY, JEDI_PULL_TOWARDS, JEDI_PUSH_LEFT, JEDI_PUSH_RIGHT, JEDI_MODE, 
				PUSH_UP, PUSH_DOWN, MOUSE_MOVEMENT, MAP_MODE, PUT_IN_INVENTORY};
	}
	
	public static String[] getRoofNames()	{
		return new String[]{START_ROOM_ROOF};
	}
}
