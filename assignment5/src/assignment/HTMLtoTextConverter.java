package assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import assignment.FileHelper;

public class HTMLtoTextConverter
{
	private static final String DIR_FOLDER = System.getProperty("user.dir")
			+ "\\resources\\";
	private static final String DIR_OUTFOLDER = System.getProperty("user.dir")
			+ "\\result\\";

	

	public static void converter() throws IOException
	{

		ArrayList<File> files = new ArrayList<File>(
				FileHelper.getFileList(DIR_FOLDER));

		for (int i = 0; i < files.size(); i++)
		{

			StringBuilder in = new StringBuilder();
			String r = FileHelper.readToBuffer(in, files.get(i));
			StringBuffer buffer = new StringBuffer(
					FileHelper.splitAndFilterString(r, r.length()));
			FileHelper.writeFile(buffer,
					DIR_OUTFOLDER + "/" + files.get(i).getName() + ".txt");
		}
	}
}
