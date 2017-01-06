<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>第一个struts应用</title>
    <meta content="text/html">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <!-- 
  <p>name:${name}</p>><br>
  <!% byte[] xx = request.getParameter("name").getBytes("ISO-8859-1");
  System.out.println(request.getParameter("name"));
  String str = new String(xx,"utf-8"); 
  System.out.println("str="+str);%>
   -->
  <body>
   <form action="<%=request.getContextPath()%>/test/helloworld" method="post">
   id:<input type="text" name="id" value="${ id }"/><br>
   name:<input type="text" name="name" value="${name }"/><br>
   <input type="submit" value="提交"/>
   </form>
  </body>
</html>
