package assignment4;

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
		MAP_MODE = "MapMode";
	
	public final static String
		START_ROOM_ROOF = "StartRoomRoof";
	
	public static String[] getMappingNames()	{
		return new String[]{PICK, PICK_DRAG, PUSH_AWAY, PULL_TOWARDS, PUSH_LEFT, PUSH_RIGHT, JEDI_MODE, 
				PUSH_UP, PUSH_DOWN, MOUSE_MOVEMENT, MAP_MODE};
	}
	
	public static String[] getRoofNames()	{
		return new String[]{START_ROOM_ROOF};
	}
}
