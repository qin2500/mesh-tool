package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class OBJMeshWriter implements MeshWriter{

	@Override
	public void write(String fileName, HashSet<Polygon> polygons) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)); 
		HashMap<Vertex, Integer> verticies = new HashMap<Vertex, Integer>();
		int line = 1;
		//load vertices
		for(Polygon i : polygons)
		{
			for(Vertex j : i.vertices)
			{
				if(!verticies.containsKey(j))
				{
//					System.out.println("HOOIOIII");
					verticies.put(j,line);
					bw.write("v " + j.x + " " + j.y + " " + j.z + "\n");
					line++;					
				}
				
			}
		}
		//load faces
		for(Polygon i : polygons)
		{
			String out = "f";
			for(Vertex j : i.vertices)
			{
				out += " " + verticies.get(j);
			}
			out += "\n";
			bw.write(out);
		}
		bw.close();
	}
	
}
