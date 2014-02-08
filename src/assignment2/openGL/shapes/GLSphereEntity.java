package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.openGL.GLEntity;

import com.jogamp.opengl.util.gl2.GLUT;

public class GLSphereEntity extends GLEntity {
	
	private float radius;
	private GLUT glut = new GLUT(); 
	
	public GLSphereEntity(float xPos, float yPos, float zPos, float radius)	{
		super(xPos, yPos, zPos);
		this.radius = radius;
		this.entityType = "Sphere";
		this.ambientRGBA = new float[]{0, 0, 0.2f, 1.0f};
		this.diffuseRGBA = new float[]{ 0.1f, 0.5f, 0.8f, 1.0f };
	}

	@Override
	public void draw(GL2 gl, GLU glu) {
		gl.glPushName(id);
		gl.glPushMatrix();
		gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS);
			gl.glTranslatef(xPos, yPos, zPos);
			gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
			drawMaterial(gl, GL2.GL_FRONT_AND_BACK, 0);
			glut.glutSolidSphere(radius, 20, 20);
		gl.glEnd(); 
		gl.glPopMatrix();
	}

	@Override
	public void printShapeSpecifics() {
		System.out.println("Radius = " + radius);
	}
}
