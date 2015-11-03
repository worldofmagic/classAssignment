package Assignment;

import graphs.CC;
import graphs.Graph;
import graphs.Queue;
import graphs.StdIn;
import graphs.StdOut;
import graphs.SymbolGraph;

public class symbolGTask
{
	static long startTime;
	static long endTime;
	static long totalTime;
	public static void symTest()
	{
		// In in = new In("mediumDG.txt");
		SymbolGraph sg = new SymbolGraph("movies.txt", "/");
		Graph G = sg.G();
		CC cc = new CC(G);

		// number of connected components
		int M = cc.count();
		StdOut.println(M + " components");

		// compute list of vertices in each connected component
		
		totalTime = 0;
		startTime = System.currentTimeMillis();
		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
		for (int i = 0; i < M; i++)
		{
			components[i] = new Queue<Integer>();
		}
		for (int v = 0; v < G.V(); v++)
		{
			components[cc.id(v)].enqueue(v);
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("time cost for connected components: " + totalTime);
		

		// print results
		for (int i = 0; i < M; i++)
		{
			for (int v : components[i])
			{
			//	StdOut.print(v + " ");
			}
		//	StdOut.println();
		}

	}
	
	public static void SearchTask()
	{
		 SymbolGraph sg = new SymbolGraph("movies.txt", "/");
	        Graph G = sg.G();
	        while (StdIn.hasNextLine()) {
	            String source = StdIn.readLine();
	            if (source.equals("exit")) {
	                StdOut.println("Bye...");
	            	break;
	            }
	            if (sg.contains(source)) {
	                int s = sg.index(source);
	                for (int v : G.adj(s)) {
	                    StdOut.println("   " + sg.name(v));
	                }
	            }
	            else {
	                StdOut.println("input does not contain '" + source + "'\n");
	            }
	        }
	        
	        CC cc = new CC(G);

	        // number of connected components
	        int M = cc.count();
	        StdOut.println(M + " components");

	        // compute list of vertices in each connected component
	        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
	        for (int i = 0; i < M; i++) {
	            components[i] = new Queue<Integer>();
	        }
	        for (int v = 0; v < G.V(); v++) {
	            components[cc.id(v)].enqueue(v);
	        }

	        // print results
	        for (int i = 0; i < M; i++) {
	            for (int v : components[i]) {
	                StdOut.print(v + " ");
	            }
	            StdOut.println();
	        }
	}
}
