package assignment2.openGL.shapes;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import assignment2.globals.Constants;
import assignment2.openGL.GLEntity;

import com.jogamp.opengl.util.gl2.GLUT;

public class GLCubeEntity extends GLEntity {

	private GLUT glut = new GLUT();
	private float radius;
	
	public GLCubeEntity(float xPos, float yPos, float zPos, float radius) {
		super(xPos, yPos, zPos);
		this.radius = radius;
		this.ambientRGBA = new float[]{0, 0, 0.2f, 1.0f};
		this.diffuseRGBA = new float[]{ 0.1f, 0.5f, 0.8f, 1.0f };
		this.entityType = Constants.CUBE_POLYGON;
	}

	@Override
	public void innerDrawMethod(GL2 gl, GLU glu) {
		gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
		drawMaterial(gl, GL2.GL_FRONT_AND_BACK, 0);
		glut.glutSolidCube(radius);
	}

	@Override
	public void printShapeSpecifics() {
		System.out.println("Radius = " + radius); 
	}

	@Override
	public void resizeObject(float scaleFactor) {
		// TODO Auto-generated method stub

	}

}
