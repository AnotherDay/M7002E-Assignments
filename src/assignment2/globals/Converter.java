package assignment2.globals;

import java.awt.Color;

public class Converter {
	
	public Color getColor(String colorString)	{
		switch(colorString)	{
			case Constants.COLOR_BLUE:
				return Color.RED;
			case Constants.COLOR_GREEN:
				return Color.GREEN;
			case Constants.COLOR_RED:
				return Color.RED;
			case Constants.COLOR_WHITE:
				return Color.WHITE;
			case Constants.COLOR_YELLOW:
				return Color.YELLOW;
		}
		return Color.WHITE;
	}
}
