package com.sql;

import java.sql.*;
import java.util.logging.*;

/**
 * SQL helper class. Work with MySQL in an easy way.
 * 
 * @author Mephisto
 */
public class SqlHelper
{
	/**
	 * driver
	 */
	public static String driver = "com.mysql.jdbc.Driver";
	/**
	 * databaseUrl
	 */
	public static String databaseUrl = "jdbc:mysql://45.63.16.140:";
	/**
	 * connection port
	 */
	public static String port = "3306";
	/**
	 * database name
	 */
	public static String databaseName = "ISA";
	public static String url = databaseUrl + port + "/" + databaseName;
	//public static String url ="jdbc:mysql://45.63.16.140:3306";
	/**
	 * user name
	 */
	public static String user = "uwindsorpedia";
	/**
	 * password
	 */
	public static String password = "1000snfj";

	/**
	 * 
	 */
	private SqlHelper()
	{
	}

	/**
	 * driver / url / user / password set the connection of MySQL
	 * 
	 * @return connection
	 */
	public static Connection getConnection()
	{
		try
		{
			// get driver
			Class.forName(driver);
		} catch (ClassNotFoundException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		try
		{
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			return null;
		}
	}

	/**
	 * get a Statement with data set, which can be roll and update
	 * 
	 * @return if fail getting, it will return null. Please check the return
	 *         value while using it.
	 */
	public static Statement getStatement()
	{
		Connection conn = getConnection();
		if (conn == null)
		{
			return null;
		}
		try
		{
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			close(conn);
		}
		return null;
	}

	/**
	 * get a statement
	 * 
	 * @param conn
	 *            SQL connection
	 * @return if fail getting, it will return null. Please check the return
	 *         value while using it.
	 */
	public static Statement getStatement(Connection conn)
	{
		if (conn == null)
		{
			return null;
		}
		try
		{
			return conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			return null;
		}
	}

	/**
	 * get a PreparedStatement
	 * 
	 * @param cmdText
	 *            SQL sentence with ? parameter
	 * @param cmdParams
	 *            SQL sentence parameter table
	 * @return if fail getting, it will return null. Please check the return
	 *         value while using it.
	 */
	public static PreparedStatement getPreparedStatement(String cmdText,
			Object... cmdParams)
	{
		Connection conn = getConnection();
		if (conn == null)
		{
			return null;
		}
		PreparedStatement pstmt = null;
		System.out.println(cmdParams);
		try
		{
			pstmt = conn.prepareStatement(cmdText,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			int i = 1;
			for (Object item : cmdParams)
			{
				pstmt.setObject(i, item);
				i++;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			close(conn);
		}
		return pstmt;
	}

	/**
	 * get a PreparedStatement
	 * 
	 * @param cmdText
	 *            SQL sentence with ? parameter
	 * @param cmdParams
	 *            SQL sentence parameter table
	 * @return if fail getting, it will return null. Please check the return
	 *         value while using it.
	 */
	public static PreparedStatement getPreparedStatement(Connection conn,
			String cmdText, Object... cmdParams)
	{
		if (conn == null)
		{
			return null;
		}
		PreparedStatement pstmt = null;
		try
		{
			pstmt = conn.prepareStatement(cmdText,
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			int i = 1;
			for (Object item : cmdParams)
			{
				pstmt.setObject(i, item);
				i++;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			close(pstmt);
		}
		return pstmt;
	}

	/**
	 * run SQL sentence, return type is integer
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return positive number:success; -1:run error; -2:connection error
	 */
	public static int ExecSql(String cmdText)
	{
		Statement stmt = getStatement();
		if (stmt == null)
		{
			return -2;
		}
		int i;
		try
		{
			i = stmt.executeUpdate(cmdText);
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			i = -1;
		}
		closeConnection(stmt);
		return i;
	}

	/**
	 * run SQL sentence, return type is integer
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return positive number:success; -1:run error; -2:connection error
	 */
	public static int ExecSql(Connection conn, String cmdText)
	{
		Statement stmt = getStatement(conn);
		if (stmt == null)
		{
			return -2;
		}
		int i;
		try
		{
			i = stmt.executeUpdate(cmdText);
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			i = -1;
		}
		close(stmt);
		return i;
	}

	/**
	 * run SQL sentence, return type is integer
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @param cmdParams
	 *            SQL parameter table
	 * @return positive number:success; -1:run error; -2:connection error
	 */
	public static int ExecSql(String cmdText, Object... cmdParams)
	{
		PreparedStatement pstmt = getPreparedStatement(cmdText, cmdParams);
		if (pstmt == null)
		{
			return -2;
		}
		int i;
		try
		{
			i = pstmt.executeUpdate();
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			i = -1;
		}
		closeConnection(pstmt);
		return i;
	}

	/**
	 * run SQL sentence, return type is integer
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @param cmdParams
	 *            SQL parameter table
	 * @return positive number:success; -1:run error; -2:connection error
	 */
	public static int ExecSql(Connection conn, String cmdText,
			Object... cmdParams)
	{
		PreparedStatement pstmt = getPreparedStatement(conn, cmdText,
				cmdParams);
		if (pstmt == null)
		{
			return -2;
		}
		int i;
		try
		{
			i = pstmt.executeUpdate();
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			i = -1;
		}
		close(pstmt);
		return i;
	}

	/**
	 * return the first line of result set, ignore others
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static Object ExecScalar(String cmdText)
	{
		ResultSet rs = getResultSet(cmdText);
		Object obj = buildScalar(rs);
		closeConnection(rs);
		return obj;
	}

	/**
	 * return the first line of result set
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static Object ExecScalar(Connection conn, String cmdText)
	{
		ResultSet rs = getResultSet(conn, cmdText);
		Object obj = buildScalar(rs);
		closeEx(rs);
		return obj;
	}

	/**
	 * return the first line of result set, ignore others
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static Object ExecScalar(String cmdText, Object... cmdParams)
	{
		ResultSet rs = getResultSet(cmdText, cmdParams);
		Object obj = buildScalar(rs);
		closeConnection(rs);
		return obj;
	}

	/**
	 * return the first line of result set, ignore others
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static Object ExecScalar(Connection conn, String cmdText,
			Object... cmdParams)
	{
		ResultSet rs = getResultSet(conn, cmdText, cmdParams);
		Object obj = buildScalar(rs);
		closeEx(rs);
		return obj;
	}

	/**
	 * return a ResultSet
	 * 
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static ResultSet getResultSet(String cmdText)
	{
		Statement stmt = getStatement();
		if (stmt == null)
		{
			return null;
		}
		try
		{
			return stmt.executeQuery(cmdText);
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			closeConnection(stmt);
		}
		return null;
	}

	/**
	 * return a ResultSet
	 * 
	 * @param conn
	 *            SQL connection
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static ResultSet getResultSet(Connection conn, String cmdText)
	{
		Statement stmt = getStatement(conn);
		if (stmt == null)
		{
			return null;
		}
		try
		{
			return stmt.executeQuery(cmdText);
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			close(stmt);
		}
		return null;
	}

	/**
	 * return a ResultSet
	 * 
	 * @param cmdParams
	 *            SQL parameters
	 * @param cmdText
	 *            SQL sentence
	 * @return
	 */
	public static ResultSet getResultSet(String cmdText, Object... cmdParams)
	{
		PreparedStatement pstmt = getPreparedStatement(cmdText, cmdParams);
		if (pstmt == null)
		{
			return null;
		}
		try
		{
			return pstmt.executeQuery();
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			closeConnection(pstmt);
		}
		return null;
	}

	/**
	 * 返回一个 ResultSet
	 * 
	 * @param conn
	 *            SQL connection
	 * @param cmdText
	 *            SQL sentence
	 * @param cmdParams
	 *            SQL parameters
	 * @return
	 */
	public static ResultSet getResultSet(Connection conn, String cmdText,
			Object... cmdParams)
	{
		PreparedStatement pstmt = getPreparedStatement(conn, cmdText,
				cmdParams);
		if (pstmt == null)
		{
			return null;
		}
		try
		{
			return pstmt.executeQuery();
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
			close(pstmt);
		}
		return null;
	}

	public static Object buildScalar(ResultSet rs)
	{
		if (rs == null)
		{
			return null;
		}
		Object obj = null;
		try
		{
			if (rs.next())
			{
				obj = rs.getObject(1);
			}
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
		}
		return obj;
	}

	/**
	 * 获取一个具有更新功能的数据模型 如果只要读取数据，就不要用它了
	 * 
	 * @param cmdText
	 *            能返回一个数据集的查询SQL 语句
	 * @return 表格数据模型
	 * 
	 * 
	 *         DataSet 没有找到在哪个包中,因为暂时用不到所以省略此方法
	 * 
	 *         public static DataSet getDataSet(String cmdText) { Statement stmt
	 *         = getStatement(); DataSet dbc = new DataSet(); if (stmt == null)
	 *         { dbc.code = -2; return dbc; } try { // 查询语句 dbc.rs =
	 *         stmt.executeQuery(cmdText); dbc.model = buildTableModel(dbc.rs);
	 *         dbc.code = dbc.model.getRowCount(); } catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */

	/**
	 * 获取一个具有更新功能的数据模型 如果只要读取数据，就不要用它了
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            能返回一个数据集的查询SQL 语句
	 * @return 表格数据模型
	 * 
	 *         同上一个方法
	 * 
	 *         public static DataSet getDataSet(Connection conn, String cmdText)
	 *         { Statement stmt = getStatement(conn); DataSet dbc = new
	 *         DataSet(); if (stmt == null) { dbc.code = -2; return dbc; } try {
	 *         // 查询语句 dbc.rs = stmt.executeQuery(cmdText); dbc.model =
	 *         buildTableModel(dbc.rs); dbc.code = dbc.model.getRowCount(); }
	 *         catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */

	/**
	 * 获取一个具有更新功能的数据模型 如果只要读取数据，就不要用它了
	 * 
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return 表格数据模型
	 * 
	 * 
	 *         同上一个方法 *
	 * 
	 * 
	 *         public static DataSet getDataSet(String cmdText, Object...
	 *         cmdParams) { PreparedStatement pstmt =
	 *         getPreparedStatement(cmdText, cmdParams); DataSet dbc = new
	 *         DataSet(); if (pstmt == null) { dbc.code = -2; return dbc; } try
	 *         { // 查询语句 dbc.rs = pstmt.executeQuery(); dbc.model =
	 *         buildTableModel(dbc.rs); dbc.code = dbc.model.getRowCount(); }
	 *         catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 * 
	 */
	/**
	 * 获取一个具有更新功能的数据模型 如果只要读取数据，就不要用它了
	 * 
	 * @param conn
	 *            数据库连接
	 * @param cmdText
	 *            需要 ? 参数的 SQL 语句
	 * @param cmdParams
	 *            SQL 语句的参数表
	 * @return 表格数据模型
	 * 
	 * 
	 *         同上
	 * 
	 * 
	 *         public static DataSet getDataSet(Connection conn, String cmdText,
	 *         Object... cmdParams) { PreparedStatement pstmt =
	 *         getPreparedStatement(conn, cmdText, cmdParams); DataSet dbc = new
	 *         DataSet(); if (pstmt == null) { dbc.code = -2; return dbc; } try
	 *         { // 查询语句 dbc.rs = pstmt.executeQuery(); dbc.model =
	 *         buildTableModel(dbc.rs); dbc.code = dbc.model.getRowCount(); }
	 *         catch (SQLException ex) {
	 *         Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE,
	 *         null, ex); dbc.code = -1; } return dbc; }
	 */
	private static void close(Object obj)
	{
		if (obj == null)
		{
			return;
		}
		try
		{
			if (obj instanceof Statement)
			{
				((Statement) obj).close();
			} else if (obj instanceof PreparedStatement)
			{
				((PreparedStatement) obj).close();
			} else if (obj instanceof ResultSet)
			{
				((ResultSet) obj).close();
			} else if (obj instanceof Connection)
			{
				((Connection) obj).close();
			}
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	private static void closeEx(Object obj)
	{
		if (obj == null)
		{
			return;
		}
		try
		{
			if (obj instanceof Statement)
			{
				((Statement) obj).close();
			} else if (obj instanceof PreparedStatement)
			{
				((PreparedStatement) obj).close();
			} else if (obj instanceof ResultSet)
			{
				((ResultSet) obj).getStatement().close();
			} else if (obj instanceof Connection)
			{
				((Connection) obj).close();
			}
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}

	private static void closeConnection(Object obj)
	{
		if (obj == null)
		{
			return;
		}
		try
		{
			if (obj instanceof Statement)
			{
				((Statement) obj).getConnection().close();
			} else if (obj instanceof PreparedStatement)
			{
				((PreparedStatement) obj).getConnection().close();
			} else if (obj instanceof ResultSet)
			{
				((ResultSet) obj).getStatement().getConnection().close();
			} else if (obj instanceof Connection)
			{
				((Connection) obj).close();
			}
		} catch (SQLException ex)
		{
			Logger.getLogger(SqlHelper.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}
}