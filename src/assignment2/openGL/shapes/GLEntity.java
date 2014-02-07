package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public abstract class GLEntity  {
	protected float xPos, yPos, zPos;
	protected int id;
	protected String entityType = "Undefined";
	
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
	
	public void setXPos(float xPos)	{
		this.xPos = xPos;
	}
	
	public void setYPos(float yPos)	{
		this.yPos = yPos;
	}
	
	public void setZPos(float zPos)	{
		this.zPos = zPos;
	}
	
	public void printInfo()	{
		System.out.println("----------------------------------------------");
		System.out.println("Id = " + id);
		System.out.println("Type = " + entityType);
		System.out.println("X = " + xPos + ", Y = " + yPos + ", Z = " + zPos);
		System.out.println("----------------------------------------------");
	}
	
	public abstract void draw(GL2 gl, GLU glu);
	
}
