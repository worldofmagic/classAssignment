package assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPhoneAndEmail
{
	private static final String DIR_FOLDER = System.getProperty("user.dir")
			+ "\\result\\";

	public static void filterPhone() throws IOException
	{
		Pattern phone = Pattern.compile(
				"(\\+1)?[-\\s]?(\\()?(\\d){3}[-\\s\\)](\\d){3}[-\\s\\)](\\d){4}");
		// Pattern phone = Pattern.compile("(\\()?(\\d){3}(\\))?[-
		// ](\\d){3}-(\\d){4}");
		ArrayList<File> files = new ArrayList<File>(
				FileHelper.getFileList(DIR_FOLDER));
		for (int i = 0; i < files.size(); i++)
		{
			StringBuilder in = new StringBuilder();
			String r = FileHelper.readToBuffer(in, files.get(i));
			StringBuffer buffer = new StringBuffer(
					FileHelper.splitAndFilterString(r, r.length()));
			StringBuffer rst = new StringBuffer();
			Matcher mphone = phone.matcher(buffer);
			while (mphone.find())
			{
				rst.append(mphone.group(0));
				FileHelper.writeFile(rst, DIR_FOLDER + "/" + "phonerst.txt");
				System.out.println(rst);
			}			
		}
	}

	public static void filterEmail() throws IOException
	{
		Pattern email = Pattern.compile("[a-zA-Z][\\w_]+@\\w+(\\.\\w+)+");
		ArrayList<File> files = new ArrayList<File>(
				FileHelper.getFileList(DIR_FOLDER));
		for (int i = 0; i < files.size(); i++)
		{
			StringBuilder in = new StringBuilder();
			String r = FileHelper.readToBuffer(in, files.get(i));
			StringBuffer buffer = new StringBuffer(
					FileHelper.splitAndFilterString(r, r.length()));
			StringBuffer rst = new StringBuffer();
			Matcher memail = email.matcher(buffer);
			while (memail.find())
			{
				rst.append(memail.group(0));
				System.out.println(rst+"\n");
				FileHelper.writeFile(rst, DIR_FOLDER + "/" + "emailrst.txt");
			}
			
		}
	}
}
