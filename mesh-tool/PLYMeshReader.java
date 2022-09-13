package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

public class PLYMeshReader implements MeshReader {

	private int vertexCount;
	private int faceCount;
	@Override
	public HashSet<Polygon> read(String fileName) throws IOException, WrongFileFormatException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		//checking fixed lines manually cuz i'm lazy
		String input = br.readLine();
		checkEqual(input, "ply");
		input = br.readLine();
		checkEqual(input, "format ascii 1.0");
		input = br.readLine();
		String[] split = input.split(" ");
		checkEqual(split[0], "element"); checkEqual(split[1], "vertex"); vertexCount = Integer.parseInt(split[2]);
		input = br.readLine();
		checkEqual(input, "property float32 x");
		input = br.readLine();
		checkEqual(input, "property float32 y");
		input = br.readLine();
		checkEqual(input, "property float32 z");
		input = br.readLine();
		split = input.split(" ");
		checkEqual(split[0], "element"); checkEqual(split[1], "face"); faceCount = Integer.parseInt(split[2]);
		input = br.readLine();
		checkEqual(input, "property list uint8 int32 vertex_indices");
		input = br.readLine();
		checkEqual(input, "end_header");
		
		
		
		HashSet<Polygon> out = new HashSet<Polygon>();
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		
		Pattern pattern = Pattern.compile("([-]?[0-9]+(\\.[0-9]+)?(( )+)?){3,}");
		
		for(int i=0; i<vertexCount; i++)
		{
			input = br.readLine().trim();
			if(!pattern.matcher(input).matches()) throw new WrongFileFormatException();
			String[] parse = input.split("( )+");
			verticies.add(new Vertex(Double.parseDouble(parse[0]),Double.parseDouble(parse[1]), Double.parseDouble(parse[2])));
		}

		for(int i=0; i<faceCount; i++)
		{
			input = br.readLine().trim();
			if(!pattern.matcher(input).matches()) throw new WrongFileFormatException();
			String[] parse = input.split("( )+");
			LinkedHashSet<Vertex> ass = new LinkedHashSet<Vertex>();
			for(int j=1; j<parse.length; j++)
			{
				ass.add(copy(verticies.get(Integer.parseInt(parse[j]))));
			}
			
			out.add(new Polygon(ass));
		}
		
		return out;
			
	}
	
	private Vertex copy(Vertex v)
	{
		return new Vertex(v.x, v.y, v.z);
	}
	
	public void checkEqual(String s1, String s2) throws WrongFileFormatException
	{
		if(!s1.trim().equals(s2.trim())) throw new WrongFileFormatException();
	}

}
