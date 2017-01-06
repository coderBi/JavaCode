<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="cn.ss2h.domain.Gender"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
</head>
<body>
	<s:iterator value="#request.persons" var="person"> 
		id=<s:property value="id" />,name=<s:property value="name" />,gender=<s:if
			test="gender==null">null
		</s:if>
		<s:else>
			<s:property value="gender.name" />
		</s:else>
		<br>
	</s:iterator>
</body>
</html>