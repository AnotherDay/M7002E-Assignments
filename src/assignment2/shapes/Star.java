package assignment2.shapes;

public class Star extends Shape {
	
	private float span;
	
	public Star(float xPos, float yPos, float zPos, float span)	{
		super(xPos, yPos, zPos);
		this.span = span;
	}
	
	public float getSpan()	{
		return span;
	}
}
