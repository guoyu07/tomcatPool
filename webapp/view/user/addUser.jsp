<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增用户</title>
</head>
<body>
<div>
<form action="<%=request.getContextPath()%>/user?method=add" method="post">
	<table width="500" border="1">
		<tr>
			<td>用户名：</td>
			<td><input type="text" name="userName" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="提交" /></td>
		</tr>
	</table>
</form>
</div>
</body>
</html>