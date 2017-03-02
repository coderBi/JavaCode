<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC  "-//W3C/DTD HTML 4.01 Transitional//EN" "http:www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询商品列表</title>
<script type="text/javascript">
	function deleteItems() {
		//提交form
		document.itemsForm.action = "${pageContext.request.contextPath }/items/deleteItems.action";
		document.itemsForm.submit();
	}
	function queryItems() {
		//提交form
		document.itemsForm.action = "${pageContext.request.contextPath }/items/queryItems.action";
		document.itemsForm.submit();
	}
</script>
</head>
<body>
<c:if test="${username!=null }">
	当前用户：${username } <a href="${pageContext.request.contextPath }/login.action"><span style="color:red">退出</span></a> 
</c:if>
	<form name="itemsForm"
		action="${pageContext.request.contextPath }/items/queryItems.action"
		method="post">
		查询条件：
		<table width="100%" border=1>
			<tr>
				<td>商品名称</td>
				<!-- 包装类型中的属性itemsCustom的name属性 -->
				<td><input type="text" name="itemsCustom.name" /></td>
			</tr>
			<tr>
				<td>商品类型</td>
				<td><select name="itemsTypes"><c:forEach items="${itemsTypes }" var="type"><option value="${type.key }">${type.value }</option></c:forEach></select></td>
			</tr>
			<tr>
				<td><input type="button" value="查询" onclick="queryItems()" /></td>
				<td><input type="button" value="批量删除" onclick="deleteItems()" />
				</td>
			</tr>
		</table>
		商品列表:
		<table width="100%" border="1">
			<tr>
				<td>选择</td>
				<td>商品名称</td>
				<td>商品价格</td>
				<td>生产日期</td>
				<td>商品描述</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${itemsList }" var="item">
				<tr>
					<td><input type="checkbox" name="items_id" value="${item.id}" />
					</td>
					<td>${item.name }</td>
					<td>${item.price }</td>
					<!-- 注意"美元符{}" 右边的}跟"之间不能有任何空格，否则会将所有数据类型解析成字符串类型 -->
					<td><fmt:formatDate value="${item.createtime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${item.detail }</td>
					<td><a
						href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">修改</a>
					</td>
			</c:forEach>
		</table>
	</form>
</body>
</html>