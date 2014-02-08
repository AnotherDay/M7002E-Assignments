package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.openGL.GLEntity;

public class GLStarEntity extends GLEntity {
	
	private float span;
	
	public GLStarEntity(float xPos, float yPos, float zPos, float span)	{
		super(xPos, yPos, zPos);
		this.span = span;
		this.entityType = "Star";
		this.setAmbientRGBA(0, 1.0f, 0.0f, 1.0f);
	}
	
	public float getSpan()	{
		return span;
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		if(span == 0)	throw new IllegalArgumentException("Span cannot be zero");
		else	{
			float innerRadius = span/4;
			gl.glPushName(id);
			gl.glPushMatrix();
			gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
				gl.glTranslatef(xPos, yPos, zPos);
				drawMaterial(gl, GL2.GL_FRONT_AND_BACK, 0);
//				gl.glPolygonMode(GL2.GL_FRONT, GL2.GL_FILL);
				//Starting from the far right vertex and then moving up in a counter clockwise direction
				gl.glBegin(GL2.GL_LINE_LOOP);  
					gl.glVertex3f(span, 0, 0);
					gl.glVertex3f(innerRadius, innerRadius, 0);
					gl.glVertex3f(0, span, 0);
					gl.glVertex3f(-innerRadius, innerRadius, 0);
					gl.glVertex3f(-span, 0, 0);
					gl.glVertex3f(-innerRadius, -innerRadius, 0);
					gl.glVertex3f(0, -span, 0);
					gl.glVertex3f(innerRadius, -innerRadius, 0);
				gl.glEnd();
			gl.glPopAttrib();
			gl.glPopMatrix();
		}
	}

	@Override
	public void printShapeSpecifics() {
		System.out.println("Span = " + span);
	}

	@Override
	public void resizeObject(float scaleFactor) {
		// TODO Auto-generated method stub
		
	}
}
