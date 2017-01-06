<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>文件上传</title>

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
	<form action="${pageContext.request.contextPath }/upload/uploadFiles" enctype="multipart/form-data" method="post">
		文件1：<input type="file" name="files" /><br> 文件2：<input
			type="file" name="files" /><br> 文件3：<input type="file"
			name="files" /><br> <input type="submit" value="上传" />
		<c:if test="${SuccessUpload }">
			<p>${uploadResult }</p>
		</c:if>
	</form>
	<h2>打印文件的具体信息</h2>
	<h3>filesContentType:</h3>
	<ul>
	<c:forEach items="${filesContentType }" var="fct">
			<li>${fct }</li>
	</c:forEach>
	</ul>
	<h3>filesFileName:</h3>
	<ul>
	<c:forEach items="${filesFileName }" var="ffn">
			<li>${ffn }</li>
	</c:forEach>
	</ul>
</body>
</html>
