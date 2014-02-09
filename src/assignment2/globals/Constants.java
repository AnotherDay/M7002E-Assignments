package assignment2.globals;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;

public class Constants {
	
	//Shape string constants
	public final static String 
			PYRAMID_POLYGON = "Pyramid", 
			SQUARE_POLYGON = "Square",
			STAR_POLYGON = "Star",
			SPHERE_POLYGON = "Sphere",
			CUBE_POLYGON = "Cube",
			LIGHT_SOURCE = "Light source";
	
	//Color string constants
	public final static String 
			COLOR_RED = "Red",
			COLOR_BLUE = "Blue",
			COLOR_GREEN = "Green",
			COLOR_YELLOW = " Yellow", 
			COLOR_WHITE = "White",
			COLOR_DEFAULT = "Default";
	
	//Other constants
	public final static Color backgroundColor = new Color(72, 71, 66);
	public final static Color textColor = Color.WHITE;
	
	public static LinkedList<String> getAllColorConstants()	{
		LinkedList<String> colorConstants = new LinkedList<String>();
		colorConstants.addAll(Arrays.asList(COLOR_RED, COLOR_BLUE, COLOR_GREEN, COLOR_YELLOW, COLOR_WHITE));
		return colorConstants;
	}
	
	public static LinkedList<String> getObjectList()	{
		LinkedList<String> objectList = new LinkedList<String>();
		objectList.addAll(Arrays.asList(PYRAMID_POLYGON, SQUARE_POLYGON, SPHERE_POLYGON, CUBE_POLYGON, STAR_POLYGON, LIGHT_SOURCE));
		return objectList;
	}
}
