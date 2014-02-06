package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

public class GLSquareEntity extends GLEntity {
	private float edgeLength;
	
	public GLSquareEntity(float xPos, float yPos, float zPos, float edgeLength)	{
		super(xPos, yPos, zPos);
		this.edgeLength = edgeLength;
	}
	
	public float getEdgeLength()	{
		return edgeLength;
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		if(edgeLength == 0) throw new IllegalArgumentException("Radius cannot be zero");
		else	{
			float halfEdgeLength = edgeLength/2;
			gl.glPushName(id);
			gl.glPushMatrix();
			gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
				gl.glTranslatef(xPos, yPos, zPos);
				gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_FILL);
				gl.glColor3d(0, 0, 255);
				gl.glBegin(GL2.GL_QUADS);
					gl.glVertex3f(-halfEdgeLength, -halfEdgeLength, 0); //Bottom left vertex
					gl.glVertex3f(halfEdgeLength, -halfEdgeLength, 0); //Bottom right vertex
					gl.glVertex3f(halfEdgeLength, halfEdgeLength, 0); //Top right vertex
					gl.glVertex3f(-halfEdgeLength, halfEdgeLength, 0); //Top left vertex
				gl.glEnd();
			gl.glPopAttrib();
			gl.glPopMatrix();
		}
	}
}
