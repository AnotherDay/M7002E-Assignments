package assignment2.globals;

import java.util.ArrayList;

import assignment2.openGL.GLEntity;
import assignment2.openGL.GLLightSourceEntity;

/**
 * Singleton object which contains all the GLEntity objects.
 * 
 * @author David Eriksson
 *
 */
public class ObjectContainer {
	private static final ObjectContainer INSTANCE = new ObjectContainer();
	
	private ArrayList<GLEntity> glEntityList = new ArrayList<GLEntity>();
	private GLLightSourceEntity[] lightSourceArray = new GLLightSourceEntity[8];
	private boolean[] takenLightSlots = new boolean[8];
	
	private int objectIdCounter; 
	private int lightSourceCounter;
	
	private ObjectContainer(){
		objectIdCounter = 0;
		lightSourceCounter = 0;
	};
	
	public static ObjectContainer getInstance()	{
		return INSTANCE;
	}
	
	public void addGLEntity(GLEntity glEntity)	{
		setId(glEntity);
		glEntityList.add(glEntity);
	}
	
	public GLEntity getGLEntitiy(int id) throws RuntimeException	{
		for(GLEntity glEntity : glEntityList)	{
			if(glEntity.getId() == id)		{
				return glEntity;
			}
		}
		throw new RuntimeException("Object not in object container");
	}
	
	public ArrayList<GLEntity> getGLEntityList()	{
		return glEntityList;
	}
	
	public void deleteGLEntity(int id)	{
		for(GLEntity glEntity : glEntityList)	{
			if(glEntity.getId() == id)	{
				glEntityList.remove(glEntity);
				break;
			}
		}
	}
	
	public void addLightSource(GLLightSourceEntity glLightSourceEntity) throws RuntimeException	{
		glLightSourceEntity.setId(lightSourceCounter);
		for(int i = 0; i < takenLightSlots.length; i++)		{
			if(takenLightSlots[i] == false)	{
				glLightSourceEntity.setLightSourceId(i);
				lightSourceArray[i] = glLightSourceEntity;
				takenLightSlots[i] = true;
				break;
			}
			else if(i == takenLightSlots.length-1 && takenLightSlots[i] == true)	{
				throw new RuntimeException("Can only have 8 light sources");
			}
		}
	}
	
	public ArrayList<GLLightSourceEntity> getLightSourceList()	{
		ArrayList<GLLightSourceEntity> lightSourceList = new ArrayList<GLLightSourceEntity>();
		for(int i = 0; i < takenLightSlots.length; i++)	{
			if(takenLightSlots[i] == true)		{
				lightSourceList.add(lightSourceArray[i]);
			}
		}
		return lightSourceList;
	}
	
	public void clearLightSources()	{
		lightSourceArray = new GLLightSourceEntity[8];
		takenLightSlots = new boolean[8];
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
