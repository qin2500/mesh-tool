package assignment;

public class Vertex extends GraphicalObject {

	double x,y,z;
	
	public Vertex(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void transform(double[][] matrix) {
		// TODO Auto-generated method stub
		double[] cur = {this.x, this.y, this.z};
		double[] vector = new double[3];
		
		for(int r=0; r<matrix.length; r++)
		{
			double sum = 0;
			for(int c = 0; c < matrix[0].length; c++)
			{
				double rounded = Math.round(matrix[r][c] * Math.pow(10, 6))/Math.pow(10, 6);
				sum += rounded * cur[c];
			}
			vector[r] = sum;
		}
		
		this.x = vector[0];
		this.y = vector[1];
		this.z = vector[2];
		
		//System.out.println("[ " + cur[0] + ", " + cur[1] + ", " + cur[2] +  " ] --> [ " + vector[0] + ", " + vector[1] + ", " + vector[2] +  " ]"  );
	
		
	}
	@Override
	public String toString()
	{
		return x + " " + y + " " + z;
	}
	
	@Override
	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		long temp;
//		temp = Double.doubleToLongBits(x);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(y);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		temp = Double.doubleToLongBits(z);
//		result = prime * result + (int) (temp ^ (temp >>> 32));
//		return result;
		
		return (int)(x*100000) + (int)(y*100000) + (int)(z*100000);
		
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vertex other = (Vertex) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}


	
	
	
}
