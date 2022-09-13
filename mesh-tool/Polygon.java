package assignment;

import java.util.LinkedHashSet;

public class Polygon extends GraphicalObject {
	
	public LinkedHashSet<Vertex> vertices;
	
	public Polygon(LinkedHashSet<Vertex> vertices)
	{
		this.vertices = vertices;
	}

	@Override
	public void transform(double[][] matrix) {
		// TODO Auto-generated method stub
		for(Vertex i : vertices)
		{
			i.transform(matrix);
		}
	}

	@Override
	public int hashCode() {
		int out =0;
		for(Vertex i : vertices)
		{
			out += i.hashCode();
		}
		return out;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polygon other = (Polygon) obj;
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices))
			return false;
		return true;
	}
	
	

}
