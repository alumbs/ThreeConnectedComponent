public class ThreeConnectedComponents
{
	public static void main(String [] args)
	{
		//String[] graphFiles = {"Cutpt1.txt", "Cutpt2.txt","Cutpt3.txt"}; 
		String[] graphFiles = {"Cutpt1.txt", "Cutpt2.txt","Cutpt3.txt"}; 
		prtln("Program started");
		
		for(String graph: graphFiles)
		{
			Graph g = new Graph();
			
			g.readInGraph(graph);
			
			g.printGraph();
			
			prtln("");
		}
	}
	
	public static void prt(String value)
	{
		if(value != null && !value.isEmpty())
		{
			System.out.print(value);
		}
	}
	
	public static void prtln(String value)
	{
		if(value != null)
		{
			System.out.println(value);
		}
	}
}
