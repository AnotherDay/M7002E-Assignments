package Assignment1;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

public class EventListener implements GLEventListener {

	private GLU glu; 
	private ShapeDrawer shapeDrawer = new ShapeDrawer();
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		shapeDrawer.drawPyramid(gl, -2.3f, 0.0f, -7.0f);
		shapeDrawer.drawSquare(gl, 0, 0, -8.0f, 1.0f);
		shapeDrawer.draw2dStar(gl, 2.0f, 0, -6.0f, 1.0f);
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		glu = new GLU();
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
				 
		if (height == 0) height = 1;   // prevent divide by zero
		float aspect = (float)width / height;
		 
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
	}

}
