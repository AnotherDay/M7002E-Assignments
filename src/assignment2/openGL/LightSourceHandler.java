package assignment2.openGL;

import java.util.ArrayList;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.globals.ObjectContainer;

public class LightSourceHandler {

	private ArrayList<GLLightSourceEntity> lightSourceList;
	private ArrayList<Integer> idList;
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	
	public LightSourceHandler(GL2 gl, GLU glu)	{
		lightSourceList = theObjectContainer.getLightSourceList();
		idList = new ArrayList<Integer>();
		for(GLLightSourceEntity lightSource : lightSourceList)	{
			idList.add(lightSource.getId());
			lightSource.draw(gl, glu);
		}
	}
	
	public void updateLightSources(GL2 gl, GLU glu)	{
		if(lightSourceList.size() != 0)	{
			ArrayList<GLLightSourceEntity> newLightSourceList = theObjectContainer.getLightSourceList();
			ArrayList<Integer> newIdList = new ArrayList<Integer>();
			for(GLLightSourceEntity lightSource : newLightSourceList)	{
				newIdList.add(lightSource.getId());
			}
			
			ArrayList<Integer> lightsToAdd = new ArrayList<Integer>(newIdList);
			lightsToAdd.removeAll(idList);
			
			ArrayList<Integer> lightsToRemove = new ArrayList<Integer>(idList);
			lightsToRemove.removeAll(newIdList);
			
			for(int id : lightsToAdd)	{
				for(GLLightSourceEntity lightSource : newLightSourceList)	{
					if(lightSource.getId() == id)	{
						lightSource.draw(gl, glu);
						lightSourceList.add(lightSource);
					}
				}
			}
			
			for(int id : lightsToRemove)	{
				for(GLLightSourceEntity lightSource : lightSourceList)	{
					if(lightSource.getId() == id)	{
						lightSource.disableLight(gl);
						lightSourceList.remove(lightSource);
					}
				}
			}
		}
	}
	
	public void removeAllLightSources(GL2 gl)	{
		for(GLLightSourceEntity lightSource : lightSourceList)	{
			lightSource.disableLight(gl);
		}
		lightSourceList.clear();
		theObjectContainer.clearLightSources();
	}
}
