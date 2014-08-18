package com.likg.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.likg.pool.util.DbUtil;

public class DbTest {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		try {
			conn = DBUtil.getConnection("MySQL", "root", "root");
			stmt = conn.createStatement();
			com.mysql.jdbc.StatementImpl ss;
			System.out.println(stmt.getClass().getName());
			rs = stmt.executeQuery("select * from tt");
			
			rs2 = stmt.executeQuery("select * from user");
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "==="+ rs.getInt("type"));

			}
			while (rs2.next()) {
				System.out.println(rs2.getInt("id") + "==="+ rs2.getString("name"));

			}
			
			ps = conn.prepareStatement("select * from tt");
			rs = ps.executeQuery();
			
			ps = conn.prepareStatement("select * from user");
			rs2 = ps.executeQuery();
			while (rs2.next()) {
				System.out.println(rs2.getInt("id") + "==="+ rs2.getString("name"));
			}
			
			while (rs.next()) {
				System.out.println(rs.getInt("id") + "==="+ rs.getInt("type"));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(rs, null, null);
			DbUtil.closeConnection(rs2, ps, conn);
		}

	}
}
