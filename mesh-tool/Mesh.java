package assignment;

import java.io.IOException;
import java.util.HashSet;

public class Mesh extends GraphicalObject {

	HashSet<Polygon> polygons;
	MeshReader reader;
	MeshWriter writer;
	
	public void setReader (MeshReader reader)
	{
		this.reader = reader;
	}
	
	public void setWriter (MeshWriter writer)
	{
		this.writer = writer;
	}
	
	public void readFromFile (String fileName) throws IOException, WrongFileFormatException
	{
		polygons = reader.read(fileName);
	}
	
	public void writeToFile(String fileName) throws IOException
	{
		writer.write(fileName, polygons);
	}

	@Override
	public void transform(double[][] matrix) {
		for(Polygon i : polygons)
			i.transform(matrix);
	}

	@Override
	public int hashCode() {
		int out = 0;
		for(Polygon i : polygons)
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
		Mesh other = (Mesh) obj;
		if (polygons == null) {
			if (other.polygons != null)
				return false;
		} else if (!polygons.equals(other.polygons))
			return false;
		return true;
	}
	
	
	
	
}
