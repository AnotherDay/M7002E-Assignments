package assignment2.globals;

import java.util.ArrayList;
import java.util.Iterator;

import assignment2.shapes.GLEntity;
import assignment2.shapes.GLPyramidEntity;
import assignment2.shapes.GLSquareEntity;
import assignment2.shapes.GLStarEntity;

/**
 * Singleton object which contains all the shape objects.
 * 
 * @author David Eriksson
 *
 */
public class ObjectContainer {
	private static final ObjectContainer INSTANCE = new ObjectContainer();
	
	private ArrayList<GLEntity> glEntityList = new ArrayList<GLEntity>();
	
	private ObjectContainer(){};
	
	public static ObjectContainer getInstance()	{
		return INSTANCE;
	}
	
	public void addPyramid(GLPyramidEntity pyramid)	{
		glEntityList.add(pyramid);
	}
	
	public void addSquare(GLSquareEntity square)	{
		glEntityList.add(square);
	}
	
	public void addStar(GLStarEntity star)	{
		glEntityList.add(star);
	}
	
	public ArrayList<GLEntity> getGLEntityList()	{
		return glEntityList;
	}
	
	public void clearObjectContainer()	{
		glEntityList.clear();
	}
}
