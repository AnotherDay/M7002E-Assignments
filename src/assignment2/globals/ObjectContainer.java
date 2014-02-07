package assignment2.globals;

import java.util.ArrayList;

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
	
	private int objectIdCounter; //Matches the objects index value
	
	private ObjectContainer(){
		objectIdCounter = 0;
	};
	
	public static ObjectContainer getInstance()	{
		return INSTANCE;
	}
	
	public void addPyramid(GLPyramidEntity pyramid)	{
		pyramid.setId(objectIdCounter);
		objectIdCounter++;
		glEntityList.add(pyramid);
	}
	
	public void addSquare(GLSquareEntity square)	{
		square.setId(objectIdCounter);
		objectIdCounter++;
		glEntityList.add(square);
	}
	
	public void addStar(GLStarEntity star)	{
		star.setId(objectIdCounter);
		objectIdCounter++;
		glEntityList.add(star);
	}
	
	public GLEntity getGLEntitiy(int id)		{
		return glEntityList.get(id);
	}
	
	public ArrayList<GLEntity> getGLEntityList()	{
		return glEntityList;
	}
	
	public void clearObjectContainer()	{
		glEntityList.clear();
		objectIdCounter = 0;
	}
}
