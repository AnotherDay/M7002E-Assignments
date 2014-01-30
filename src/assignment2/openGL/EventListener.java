package assignment2.openGL;

import assignment2.shapes.*;

import java.util.Iterator;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import assignment2.globals.ObjectContainer;

public class EventListener implements GLEventListener {

	private GLU glu; 
	private PolygonDrawer polygonDrawer = new PolygonDrawer();
	private ObjectContainer objectContainer = ObjectContainer.getInstance();
	
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		Iterator<Pyramid> pyramidIterator = objectContainer.getPyramidIterator();
		Iterator<Square> squareIterator = objectContainer.getSquareIterator();
		Iterator<Star> starIterator = objectContainer.getStarIterator();
		
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
		
//		polygonDrawer.drawPyramid(gl, -2.3f, -1.0f, -7.0f, 2.0f, 2.0f);
//		polygonDrawer.drawSquare(gl, 0, 0, -8.0f, 1.0f);
//		polygonDrawer.draw2dStar(gl, 2.0f, 0, -6.0f, 1.0f);
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
