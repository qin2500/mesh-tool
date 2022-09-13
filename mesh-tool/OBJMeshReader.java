package assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OBJMeshReader implements MeshReader {

	@Override
	public HashSet<Polygon> read(String fileName) throws IOException, WrongFileFormatException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));	
		
		HashSet<Polygon> out = new HashSet<Polygon>();
		ArrayList<Vertex> verticies = new ArrayList<Vertex>();
		
		String input = br.readLine();
		Pattern pattern = Pattern.compile("(v|f)((( )+)?[-]?[0-9]+(\\.[0-9]+)?){3,}");
		while(input != null)
		{
			input = input.trim();
//			System.out.println(input);
			if(!pattern.matcher(input).matches())
				{
					//System.out.println(input);
					throw new WrongFileFormatException();
				}
			String[] parse = input.split("( )+");
			if(parse[0].equals("v")) verticies.add(new Vertex(Double.parseDouble(parse[1]),Double.parseDouble(parse[2]), Double.parseDouble(parse[3])));
			else 
			{
				//for(String s : parse)System.out.println(s);
				LinkedHashSet<Vertex> ass = new LinkedHashSet<Vertex>();
				for(int i=1; i<parse.length; i++)
				{
					ass.add(copy(verticies.get(Integer.parseInt(parse[i])-1)));
				}
					
				out.add(new Polygon(ass));
			}
			input = br.readLine();
			
		}
 
		
		return out;
		
	}
	
	private Vertex copy(Vertex v)
	{
		return new Vertex(v.x, v.y, v.z);
	}
	
}
