package assignment2.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public abstract class GLEntity  {
	protected float xPos, yPos, zPos;
	protected int id;
	
	public GLEntity(float xPos, float yPos, float zPos)	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}
	
	public void setId(int id)	{
		this.id = id;
	}
	
	public int getId()	{
		return id;
	}
	
	public float getXPos() 	{
		return xPos;
	}
	
	public float getYPos()	{
		return yPos;
	}
	
	public float getZPos()	{
		return zPos;
	}
	
	public abstract void draw(GL2 gl, GLU glu);
	
}
