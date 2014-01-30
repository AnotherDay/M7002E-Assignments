package assignment2.shapes;

public class Square extends Shape {
	private float edgeLength;
	
	public Square(float xPos, float yPos, float zPos, float edgeLength)	{
		super(xPos, yPos, zPos);
		this.edgeLength = edgeLength;
	}
	
	public float getEdgeLength()	{
		return edgeLength;
	}
}
