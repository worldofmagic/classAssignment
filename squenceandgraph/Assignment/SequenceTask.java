package Assignment;

import algorithmDesign.Sequences;

public class SequenceTask
{
	public static void test()
	{
		int[] lengths =
		{ 10, 20, 50, 100 };
		int dr = 0;
		int totalDr = 0;
		long totalTime = 0;
		long startTime = 0;
		long endTime = 0;

		for (int i : lengths)

		{
			for (int j = 0; j < 1000; j++)
			{
				StringPair strPair = new StringPair(i);
				String strPairA = strPair.firstOfPair;
				String strPairB = strPair.secondOfpair;
				startTime = System.nanoTime();
				dr = Sequences.editDistance(strPairA, strPairB);
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
				totalDr += dr;
			}
			StringBuilder output = new StringBuilder();
			output.append("The distance of length | ").append(i)
					.append("| is |").append(totalDr)
					.append(" | Average distance is |").append(totalDr / 1000)
					.append(" | Average CPU time is |")
					.append(totalTime / 1000);
			System.out.println(output);
		}
	}
}
