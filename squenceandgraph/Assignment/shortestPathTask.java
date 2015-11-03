package Assignment;

import graphs.DijkstraSP;
import graphs.DirectedEdge;
import graphs.Edge;
import graphs.EdgeWeightedDigraph;
import graphs.EdgeWeightedGraph;
import graphs.In;
import graphs.KruskalMST;
import graphs.StdOut;

public class shortestPathTask
{
	static In spIn = new In("largeEWG.txt");
	static long startTime;
	static long endTime;
	static long totalTime;

	public static void spTest()
	{
		EdgeWeightedDigraph spG = new EdgeWeightedDigraph(spIn);
		int s = 0;
		DijkstraSP sp = new DijkstraSP(spG, s);
		totalTime = 0;
		startTime = System.currentTimeMillis();
		for (int t = 2; t < spG.V(); t++)
		{
			if (sp.hasPathTo(t))
			{
				StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
				if (sp.hasPathTo(t))
				{
					for (DirectedEdge e : sp.pathTo(t))
					{
						StdOut.print(e + "   ");
					}
				}
				StdOut.println();
			} else
			{
				StdOut.printf("%d to %d         no path\n", s, t);
			}
		}
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("time cost for shortest path: " + totalTime);
	}

	public static void mstTest()
	{
		EdgeWeightedGraph mstG = new EdgeWeightedGraph(spIn);
		EdgeWeightedGraph G = new EdgeWeightedGraph(mstG);
		KruskalMST mst = new KruskalMST(G);
		totalTime = 0;
		startTime = System.currentTimeMillis();
		for (Edge e : mst.edges())
		{
			StdOut.println(e);
		}
		StdOut.printf("%.5f\n", mst.weight());
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("time cost for shortest path: " + totalTime);
	}
}
