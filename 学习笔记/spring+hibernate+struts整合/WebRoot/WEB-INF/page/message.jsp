<%@ page language="java"  import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>personlist.jsp</title>

	<meta content="text/html charset:utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body> 
  	${message } 
  	<c:if test="${message.equals('添加成功')}">
  		<% String location = request.getHeader("location");
  		//String servletPath = request.getServletPath();  //这个获得是当前页面对于网站root目录的位置。
  		String contextPath = request.getContextPath();
  		String js = "<script>window.location='"+contextPath+"/person/manage.do?method=list'</script>";
  		out.print(js);
		//out.close();
  		 %>
  	</c:if>
  </body>
</html>