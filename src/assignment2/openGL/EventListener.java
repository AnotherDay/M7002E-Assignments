package assignment2.openGL;

import java.nio.IntBuffer;
import java.util.LinkedList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import assignment2.globals.ObjectContainer;
import assignment2.openGL.shapes.GLEntity;

import com.jogamp.common.nio.Buffers;

public class EventListener implements GLEventListener {

	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	private GLU glu = new GLU();
	private int mouseX, mouseY;
	private LinkedList<Integer> lastSelectedObjects = new LinkedList<Integer>();
	private int[] lastViewPort = new int[4];
	
	private static final int UPDATE = 1, SELECT = 2;
    private int cmd = UPDATE;
    private static final float orthoLeft = 0, orthoRight = 2, orthoBotton = 0, orthoTop = 1; 
    
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		int buffsize = 512;
		int[] viewPort = new int[4];
		IntBuffer selectBuffer = Buffers.newDirectIntBuffer(buffsize);
		gl.glGetIntegerv(GL2.GL_VIEWPORT, viewPort, 0);
		lastViewPort = viewPort;
		
		switch (cmd) {
		case UPDATE:
			drawScene(gl);
			break;
		case SELECT:
			double x = (double) mouseX, y = (double) mouseY;
			gl.glSelectBuffer(buffsize, selectBuffer);
			gl.glRenderMode(GL2.GL_SELECT);
			gl.glInitNames();
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glPushMatrix();
				gl.glLoadIdentity();
				glu.gluPickMatrix(x, (double) (viewPort[3] - y), 5.0d, 5.0d, viewPort, 0);
				System.out.println("X-Coordiante = " + x + ", Y-Coordinate = " + (double) (viewPort[3] - y));
				glu.gluOrtho2D(orthoLeft, orthoRight, orthoBotton, orthoTop);
				drawScene(gl);
				gl.glFlush();
				gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glPopMatrix();
			gl.glFlush();
			int hits;
			hits = gl.glRenderMode(GL2.GL_RENDER);
			cmd = UPDATE;
			processHits(hits, selectBuffer);
			break;
		}
	}
	
	public void processHits(int hits, IntBuffer buffer)
    {
		lastSelectedObjects.clear();
      System.out.println("---------------------------------");
      System.out.println(" HITS: " + hits);
      int offset = 0;
      int names;
      float z1, z2;
      for (int i=0;i<hits;i++)
        {
          System.out.println("- - - - - - - - - - - -");
          System.out.println(" hit: " + (i + 1));
          names = buffer.get(offset); offset++;
          z1 = (float) (buffer.get(offset)& 0xffffffffL) / 0x7fffffff; offset++;
          z2 = (float) (buffer.get(offset)& 0xffffffffL) / 0x7fffffff; offset++;
          System.out.println(" number of names: " + names);
          System.out.println(" z1: " + z1);
          System.out.println(" z2: " + z2);
          System.out.println(" names: ");

          for (int j=0;j<names;j++)
            {
              System.out.print("       " + buffer.get(offset)); 
              if (j==(names-1))	{
            	lastSelectedObjects.add(buffer.get(offset));
                System.out.println("<-");
              }
              else
                System.out.println();
              offset++;
            }
          System.out.println("- - - - - - - - - - - -");
        }
      System.out.println("---------------------------------");
    }
	
	/**
	 * Changes the coordinates of the last objects selected
	 */
	public void changeSelectedObjectPosition(float newX, float newY)		{
		GLEntity glEntity;
		for(int objectId : lastSelectedObjects)	{
			glEntity = theObjectContainer.getGLEntitiy(objectId);
			glEntity.setXPos(newX);
			glEntity.setYPos(newY);
			System.out.println("Moved " + glEntity.getId() + " to X = " + newX + ", Y = " + newY);
		}
	}
	
	private void drawScene(GL2 gl)	{
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		for(GLEntity glEntity : theObjectContainer.getGLEntityList())	{
			glEntity.draw(gl, glu);
		}
	}
	
	public void registerMouseClick(int x, int y, int panelHeight, int panelWidth)	{
		mouseX = x;
		mouseY = y;
		cmd = SELECT;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(orthoLeft, orthoRight, orthoBotton, orthoTop);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
	}

}
