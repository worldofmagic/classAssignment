package assignment;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import intractability.BackTSP;

public class BTSP
{
	//generate random number for distance between cities
		public static int GenerateNumber()
		{
			double i = Math.random();
			int n = (int)(i * 200);
			return n;
		}
		
		//generate txt file that contain the city information
		public static String GenerateFile(int noc) throws IOException
		{
			String outFilename = "src/intractability/RoadSetSample" + noc + ".txt";
			OutputStream os = new FileOutputStream(outFilename);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			writer.write(noc + "   some sample cities");
			writer.newLine();
			for(int i = 0; i < noc; i++)
			{
				writer.write((char)('a' + i));
				writer.newLine();
			}
			writer.newLine();
			for(int i = 0; i < noc; i++)
			{
				if(i != noc - 2)
				{
					for(int j = i + 1; j < noc ; j++)
					{
						writer.write("\"" + (char)('a' + i) + "\" \"" + (char)('a' + j) + "\" " + GenerateNumber());
						writer.newLine();
					}
				}
				else
				{
					for(int j = i + 1; j < noc ; j++)
					{
						writer.write("\"" + (char)('a' + i) + "\" \"" + (char)('a' + j) + "\" " + GenerateNumber());
					}
				}
			}
			writer.flush();
			os.close();
			return outFilename;
		}
		
		public static void main(String[] args) throws Exception
		{
			for(int i = 12; i < 21; i++)
			{
				String filename = GenerateFile(i);
				System.out.println("The graphs for " + i + " cities has been generated in file " + filename);
			}
			/*for(int i = 0; i < 11; i++)
			{
				String filename =  "intractability/RoadSetSample" + (i + 10)  + ".txt";
				 Scanner inp = new Scanner(new java.io.File(filename));
			      long start;
			      long[] total = new long[11];
			      System.out.println("Data read from file " + filename);
			      BackTSP.init(inp);
			      start = System.nanoTime();
			      BackTSP.tour();
			      total[i] = System.nanoTime() - start;
			      System.out.printf ("Total time for back tracking for " + (i + 10) + " cities is :  %3.3f milliseconds\n",
					         1.0E-06 * total[i] );
			}*/
			String filename =  "src/intractability/RoadSetSample13.txt";
			 Scanner inp = new Scanner(new java.io.File(filename));
		      long start;
		      System.out.println("Data read from file " + filename);
		      BackTSP.init(inp);
		      start = System.nanoTime();
		      BackTSP.tour();
		      long total = System.nanoTime() - start;
		      System.out.printf ("Total time for back tracking for 12 cities is :  %3.3f milliseconds\n",
				         1.0E-06 * total );
		}
}
