package Assignment;

import graphs.DepthFirstOrder;
import graphs.Digraph;
import graphs.In;
import graphs.StdOut;

public class DFSTask
{
	public static void test()
	{
		In in = new In("largeDG.txt");
		Digraph G = new Digraph(in);
		long startTime = 0;
		long endTime = 0;
		long totalTime = 0;

		DepthFirstOrder dfs = new DepthFirstOrder(G);
		StdOut.println("   v  pre post");
		StdOut.println("--------------");
		for (int v = 0; v < G.V(); v++)
		{
			//StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
		}

		StdOut.print("Preorder:  ");
		startTime= System.currentTimeMillis();
		for (int v : dfs.pre())
		{
		//	StdOut.print(v + " ");
		}
		endTime = System.currentTimeMillis();
		totalTime += endTime - startTime;
		System.out.println(totalTime);
		//StdOut.println();

		StdOut.print("Postorder: ");
		totalTime = 0;
		startTime = System.currentTimeMillis();
		for (int v : dfs.post())
		{
		//	StdOut.print(v + " ");
		}
		endTime = System.currentTimeMillis();
		totalTime += endTime - startTime;
		System.out.println(totalTime);
		//StdOut.println();

		StdOut.print("Reverse postorder: ");
		totalTime = 0;
		startTime = System.currentTimeMillis();
		for (int v : dfs.reversePost())
		{
			//StdOut.print(v + " ");
		}
		endTime = System.currentTimeMillis();
		totalTime += endTime - startTime;
		System.out.println(totalTime);
		//StdOut.println();
	}
}
