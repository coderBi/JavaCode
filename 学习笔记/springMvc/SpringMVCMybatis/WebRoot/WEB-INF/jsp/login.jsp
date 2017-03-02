<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户登录</title>

</head>

<body>
	<form action="${pageContext.request.contextPath }/login.action" method="post">
		用户名：<input type="text" name="username" /><br>
		密码：<input type="password" value="password" /><br>
		<input type="submit" value="登录"/>
	</form>
</body>
</html>
