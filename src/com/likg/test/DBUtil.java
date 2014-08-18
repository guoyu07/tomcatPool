package com.likg.test;

import java.sql.*;
import java.sql.*;

public class DBUtil
{
	public static Connection getConnection(String dbName, String user, String password)
	{
		String driverName = null;
		String url = null;
		Connection conn = null;
		
		if("oracle".equalsIgnoreCase(dbName))
		{
			driverName = "oracle.jdbc.driver.OracleDriver";
			url = "jdbc:oracle:thin:@192.168.0.26:1521:tarena";
		}
		else if("MySQL".equalsIgnoreCase(dbName))
		{
			driverName = "com.mysql.jdbc.Driver";
			url = "jdbc:mysql://127.0.0.1:3306/auth";
		}
		
		try
		{
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}
