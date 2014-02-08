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
	
	private int objectIdCounter; //Matches the objects index value
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
	
	public void addLightSource(GLLightSourceEntity glLightSourceEntity) throws RuntimeException	{
		glLightSourceEntity.setId(lightSourceCounter);
		for(int i = 0; i < takenLightSlots.length; i++)		{
			if(takenLightSlots[i] == false)	{
				glLightSourceEntity.setLightSourceId(i);
				lightSourceArray[i] = glLightSourceEntity;
			}
			else if(i == takenLightSlots.length-1 && takenLightSlots[i] == true)	{
				throw new RuntimeException("Can only have 8 light sources");
			}
		}
	}
	
	public ArrayList<GLLightSourceEntity> getLightSourceList()	{
		ArrayList<GLLightSourceEntity> lightSourceList = new ArrayList<GLLightSourceEntity>();
		for(GLLightSourceEntity lightSource : lightSourceArray)	{
			lightSourceList.add(lightSource);
		}
		return lightSourceList;
	}
	
	public void clearLightSources()	{
		lightSourceArray = new GLLightSourceEntity[8];
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
