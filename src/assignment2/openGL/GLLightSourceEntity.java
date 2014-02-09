package assignment2.openGL;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class GLLightSourceEntity extends GLEntity {
	
	private int lightSourceId;
	
	public GLLightSourceEntity(float xPos, float yPos, float zPos)	{
		super(xPos, yPos, zPos);
		ambientRGBA = new float[]{1,1,1,1};
		diffuseRGBA = new float[]{1,1,1,1};
		specularRGBA = new float[]{1,1,1,1};
		this.entityType = "Light Source";
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		int lightSourceId = getLightSourceId();
		gl.glEnable( lightSourceId );
		gl.glLightfv(lightSourceId, GL2.GL_POSITION, new float[]{xPos, yPos, zPos, 0}, 0);
		gl.glLightfv( lightSourceId, GL2.GL_AMBIENT, ambientRGBA, 0 );
		gl.glLightfv( lightSourceId, GL2.GL_DIFFUSE, diffuseRGBA, 0 );
		gl.glLightfv( lightSourceId, GL2.GL_SPECULAR, specularRGBA, 0 );
	}

	@Override
	public void printShapeSpecifics() {}
	
	public void setLightSourceId(int lightSourceId)	{
		this.lightSourceId = lightSourceId;
	}
	
	public void disableLight(GL2 gl)	{
		int lightSourceId = getLightSourceId();
		gl.glDisable(lightSourceId);
	}
	
	private int getLightSourceId() throws IllegalArgumentException	{
		switch(lightSourceId)	{
		case 0:
			return GL2.GL_LIGHT0;
		case 1:
			return GL2.GL_LIGHT1;
		case 2:
			return GL2.GL_LIGHT2;
		case 3:
			return GL2.GL_LIGHT3;
		case 4:
			return GL2.GL_LIGHT4;
		case 5:
			return GL2.GL_LIGHT5;
		case 6:
			return GL2.GL_LIGHT6;
		case 7:
			return GL2.GL_LIGHT7;
		}
		throw new IllegalArgumentException("Can only have 7 light sources");
	}

	@Override
	public void resizeObject(float scaleFactor) {}

	@Override
	public void innerDrawMethod(GL2 gl, GLU glu) {}
}
