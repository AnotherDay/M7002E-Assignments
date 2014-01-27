package assignment1;

import javax.media.opengl.GL2;


public class ShapeDrawer {
	public void drawPyramid(GL2 gl, float xPos, float yPos, float zPos)	{
		gl.glPushMatrix();
		gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
			gl.glTranslatef(xPos, yPos, zPos);
//			gl.glRotatef(20.0f, 0.0f, 0.0f, 0.0f); 
			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
			gl.glColor3d(255, 0, 0);
			gl.glBegin(GL2.GL_TRIANGLES); 
				// Font-face triangle
				gl.glVertex3f(0.0f, 1.0f, 0.0f);
				gl.glVertex3f(-1.0f, -1.0f, 1.0f);
				gl.glVertex3f(1.0f, -1.0f, 1.0f);
				 
				// Right-face triangle
				gl.glVertex3f(0.0f, 1.0f, 0.0f);
				gl.glVertex3f(1.0f, -1.0f, 1.0f);
				gl.glVertex3f(1.0f, -1.0f, -1.0f);
				 
				// Back-face triangle
				gl.glVertex3f(0.0f, 1.0f, 0.0f);
				gl.glVertex3f(1.0f, -1.0f, -1.0f);
				gl.glVertex3f(-1.0f, -1.0f, -1.0f);
				 
				// Left-face triangle
				gl.glVertex3f(0.0f, 1.0f, 0.0f);
				gl.glVertex3f(-1.0f, -1.0f, -1.0f);
				gl.glVertex3f(-1.0f, -1.0f, 1.0f);
			gl.glPopAttrib();
			gl.glEnd(); 
		gl.glPopMatrix();
	}
	
	public void drawSquare(GL2 gl, float xPos, float yPos, float zPos, float width)	{
		if(width == 0) throw new IllegalArgumentException("Radius cannot be zero");
		else	{
			gl.glPushMatrix();
			gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
			gl.glTranslatef(xPos, yPos, zPos);
			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
			gl.glColor3d(0, 0, 255);
			gl.glBegin(GL2.GL_QUADS);
			gl.glVertex3f(-width, -width, 0); //Bottom left vertex
			gl.glVertex3f(width, -width, 0); //Bottom right vertex
			gl.glVertex3f(width, width, 0); //Top right vertex
			gl.glVertex3f(-width, width, 0); //Top left vertex
			gl.glEnd();
			gl.glPopAttrib();
			gl.glPopMatrix();
		}
	}
	
	public void draw2dStar(GL2 gl, float xPos, float yPos, float zPos, float radius)	{
		if(radius == 0)	throw new IllegalArgumentException("Radius cannot be zero");
		else	{
			float innerRadius = radius/4;
			
			gl.glPushMatrix();
			gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
				gl.glTranslatef(xPos, yPos, zPos);
				gl.glColor3d(0, 255, 0);
				//Starting from the far right vertex and then moving up in a counter clockwise direction
				gl.glBegin(GL2.GL_LINE_LOOP);  
					gl.glVertex3f(radius, 0, 0);
					gl.glVertex3f(innerRadius, innerRadius, 0);
					gl.glVertex3f(0, radius, 0);
					gl.glVertex3f(-innerRadius, innerRadius, 0);
					gl.glVertex3f(-radius, 0, 0);
					gl.glVertex3f(-innerRadius, -innerRadius, 0);
					gl.glVertex3f(0, -radius, 0);
					gl.glVertex3f(innerRadius, -innerRadius, 0);
				gl.glEnd();
			gl.glPopAttrib();
			gl.glPopMatrix();
		}
	}
}
