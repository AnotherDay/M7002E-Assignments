package assignment2.shapes;

public abstract class Shape {
	private float xPos, yPos, zPos;
	
	public Shape(float xPos, float yPos, float zPos)	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}
	
	public float getXPos() 	{
		return xPos;
	}
	
	public float getYPos()	{
		return yPos;
	}
	
	public float getZPos()	{
		return zPos;
	}
}
