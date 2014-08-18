package com.likg.pool.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.likg.pool.domain.User;
import com.likg.pool.service.UserService;

/**
 * 数据库连接池测试
 */
//@WebServlet()
public class UserAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		UserService userService = new UserService();
		
		//新增
		if("add".equals(method)) {
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			try {
				userService.addUser(user);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		else {
			List<User> userList = userService.getList();
			//for(User user : userList) {
				//System.out.println(user);
			//}
			request.setAttribute("userList", userList);
		}
		
		request.getRequestDispatcher("/view/user/userList.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
