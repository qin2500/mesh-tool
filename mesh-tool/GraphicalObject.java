package assignment;

public abstract class GraphicalObject {

	public abstract void transform (double matrix[][]);
	
	public void rotateXAxis (double theta)
	{
		double[][] rotationMatrix = {{1,0,0},
									{0, Math.cos(theta), (-1)*Math.sin(theta)},
									{0, Math.sin(theta), Math.cos(theta)}
									}; 
		transform(rotationMatrix);
	}
	
	public void rotateYAxis (double theta)
	{
		double[][] rotationMatrix = {{Math.cos(theta),0,Math.sin(theta)},
									{0, 1, 0},
									{-Math.sin(theta), 0, Math.cos(theta)}
									}; 
		transform(rotationMatrix);
	}
	
	public void rotateZAxis (double theta)
	{
		double[][] rotationMatrix = {{Math.cos(theta),-Math.sin(theta),0},
									{Math.sin(theta), Math.cos(theta), 0},
									{0, 0, 1}
									}; 
		transform(rotationMatrix);
	}
}
