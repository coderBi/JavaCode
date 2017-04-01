<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!-- 进行用户登录拦截 -->
<%if(null == session.getAttribute("user")){
	out.println("<script language='javascript'>alert('您的账户已经过期，请重新登录!');window.location='index.jsp';</script>");
	return;
}%>
