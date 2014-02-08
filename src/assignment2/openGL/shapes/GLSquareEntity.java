package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.openGL.GLEntity;

public class GLSquareEntity extends GLEntity {
	private float edgeLength;
	
	public GLSquareEntity(float xPos, float yPos, float zPos, float edgeLength)	{
		super(xPos, yPos, zPos);
		this.edgeLength = edgeLength;
		this.entityType = "Square";
		this.setAmbientRGBA(0, 0.0f, 1.0f, 1.0f);
	}
	
	public float getEdgeLength()	{
		return edgeLength;
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		if(edgeLength == 0) throw new IllegalArgumentException("Radius cannot be zero");
		else	{
			gl.glPushName(id);
			gl.glPushMatrix();
			gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
				gl.glTranslatef(xPos, yPos, zPos);
				drawMaterial(gl, GL2.GL_FRONT_AND_BACK, 0);
				gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_FILL);
				gl.glBegin(GL2.GL_QUADS);
					float halfEdgeLength = edgeLength/2;
					gl.glVertex3f(-halfEdgeLength, -halfEdgeLength, 0); //Bottom left vertex
					gl.glVertex3f(halfEdgeLength, -halfEdgeLength, 0); //Bottom right vertex
					gl.glVertex3f(halfEdgeLength, halfEdgeLength, 0); //Top right vertex
					gl.glVertex3f(-halfEdgeLength, halfEdgeLength, 0); //Top left vertex
				gl.glEnd();
			gl.glPopAttrib();
			gl.glPopMatrix();
		}
	}

	@Override
	public void printShapeSpecifics() {
		System.out.println("Edge Length = " + edgeLength);
	}
}
