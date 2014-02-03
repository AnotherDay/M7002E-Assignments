package assignment2.openGL;

import assignment2.shapes.*;

import java.util.Iterator;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import assignment2.globals.ObjectContainer;

public class EventListener implements GLEventListener {

	private PolygonDrawer polygonDrawer = new PolygonDrawer();
	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	GLU glu = new GLU();
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
//		polygonDrawer.drawPyramid(gl, -2.3f, -1.0f, -7.0f, 2.0f, 2.0f);
//		polygonDrawer.drawSquare(gl, 0, 0, -8.0f, 1.0f);
//		polygonDrawer.draw2dStar(gl, 2.0f, 0, -6.0f, 1.0f);

		Iterator<Pyramid> pyramidIterator = theObjectContainer.getPyramidIterator();
		Iterator<Square> squareIterator = theObjectContainer.getSquareIterator();
		Iterator<Star> starIterator = theObjectContainer.getStarIterator();
		
		Pyramid p;
		while(pyramidIterator.hasNext())	{
			//TODO: handle illegalArgumentException
			p = pyramidIterator.next();
			polygonDrawer.drawPyramid(gl, p.getXPos(), p.getYPos(), p.getZPos(), p.getBaseWidth(), p.getHeight());
		}
		
		Square sq;
		while(squareIterator.hasNext())	{
			sq = squareIterator.next();
			polygonDrawer.drawSquare(gl, sq.getXPos(), sq.getYPos(), sq.getZPos(), sq.getEdgeLength());
		}
		
		Star st;
		while(starIterator.hasNext())	{
			st = starIterator.next();
			polygonDrawer.draw2dStar(gl, st.getXPos(), st.getYPos(), st.getZPos(), st.getSpan());
		}
		
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrtho(-1.0, 1.0, -1.0, 1.0, -1.0, 1.0);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
//		GL2 gl = drawable.getGL().getGL2();
//				 
//		if (height == 0) height = 1;   // prevent divide by zero
//		float aspect = (float)width / height;
//		 
//		gl.glLoadIdentity();
//		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar
		
		GL2 gl = drawable.getGL().getGL2();
        if (height <= 0) {
            height = 1;
        }
        float h = (float) width / (float) height;
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(50.0f, h, 1.0, 1000.0);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

}
