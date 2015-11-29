package com.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class connectionTest
{
	public static void main(String[] args) throws SQLException
	{
		// SqlHelper.getConnection();
		String name = "bill";
		String sql = "select * from User where user_name = ?";
		ResultSet result = SqlHelper.getResultSet(sql, name);
		StringBuilder string = new StringBuilder();
		ResultSetMetaData rsmd = result.getMetaData();

		try
		{
			result.next();
			for (int j = 1; j <= rsmd.getColumnCount(); j++)
			{
				string.append("<").append(rsmd.getColumnName(j)).append(">").append(result.getString(j)).append("</").append(rsmd.getColumnName(j)).append(">");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(string);
	}
}
