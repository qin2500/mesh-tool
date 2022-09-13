package assignment;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Vector;

import org.junit.jupiter.api.Test;

class test {

	//test OBJMeshReader--------------------------------------------------------
	@Test
	void testOBJMeshReader() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		
		bw.write("v 5 1 0 \r\n"
				+ "v 4 1 0 \r\n"
				+ "v 3 1 0 \r\n"
				+ "f 1 2 3 \r\n");
		bw.close();
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OBJMeshReader()); 
		mesh.readFromFile("temp.txt"); 
		
		Mesh m2 = new Mesh();
		LinkedHashSet<Vertex> verticies = new LinkedHashSet<>();
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(4,1,0));
		verticies.add(new Vertex(3,1,0));
		Polygon polygon = new Polygon(verticies);
		HashSet<Polygon> polygons = new HashSet<>();
		polygons.add(polygon);
		m2.polygons = polygons;
				
		assertEquals(m2.polygons.equals(mesh.polygons),true);
		tempFile.delete();
	}
	@Test
	void testOBJMeshReader2() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		try {
				
			BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
			
			bw.write("5 1 0 \r\n"
					+ "v 4 1 0 \r\n"
					+ "v 3 1 0 \r\n"
					+ "f 1 2 3 \r\n");
			bw.close();
			Mesh mesh = new Mesh(); 
			mesh.setReader(new OBJMeshReader()); 
			mesh.readFromFile("temp.txt"); 	
		}
		catch(WrongFileFormatException e)
		{
			assertEquals(e.message, "WRONG!! do better");
			tempFile.delete();
		}
		
		
		
	}	
	
	//test OBJMeshWriter-----------------------------------------------------------------------
	@Test
	void testOBJMeshWriter() throws IOException
	{
		Mesh m2 = new Mesh();
		LinkedHashSet<Vertex> verticies = new LinkedHashSet<>();
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(4,1,0));
		verticies.add(new Vertex(3,1,0));
		Polygon polygon = new Polygon(verticies);
		HashSet<Polygon> polygons = new HashSet<>();
		polygons.add(polygon);
		m2.polygons = polygons;
		
		File tempFile = File.createTempFile("temp", ".txt");
		m2.setWriter(new OBJMeshWriter());
		m2.writeToFile("temp.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
		assertEquals(br.readLine()!= null, true);
	}
	
	//test PLYMeshReader----------------------------------------------------------------------
	@Test
	void testPLYMeshReader() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		
		bw.write("ply \r\n"
				+ "format ascii 1.0 \r\n"
				+ "element vertex 3 \r\n"
				+ "property float32 x \r\n"
				+ "property float32 y \r\n"
				+ "property float32 z \r\n"
				+ "element face 1 \r\n"
				+ "property list uint8 int32 vertex_indices \r\n"
				+ "end_header \r\n"
				+ "5 1 0 \r\n"
				+ "4 1 0 \r\n"
				+ "3 1 0 \r\n"
				+ "3 0 1 2 \r\n");
		bw.close();
		Mesh mesh = new Mesh(); 
		mesh.setReader(new PLYMeshReader()); 
		mesh.readFromFile("temp.txt"); 
		
		Mesh m2 = new Mesh();
		LinkedHashSet<Vertex> verticies = new LinkedHashSet<>();
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(4,1,0));
		verticies.add(new Vertex(3,1,0));
		Polygon polygon = new Polygon(verticies);
		HashSet<Polygon> polygons = new HashSet<>();
		polygons.add(polygon);
		m2.polygons = polygons;
				
		assertEquals(m2.polygons.equals(mesh.polygons),true);
		tempFile.delete();
	}
	@Test
	void testPLYMeshReader2() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		
		
		
		try {
			bw.write("p \r\n");
			bw.close();
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("temp.txt"); 
		}
		catch(WrongFileFormatException e){
			assertEquals(e.message,"WRONG!! do better");
		}
		tempFile.delete();
	}
	@Test
	void testPLYMeshReader3() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		
		
		
		try {
			bw.write("ply \r\n"
					+ "format ascii 1.0 \r\n"
					+ "element vertex 3 \r\n"
					+ "property float32 x \r\n"
					+ "property float32 y \r\n"
					+ "property float32 z \r\n"
					+ "element face 1 \r\n"
					+ "property list uint8 int32 vertex_indices \r\n"
					+ "end_header \r\n"
					+ "v 5 1 0 \r\n"
					+ "4 1 0 \r\n"
					+ "3 1 0 \r\n"
					+ "3 0 1 2 \r\n");
			bw.close();
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("temp.txt"); 
		}
		catch(WrongFileFormatException e){
			assertEquals(e.message,"WRONG!! do better");
		}
		tempFile.delete();
	}
	@Test
	void testPLYMeshReader4() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		
		
		
		try {
			bw.write("ply \r\n"
					+ "format ascii 1.0 \r\n"
					+ "element vertex 3 \r\n"
					+ "property float32 x \r\n"
					+ "property float32 y \r\n"
					+ "property float32 z \r\n"
					+ "element face 1 \r\n"
					+ "property list uint8 int32 vertex_indices \r\n"
					+ "end_header \r\n"
					+ "5 1 0 \r\n"
					+ "4 1 0 \r\n"
					+ "3 1 0 \r\n"
					+ "f 0 1 2 \r\n");
			bw.close();
			Mesh mesh = new Mesh(); 
			mesh.setReader(new PLYMeshReader()); 
			mesh.readFromFile("temp.txt"); 
		}
		catch(WrongFileFormatException e){
			assertEquals(e.message,"WRONG!! do better");
		}
		tempFile.delete();
	}
	
	//test PLYMeshWriter---------------------------------------------------------
	
	@Test
	void testPLYMeshWriter() throws IOException
	{
		Mesh m2 = new Mesh();
		LinkedHashSet<Vertex> verticies = new LinkedHashSet<>();
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(4,1,0));
		verticies.add(new Vertex(3,1,0));
		Polygon polygon = new Polygon(verticies);
		HashSet<Polygon> polygons = new HashSet<>();
		polygons.add(polygon);
		m2.polygons = polygons;
		
		File tempFile = File.createTempFile("temp", ".txt");
		m2.setWriter(new PLYMeshWriter());
		m2.writeToFile("temp.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
		assertEquals(br.readLine()!= null, true);
	}
	//test OFFMeshReader-----------------------------------------------------------
	@Test
	void testOFFMeshReader() throws IOException, WrongFileFormatException
	{
		File tempFile = File.createTempFile("temp", ".txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter("temp.txt"));
		
		bw.write("OFF \r\n"
				+ "3  1  0 \r\n"
				+ "5.1  1.2  0.3 \r\n"
				+ "4.9  1.5  0.3 \r\n"
				+ "3.8  1.4  0.5 \r\n"
				+ "3  0  1  2  220  220  200 \r\n");
		bw.close();
		Mesh mesh = new Mesh(); 
		mesh.setReader(new OFFMeshReader()); 
		mesh.readFromFile("temp.txt"); 
		
		Mesh m2 = new Mesh();
		LinkedHashSet<Vertex> verticies = new LinkedHashSet<>();
		verticies.add(new Vertex(5.1,1.2,0.3));
		verticies.add(new Vertex(4.9,1.5,0.3));
		verticies.add(new Vertex(3.8,1.4,0.5));
		Polygon polygon = new Polygon(verticies);
		HashSet<Polygon> polygons = new HashSet<>();
		polygons.add(polygon);
		m2.polygons = polygons;
				
		assertEquals(m2.polygons.equals(mesh.polygons),true);
		tempFile.delete();
	}
	//test OFFMeshWriter---------------------------------------------------------
	
	@Test
	void testOFFMeshWriter() throws IOException
	{
		Mesh m2 = new Mesh();
		LinkedHashSet<Vertex> verticies = new LinkedHashSet<>();
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(5,1,0));
		verticies.add(new Vertex(4,1,0));
		verticies.add(new Vertex(3,1,0));
		Polygon polygon = new Polygon(verticies);
		HashSet<Polygon> polygons = new HashSet<>();
		polygons.add(polygon);
		m2.polygons = polygons;
		
		File tempFile = File.createTempFile("temp", ".txt");
		m2.setWriter(new OFFMeshWriter());
		m2.writeToFile("temp.txt");
		
		BufferedReader br = new BufferedReader(new FileReader("temp.txt"));
		assertEquals(br.readLine()!= null, true);
	}
	//test GraphicalObject ------------------------------------------------------------
	@Test
	void testGraphicalObjectRotateXAxis()
	{
		Vertex v = new Vertex(1,2,3);
		v.rotateXAxis(0);
		Vertex v2 = new Vertex(1,2,3);
		assertEquals(v,v2);
	}
	@Test
	void testGraphicalObjectRotateYAxis()
	{
		Vertex v = new Vertex(1,2,3);
		v.rotateYAxis(0);
		Vertex v2 = new Vertex(1,2,3);
		assertEquals(v,v2);
	}
	@Test
	void testGraphicalObjectRotateZAxis()
	{
		Vertex v = new Vertex(1,2,3);
		v.rotateZAxis(0);
		Vertex v2 = new Vertex(1,2,3);
		assertEquals(v,v2);
	}
	
	
	
	//test vertex--------------------------------------------------------
	@Test
	void testVertexEquals() {
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		assertEquals(v1,v2);
	}
	@Test
	void testVertexEquals2()
	{
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,5,3);
		assertNotEquals(v1, v2);
	}
	@Test
	void testVertexEquals3()
	{
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,5);
		assertNotEquals(v1, v2);
	}
	@Test
	void testVertexEquals4()
	{
		Vertex v1 = null;
		Vertex v2 = new Vertex(1,2,5);
		assertNotEquals(v1, v2);
	}
	@Test
	void testVertexEquals5()
	{
		Vertex v2 = new Vertex(1,2,5);
		assertEquals(v2.equals(null), false);
	}
	@Test
	void testVertexEquals6()
	{
		Vertex v2 = new Vertex(1,2,5);
		assertEquals(v2.equals("piss"), false);
	}
	@Test
	void testVertexNotEquals()
	{
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(3,2,1);
		assertNotEquals(v1, v2);
	}
	@Test
	void testVertexHashCode()
	{
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		assertEquals(v1.hashCode(), v2.hashCode());
	}
	@Test
	void testVertexToString()
	{
		Vertex v2 = new Vertex(1,2,3);
		String str = "1.0 2.0 3.0";
		assertEquals(str, v2.toString());
	}
	@Test
	void testVertexTransform()
	{
		Vertex v2 = new Vertex(1,2,3);
		double[][] rotationMatrix = {{1,0,0},
				{0, 1, 0},
				{0, 0, 1}
				};
		v2.transform(rotationMatrix);
		Vertex v3 = new Vertex(1,2,3);
		assertEquals(v2, v3);
	}
	
	//test polygon--------------------------------------------------------
	@Test
	void testPolygonEquals()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		l1.add(v1);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		
		assertEquals(p1,p2);
	}	
	@Test
	void testPolygonEquals2()
	{
		LinkedHashSet<Vertex> l1 = null;
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v2 = new Vertex(1,2,3);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		
		assertNotEquals(p1,p2);
	}
	@Test
	void testPolygonEquals3()
	{
		LinkedHashSet<Vertex> l1 = null;
		LinkedHashSet<Vertex> l2 = null;	
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		
		assertEquals(p1,p2);
	}
	@Test
	void testPolygonEquals4()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v2 = new Vertex(1,2,3);
		Vertex v3 = new Vertex(2,2,3);
		l2.add(v2);
		l2.add(v3);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		
		assertNotEquals(p2,p1);
	}
	@Test
	void testPolygonEquals5()
	{
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v3 = new Vertex(2,2,3);
		l2.add(v3);
		Polygon p2 = new Polygon(l2);
		
		assertEquals(p2.equals(null), false);
	}
	@Test
	void testPolygonEquals6()
	{
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v3 = new Vertex(2,2,3);
		l2.add(v3);
		Polygon p2 = new Polygon(l2);
		
		String bussy = "ass";
		
		assertEquals(p2.equals(bussy), false);
	}
	@Test
	void testPolygonHashCode()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		l1.add(v1);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		
		assertEquals(p1.hashCode(), p2.hashCode());
	}
	@Test
	void testPolygonTransform()
	{
		Vertex v2 = new Vertex(1,2,3);
		double[][] rotationMatrix = {{1,0,0},
				{0, 1, 0},
				{0, 0, 1}
				};
		Vertex v3 = new Vertex(1,2,3);
		
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		l1.add(v3);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		p1.transform(rotationMatrix);
		Polygon p2 = new Polygon(l2);
		assertEquals(p1, p2);
	}
	
	//test Mesh--------------------------------------------------------
	@Test
	void testMeshEquals()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		l1.add(v1);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		HashSet<Polygon> h2 = new HashSet<Polygon>(); 
		h1.add(p1);
		h2.add(p2);
		m1.polygons = h1;
		m2.polygons = h2;
		assertEquals(m1,m2);
	}
	@Test
	void testMeshEquals2()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(2,2,3);
		l1.add(v1);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		HashSet<Polygon> h2 = new HashSet<Polygon>(); 
		h1.add(p1);
		h2.add(p2);
		m1.polygons = h1;
		m2.polygons = h2;
		assertNotEquals(m1,m2);
	}
	@Test
	void testMeshEquals3()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		l1.add(v1);
		Polygon p1 = new Polygon(l1);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		h1.add(p1);;
		m1.polygons = h1;
		assertEquals(m1.equals(null),false);
	}
	@Test
	void testMeshEquals4()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		l1.add(v1);
		Polygon p1 = new Polygon(l1);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		h1.add(p1);;
		m1.polygons = h1;
		assertEquals(m1.equals("cow"),false);
	}
	@Test
	void testMeshEquals5()
	{
		Mesh m1 = new Mesh(), m2 = new Mesh();
		m1.polygons = null;
		m2.polygons = null;
		assertEquals(m1,m2);
	}
	@Test
	void testMeshEquals6()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		l1.add(v1);
		Polygon p1 = new Polygon(l1);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		h1.add(p1);
		m1.polygons = h1;
		m2.polygons = null;
		assertEquals(m2.equals(m1),false);
	}
	@Test
	void testMeshHahsCode()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		l1.add(v1);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		HashSet<Polygon> h2 = new HashSet<Polygon>(); 
		h1.add(p1);
		h2.add(p2);
		m1.polygons = h1;
		m2.polygons = h2;
		assertEquals(m1.hashCode(),m2.hashCode());
	}
	@Test
	void testMeshHahsTransform()
	{
		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
		Vertex v1 = new Vertex(1,2,3);
		Vertex v2 = new Vertex(1,2,3);
		l1.add(v1);
		l2.add(v2);
		Polygon p1 = new Polygon(l1);
		Polygon p2 = new Polygon(l2);
		Mesh m1 = new Mesh(), m2 = new Mesh();
		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
		HashSet<Polygon> h2 = new HashSet<Polygon>(); 
		h1.add(p1);
		h2.add(p2);
		m1.polygons = h1;
		m2.polygons = h2;
		double[][] rotationMatrix = {{1,0,0},
				{0, 1, 0},
				{0, 0, 1}
				};
		m1.transform(rotationMatrix);
		assertEquals(m1,m2);
	}
//	@Test
//	void testMeshWriteToFile() throws IOException
//	{
//		LinkedHashSet<Vertex> l1 = new LinkedHashSet<Vertex>();
//		LinkedHashSet<Vertex> l2 = new LinkedHashSet<Vertex>();	
//		Vertex v1 = new Vertex(1,2,3);
//		Vertex v2 = new Vertex(1,2,3);
//		l1.add(v1);
//		l2.add(v2);
//		Polygon p1 = new Polygon(l1);
//		Polygon p2 = new Polygon(l2);
//		Mesh m1 = new Mesh(), m2 = new Mesh();
//		HashSet<Polygon> h1 = new HashSet<Polygon>(); 
//		HashSet<Polygon> h2 = new HashSet<Polygon>(); 
//		h1.add(p1);
//		h2.add(p2);
//		m1.polygons = h1;
//		m1.setWriter(new OFFMeshWriter());
//		m1.writeToFile("hop.off");
//		BufferedReader br = new BufferedReader(new FileReader("hop.off"));
//		assertEquals(br.readLine().equals("OFF"), true);
//	}

	
	
	

}
