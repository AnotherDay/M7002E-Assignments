package assignment2.globals;

import java.util.ArrayList;

import assignment2.openGL.shapes.GLEntity;

/**
 * Singleton object which contains all the GLEntity objects.
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
	
	public void addGLEntity(GLEntity glEntity)	{
		setId(glEntity);
		glEntityList.add(glEntity);
	}
	
	public GLEntity getGLEntitiy(int id)		{
		return glEntityList.get(id);
	}
	
	public ArrayList<GLEntity> getGLEntityList()	{
		return glEntityList;
	}
	
	public void deleteGLEntity(int id)	{
		glEntityList.remove(id);
		if(id <= glEntityList.size())	{
			for(int i = id; i <= glEntityList.size(); i++)	{
				glEntityList.get(i).setId(glEntityList.get(i).getId()-1);
			}
		}
	}
	
	public void clearObjectContainer()	{
		glEntityList.clear();
		objectIdCounter = 0;
	}
	
	private void setId(GLEntity glEntity)	{
		glEntity.setId(objectIdCounter);
		objectIdCounter++;
	}
}
