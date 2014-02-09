package assignment2.openGL;

import java.nio.IntBuffer;
import java.util.LinkedList;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import jogamp.opengl.glu.mipmap.ScaleInternal;
import assignment2.globals.ObjectContainer;

import com.jogamp.common.nio.Buffers;

public class EventListener implements GLEventListener {

	private ObjectContainer theObjectContainer = ObjectContainer.getInstance();
	private LightSourceHandler lightSourceHandler;
	private GLU glu = new GLU();
	private int mouseX, mouseY;
	private int lastSelectedObject = -1;
	
	private static final int UPDATE = 1, SELECT = 2, REMOVE_ALL_LIGHT_SOURCES = 3;
    private int cmd = UPDATE;
    private static final float orthoLeft = 0, orthoRight = 2, orthoBotton = 0, orthoTop = 1; 
    
    private boolean deleteObject = false, resizeObject = false;
    private float scalingFactor = 1;

    final float[] colorBlack = {0.0f,0.0f,0.0f,1.0f},
    		colorWhite = {1.0f,1.0f,1.0f,1.0f},
    		colorRed= {1.0f,0.0f,0.0f,1.0f};
    
    /**
     * The following display method is based on the Picking.java demo file.
     * Source: http://jogamp.org/deployment/jogamp-current/archive/.
     */
	@Override
	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		
		switch (cmd) {
		case UPDATE:
			lightSourceHandler.updateLightSources(gl, glu);
			drawScene(gl);
			break;
		case REMOVE_ALL_LIGHT_SOURCES:
			lightSourceHandler.removeAllLightSources(gl);
			drawScene(gl);
			cmd = UPDATE;
			break;
		case SELECT:
			double x = (double) mouseX, y = (double) mouseY;
			int buffsize = 512;
			int[] viewPort = new int[4];
			IntBuffer selectBuffer = Buffers.newDirectIntBuffer(buffsize);
			gl.glGetIntegerv(GL2.GL_VIEWPORT, viewPort, 0);
			gl.glSelectBuffer(buffsize, selectBuffer);
			gl.glRenderMode(GL2.GL_SELECT);
			gl.glInitNames();
			gl.glMatrixMode(GL2.GL_PROJECTION);
			gl.glPushMatrix();
				gl.glLoadIdentity();
				glu.gluPickMatrix(x, (double) (viewPort[3] - y), 5.0d, 5.0d, viewPort, 0);
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
	
	/**
	 * The following method is base on the processHits() method in the Picking.java demo.
	 * Source: http://jogamp.org/deployment/jogamp-current/archive/. 
	 * 
	 * @param hits, number of objects hit.
	 * @param buffer, the select buffer.
	 */
	private void processHits(int hits, IntBuffer buffer)
    {
      int offset = 0;
      int names;
      
      LinkedList<Integer> objectsHit = new LinkedList<Integer>();
      for (int i=0;i<hits;i++)
        {
          names = buffer.get(offset); 
          offset+=3;
          for (int j=0;j<names;j++)
            {
              if (j==(names-1))	{
            	objectsHit.add(buffer.get(offset));
              }
              offset++;
            }
        }
      
      if(objectsHit.size() != 0)	{
    	  GLEntity glEntity = theObjectContainer.getGLEntitiy(objectsHit.pop());
    	  GLEntity glEntityCompare;
    	  while(!objectsHit.isEmpty())	{
    		  glEntityCompare = theObjectContainer.getGLEntitiy(objectsHit.pop());
    		  if(glEntity.getZPos() < glEntityCompare.getZPos()){
    			  glEntity = glEntityCompare;
    		  }
    	  }
    	  lastSelectedObject = glEntity.getId();
    	  
    	  if(deleteObject)	{
    		  theObjectContainer.deleteGLEntity(lastSelectedObject);
    		  lastSelectedObject = -1;
    	  }
    	  else if(resizeObject)	{
    		  theObjectContainer.getGLEntitiy(lastSelectedObject).resizeObject(scalingFactor);
    		  System.out.println("Resized " + lastSelectedObject);
    	  }
      }
    }
	
	/**
	 * Changes the coordinates of the last objects selected
	 */
	public void changeSelectedObjectPosition(float newX, float newY)		{
		GLEntity glEntity = theObjectContainer.getGLEntitiy(lastSelectedObject);
		glEntity.setXPos(newX);
		glEntity.setYPos(newY);
		System.out.println("Moved " + glEntity.getId() + " to X = " + newX + ", Y = " + newY);
	}
	
	private void drawScene(GL2 gl)	{
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		for(GLEntity glEntity : theObjectContainer.getGLEntityList())	{
			glEntity.draw(gl, glu);
		}
	}
	
	public void registerMouseClick(int x, int y)	{
		mouseX = x;
		mouseY = y;
		cmd = SELECT;
	}
	
	/**
	 * Deletes the last selected objects
	 */
	public void markObjectForDeletion(int x, int y)	{
		deleteObject = true;
		registerMouseClick(x, y);
	}
	
	public void markObjectForRescaling(int x, int y, float scalingFactor)	{
		resizeObject = true;
		this.scalingFactor = scalingFactor;
		registerMouseClick(x, y);
	}
	
	public void removeAllLightSources()	{
		cmd = REMOVE_ALL_LIGHT_SOURCES;
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {}

	@Override
	public void init(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		lightSourceHandler = new LightSourceHandler(gl, glu);
		gl.glEnable( GL2.GL_LIGHTING );
		
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LESS);
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
