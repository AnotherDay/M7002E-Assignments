package assignment2.openGL;

import java.util.Arrays;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.globals.Constants;

public abstract class GLEntity  {
	protected float xPos, yPos, zPos;
	protected int id;
	protected String entityType = "Undefined";
	protected float[] ambientRGBA = {0,0,0,0}, 
		specularRGBA = {0,0,0,0}, 
		diffuseRGBA = {0,0,0,0};
	
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
	
	public void setAmbientRGBA(float red, float blue, float green, float alpha)	{
		ambientRGBA = new float[]{red, blue, green, alpha};
	}
	
	public void setAmbientRGBA(float[] rgba)	{
		ambientRGBA = rgba;
	}
	
	public void setSpecularRGBA(float red, float blue, float green, float alpha)	{
		specularRGBA = new float[]{red, blue, green, alpha};
	}
	
	public void setSpecularRGBA(float[] rgba)	{
		specularRGBA = rgba;
	}
	
	public void setdDiffuseRGBA(float red, float blue, float green, float alpha)	{
		ambientRGBA = new float[]{red, blue, green, alpha};
	}
	
	public void setDiffuseRGBA(float[] rgba)	{
		ambientRGBA = rgba;
	}
	
	public void printInfo()	{
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - -");
		System.out.println("Id = " + id);
		System.out.println("Type = " + entityType);
		System.out.println("X = " + xPos + ", Y = " + yPos + ", Z = " + zPos);
		System.out.println("Ambient = " + Arrays.toString(ambientRGBA));
		System.out.println("Specular = " + Arrays.toString(specularRGBA));
		System.out.println("Diffuse = " + Arrays.toString(diffuseRGBA));
		printShapeSpecifics();
	}
	
	protected void drawMaterial(GL2 gl, int face, int paramOffset)	{
		gl.glMaterialfv( face, GL2.GL_AMBIENT, ambientRGBA, paramOffset );
		gl.glMaterialfv(face, GL2.GL_DIFFUSE, diffuseRGBA, paramOffset );
		gl.glMaterialfv(face, GL2.GL_SPECULAR, specularRGBA, paramOffset );
	}
	
	public void draw(GL2 gl, GLU glu)	{
		gl.glPushName(id);
		gl.glPushMatrix();
		gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
			if(entityType.equals(Constants.CUBE_POLYGON)) gl.glRotatef(15, 1, 1, 0);
			gl.glTranslatef(xPos, yPos, zPos);
			innerDrawMethod(gl, glu);
		gl.glPopAttrib(); 
		gl.glPopMatrix();
	}
	
	public abstract void innerDrawMethod(GL2 gl, GLU glu);
	
	public abstract void printShapeSpecifics();
	
	public abstract void resizeObject(float scaleFactor);
}
