package assignment4;

public class Constants {

	public final static String
		PICK = "Pick",
		PUSH_AWAY = "PushAway",
		PULL_TOWARDS = "PushTowards",
		PUSH_LEFT = "PushLeft",
		PUSH_RIGHT = "PushRight",
		GOD_MODE = "GodMode",
		PUSH_UP = "PushUp",
		PUSH_DOWN = "PushDown";
	
	public static String[] getMappingNames()	{
		return new String[]{PICK, PUSH_AWAY, PULL_TOWARDS, PUSH_LEFT, PUSH_RIGHT, GOD_MODE, PUSH_UP, PUSH_DOWN};
	}
}
