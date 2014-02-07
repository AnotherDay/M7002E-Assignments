package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.globals.Constants;

public class GLPyramidEntity extends GLEntity {
	private float height, baseWidth;
	
	public GLPyramidEntity(float xPos, float yPos, float zPos, float height, float baseWidth)	{
		super(xPos, yPos, zPos);
		this.height = height;
		this.baseWidth = baseWidth;
		this.entityType = Constants.PYRAMID_POLYGON;
	}
	
	public float getHeight()	{
		return height;
	}
	
	public float getBaseWidth()	{
		return baseWidth;
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		if(baseWidth == 0)	throw new IllegalArgumentException("Base width cannot be zero");
		float halfWidth = baseWidth/2;
		gl.glPushName(id);
		gl.glPushMatrix();
		gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
			gl.glRotatef(30, 1, 1, 0);
			gl.glTranslatef(xPos, yPos, zPos);
			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
			if(color != null) 
				gl.glColor3d(color.getRed(), color.getGreen(), color.getBlue());
			else 
				gl.glColor3d(255, 0, 0);
			gl.glBegin(GL2.GL_TRIANGLES); 
			
			// Font-face triangle
//			gl.glVertex3f(0.0f, 1.0f, 0.0f);
//			gl.glVertex3f(-1.0f, -1.0f, 1.0f);
//			gl.glVertex3f(1.0f, -1.0f, 1.0f);
			gl.glVertex3f(0.0f, height, 0.0f);
			gl.glVertex3f(-halfWidth, 0.0f, halfWidth);
			gl.glVertex3f(halfWidth, 0.0f, halfWidth);
			
			// Right-face triangle
//			gl.glVertex3f(0.0f, 1.0f, 0.0f);
//			gl.glVertex3f(1.0f, -1.0f, 1.0f);
//			gl.glVertex3f(1.0f, -1.0f, -1.0f);
			gl.glVertex3f(0.0f, height, 0.0f);
			gl.glVertex3f(halfWidth, 0.0f, halfWidth);
			gl.glVertex3f(halfWidth, 0.0f, -halfWidth);
			
			// Back-face triangle
//			gl.glVertex3f(0.0f, 1.0f, 0.0f);
//			gl.glVertex3f(1.0f, -1.0f, -1.0f);
//			gl.glVertex3f(-1.0f, -1.0f, -1.0f);
			gl.glVertex3f(0.0f, height, 0.0f);
			gl.glVertex3f(halfWidth, 0.0f, -halfWidth);
			gl.glVertex3f(-halfWidth, 0.0f, -halfWidth);
			
			// Left-face triangle
//			gl.glVertex3f(0.0f, 1.0f, 0.0f);
//			gl.glVertex3f(-1.0f, -1.0f, -1.0f);
//			gl.glVertex3f(-1.0f, -1.0f, 1.0f);
			gl.glVertex3f(0.0f, height, 0.0f);
			gl.glVertex3f(-halfWidth, 0.0f, -halfWidth);
			gl.glVertex3f(-halfWidth, 0.0f, halfWidth);
			gl.glPopAttrib();
		gl.glEnd(); 
		gl.glPopMatrix();
	}

	@Override
	public void printShapeSpecifics() {
		System.out.println("Height = " + height);
		System.out.println("Base width = " + baseWidth);
	}
}
