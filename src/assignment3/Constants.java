package assignment3;

public class Constants {

	public final static String
		PICK = "Pick",
		PUSH_AWAY = "PushUp",
		PULL_TOWARDS = "PushDown",
		PUSH_LEFT = "PushLeft",
		PUSH_RIGHT = "PushRight";
	
	public static String[] getMappingNames()	{
		return new String[]{PICK, PUSH_AWAY, PULL_TOWARDS, PUSH_LEFT, PUSH_RIGHT};
	}
}
