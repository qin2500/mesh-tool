package assignment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PLYMeshWriter implements MeshWriter{

	@Override
	public void write(String fileName, HashSet<Polygon> polygons) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)); 
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
		
		//write fixed lines
		bw.write("ply\n");
		bw.write("format ascii 1.0\n");
		bw.write("element vertex " + verticies.size() + "\n");
		bw.write("property float32 x\n");
		bw.write("property float32 y\n");
		bw.write("property float32 z\n");
		bw.write("element face " + polygons.size() + "\n");
		bw.write("property list uint8 int32 vertex_indices\n");
		bw.write("end_header\n");
		
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
			out += "\n";
			bw.write(out);
		}
		
		bw.close();
		
	}

}
