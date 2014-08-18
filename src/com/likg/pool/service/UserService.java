package com.likg.pool.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.likg.pool.domain.User;
import com.likg.pool.util.DbUtil;

public class UserService {

	@SuppressWarnings("static-access")
	public List<User> getList() {

		System.out.println("2222222");

		Connection conn = DbUtil.getConnection();
		System.out.println("conn==" + conn.hashCode());
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> userList = new ArrayList<User>();

		try {

			System.out.println("oldAuto===" + conn.getAutoCommit());
			Thread.currentThread().sleep(500);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			ps = conn.prepareStatement("select id,user_name from auth_user order by id desc");
			rs = ps.executeQuery();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUserName(rs.getString("user_name"));
				userList.add(user);

			}
			// conn.commit();

			if (conn.getAutoCommit() == false) {
				// conn.commit();
				conn.rollback();
				System.out.println("rollback......................");
			}
			
			
			
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtil.closeConnection(rs, ps, conn);
		}

		return userList;
	}

	public void addUser(User user) throws SQLException {
		Connection conn = DbUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean oldAutoCommit = true;
		try {
			oldAutoCommit = conn.getAutoCommit();
			System.out.println("oldAuto===" + oldAutoCommit);
			conn.setAutoCommit(false);

			ps = conn.prepareStatement("insert into auth_user(user_name) values(?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUserName());
			ps.executeUpdate();

			int userId = 0;
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				userId = rs.getInt(1);
			}

			"".charAt(2);

			ps = conn.prepareStatement("insert into auth_user_role(user_id,role_id) values(?,?)");
			ps.setInt(1, userId);
			ps.setInt(2, 1);
			ps.executeUpdate();

			conn.commit();
		} catch (Exception e) {
			// conn.rollback();
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
			// conn.setAutoCommit(oldAutoCommit);
			conn.close();
		}
	}

}
