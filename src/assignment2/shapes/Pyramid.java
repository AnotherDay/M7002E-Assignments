package assignment2.shapes;

public class Pyramid extends Shape {
	private float height, baseWidth;
	
	public Pyramid(float xPos, float yPos, float zPos, float height, float baseWidth)	{
		super(xPos, yPos, zPos);
		this.height = height;
		this.baseWidth = baseWidth;
	}
	
	public float getHeight()	{
		return height;
	}
	
	public float getBaseWidth()	{
		return baseWidth;
	}
}
