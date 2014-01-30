package assignment2.globals;

import java.util.ArrayList;
import java.util.Iterator;

import assignment2.shapes.Pyramid;
import assignment2.shapes.Square;
import assignment2.shapes.Star;

/**
 * Singleton object which contains all the shape objects.
 * 
 * @author David Eriksson
 *
 */
public class ObjectContainer {
	private static final ObjectContainer INSTANCE = new ObjectContainer();
	private ArrayList<Pyramid> pyramidList = new ArrayList<Pyramid>();
	private ArrayList<Square> squareList = new ArrayList<Square>();
	private ArrayList<Star> starList = new ArrayList<Star>();
	
	private ObjectContainer(){};
	
	public static ObjectContainer getInstance()	{
		return INSTANCE;
	}
	
	public void addPyramid(Pyramid pyramid)	{
		pyramidList.add(pyramid);
	}
	
	public void addSquare(Square square)	{
		squareList.add(square);
	}
	
	public void addStar(Star star)	{
		starList.add(star);
	}
	
	public Iterator<Pyramid> getPyramidIterator()	{
		return pyramidList.iterator();
	}
	
	public Iterator<Square> getSquareIterator()	{
		return squareList.iterator();
	}
	
	public Iterator<Star> getStarIterator()	{
		return starList.iterator();
	}
}
