package assignment2.globals;

import java.util.ArrayList;
import java.util.Iterator;

import assignment2.openGL.shapes.GLEntity;
import assignment2.openGL.shapes.GLPyramidEntity;
import assignment2.openGL.shapes.GLSquareEntity;
import assignment2.openGL.shapes.GLStarEntity;

/**
 * Singleton object which contains all the shape objects.
 * 
 * @author David Eriksson
 *
 */
public class ObjectContainer {
	private static final ObjectContainer INSTANCE = new ObjectContainer();
	
	private ArrayList<GLEntity> glEntityList = new ArrayList<GLEntity>();
	
	private int pyramidIdCounter = 100;
	private int squareIdCounter = 200;
	private int starIdCounter = 300;
	
	private ObjectContainer(){};
	
	public static ObjectContainer getInstance()	{
		return INSTANCE;
	}
	
	public void addPyramid(GLPyramidEntity pyramid)	{
		pyramid.setId(pyramidIdCounter);
		pyramidIdCounter++;
		glEntityList.add(pyramid);
	}
	
	public void addSquare(GLSquareEntity square)	{
		square.setId(squareIdCounter);
		squareIdCounter++;
		glEntityList.add(square);
	}
	
	public void addStar(GLStarEntity star)	{
		star.setId(starIdCounter);
		starIdCounter++;
		glEntityList.add(star);
	}
	
	public ArrayList<GLEntity> getGLEntityList()	{
		return glEntityList;
	}
	
	public void clearObjectContainer()	{
		glEntityList.clear();
		pyramidIdCounter = 100;
		squareIdCounter = 200;
		starIdCounter = 300;
	}
}
