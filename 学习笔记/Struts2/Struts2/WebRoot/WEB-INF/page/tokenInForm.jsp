<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>tokenInForm.jsp</title>
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
<body>
	<h2>利用s：token防止表单重复提交</h2>
	<h3 style="color:red">token拦截器是由系统提供的，实现原理是：在session里面存一个token
		id，在第一次提交的时候会更新这个id，以后如果重复提交页面中的token
		id就跟这个id不一样了，然后会跳到名字为invalid.token的视图</h3>
	<s:form action="submit" namespace="/token" method="post">
		name:<input type="text" name="name" value="${name }" />
		<s:token></s:token>
		<input type="submit" value="提交"/>
	</s:form>
	<s:if test="reSubmit == true">
		<p style="color:teal">请勿重复提交</p>
	</s:if>
</body>
</html>
