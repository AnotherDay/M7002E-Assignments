package assignment4;

public class Constants {

	public final static String
		PICK = "Pick",
		PICK_DRAG = "PickDrag",
		PUSH_AWAY = "PushAway",
		PULL_TOWARDS = "PushTowards",
		PUSH_LEFT = "PushLeft",
		PUSH_RIGHT = "PushRight",
		GOD_MODE = "GodMode",
		PUSH_UP = "PushUp",
		PUSH_DOWN = "PushDown",
		MOUSE_MOVEMENT = "MouseMovement";
	
	public static String[] getMappingNames()	{
		return new String[]{PICK, PICK_DRAG, PUSH_AWAY, PULL_TOWARDS, PUSH_LEFT, PUSH_RIGHT, GOD_MODE, 
				PUSH_UP, PUSH_DOWN, MOUSE_MOVEMENT};
	}
}
