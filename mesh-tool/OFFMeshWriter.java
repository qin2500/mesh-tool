package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class OFFMeshWriter implements MeshWriter {

	@Override
	public void write(String fileName, HashSet<Polygon> polygons) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		bw.write("OFF\n");
		
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		
		
		for(Polygon i : polygons)
		{
			for(Vertex j : i.vertices)
			{
				if(!verticies.contains(j))
				{
					verticies.add(j);
			
				}
				
			}
		}
		bw.write(verticies.size() + " " + polygons.size() + " 0\n" );
		
		for(Vertex i : verticies)
		{
			bw.write(i.x + " " + i.y + " " + i.z + "\n");
		}
		for(Polygon i : polygons)
		{
			String out = "" + i.vertices.size() + " ";
			for(Vertex j : i.vertices)
			{
				out += verticies.indexOf(j) + " ";
			}
			out += "220 220 220\n";
			bw.write(out);
		}
		
		bw.close();
		
	}

}
