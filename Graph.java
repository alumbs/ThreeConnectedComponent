import java.io.*;
import java.util.*;

public class Graph
{
	final int EXIT_FAILURE = -1;
	
	//The diagonals of the vertexList contain the degree of the vertex
	private int [][] vertexList;
	
	// index starts at 1 so its +1
	private int numVertices; 
	private String graphName;
	
	private void initializeGraph(int numOfVertices) throws Exception
	{
		int maxVertices = 256;
		if(numOfVertices > 0 && numOfVertices <= maxVertices)
		{
			//Index starts at 1
			numOfVertices++;
			
			this.numVertices = numOfVertices;
			this.vertexList = new int[numOfVertices][numOfVertices];
		}
		else
		{
			throw new Exception("Number of vertices must be between "+
				"1 and " + maxVertices);
		}
	}
	
	private void addAdjacentVertices(int firstV, int secondV)
	{
		if(firstV > 0 && 
			firstV < numVertices && 
			secondV > 0 && 
			secondV < numVertices)
		{
			//1 means two vertices are adjacent to one another
			vertexList[firstV][secondV] = 1;
			vertexList[secondV][firstV] = 1;
			
			//Also increment their diagonals to show
			//the degree of the vertex
			vertexList[firstV][firstV]++;
			vertexList[secondV][secondV]++;
		}
	}
	
	public void printGraph()
	{
		prtln("Graph Name: " + graphName);
		prtln("Number of vertices: " + (numVertices-1));
		for(int i = 1; i < this.numVertices; i++)
		{
			for(int j = 1; j < this.numVertices; j++)
			{
				prt(vertexList[i][j] + " ");
			}
			prtln("");
		}
	}
	
	//Graph File Format: first line contains the number of vertices
	// other lines contain adjacent vertices e.g. 1 2 3 4 means 
	// 1 is adjacent to 2,3, and 4.
	//last line contains a 0 to mark the end of input
	public void readInGraph(String graphFilename)
	{
		BufferedReader br;
		String line;
		String [] splitTemp;
		
		if(graphFilename == null || graphFilename.isEmpty())
		{
			//initializeGraph(1);
			//Do Nothing
		}
		else
		{		
			try
			{
				br = new BufferedReader(new FileReader(graphFilename));
				
				if((line = br.readLine()) != null){
					//First read the name of the Graph
					this.graphName = line;
				}
				
				if((line = br.readLine()) != null){
					//Next read the amount of vertices of the Graph
					this.numVertices = Integer.parseInt(line);
					initializeGraph(this.numVertices);
				}
				
				//Keep going as long as we have valid input
				while((line = br.readLine()) != null && !line.isEmpty()){
					//System.out.println("line = " + line);
					splitTemp = line.split(" ");
					String vertex = splitTemp[0].substring(1);//Remove the first char
					int vertexIndex = 0;
					
					if(!vertex.isEmpty()){
						//The vertex we are at
						vertexIndex = Integer.parseInt(vertex); 
					}
					
					//prtln(""+splitTemp.length);
					
					//Put the degree of the vertex on its diagonal
					//vertexList[vertexIndex][vertexIndex] = splitTemp.length - 1;
					
					//Read the list of adjacent vertices
					for(int i = 1; i < splitTemp.length; i++)
					{
						addAdjacentVertices(vertexIndex, 
							Integer.parseInt(splitTemp[i]));
					}
				}			
			}
			catch(Exception e)
			{
				//Opening file failed or other stuff
				e.printStackTrace();
				System.out.println("Exception caught: " + e);
				System.exit(EXIT_FAILURE);
			}
		}
	}
	
	private void prt(String value)
	{
		if(value != null && !value.isEmpty())
		{
			System.out.print(value);
		}
	}
	
	private void prtln(String value)
	{
		if(value != null)
		{
			System.out.println(value);
		}
	}
}
