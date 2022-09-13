package assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Pattern;

public class OFFMeshReader implements MeshReader {

	private int vertexCount;
	private int faceCount;
	
	@Override
	public HashSet<Polygon> read(String fileName) throws IOException, WrongFileFormatException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		Pattern pattern = Pattern.compile("([-]?[0-9]+(\\.[0-9]+)?(( )+)?){3,}");
		
		//dealing with the lame shit
		String weed = br.readLine();
		weed = weed.trim();
		if(!weed.equals("OFF")) throw new WrongFileFormatException();
		weed = br.readLine();
		weed = weed.trim();
		if(!pattern.matcher(weed).matches()) throw new WrongFileFormatException();
		String[] parse = weed.split("( )+");
		vertexCount = Integer.parseInt(parse[0]);
		faceCount = Integer.parseInt(parse[1]);
		
		HashSet<Polygon> out = new HashSet<Polygon>();
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
			
		for(int i=0; i<vertexCount; i++)
		{
			weed = br.readLine().trim();
			if(!pattern.matcher(weed).matches()) throw new WrongFileFormatException();
			parse = weed.split("( )+");
			verticies.add(new Vertex(Double.parseDouble(parse[0]),Double.parseDouble(parse[1]), Double.parseDouble(parse[2])));
		}
		
		pattern = Pattern.compile("([-]?[0-9]+(( )+)?){3,} ?(([0-255]?(( )+)?){3})?");
		for(int i=0; i<faceCount; i++)
		{
			weed = br.readLine().trim();
			//System.out.println(weed);
			weed = weed.trim();
			if(!pattern.matcher(weed).matches()) {
				System.out.println(weed);
				throw new WrongFileFormatException();
			}
			parse = weed.split("( )+");
			LinkedHashSet<Vertex> ass = new LinkedHashSet<Vertex>();
			for(int j=1; j<= Integer.parseInt(parse[0]); j++)
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

}
