<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>json交互测试</title>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.3.js"></script>
   <script type="text/javascript">
   	//请求参数是json字符串
   	function reqeustJson(){
   		$.ajax({
   			type:'post',
   			dataType:'json', //默认是html 可以是html xml text json
   			url:'${pageContext.request.contextPath }/jsonTest/requestJson.action',
   			contentType:'application/json;charset=utf-8',
   			data:'{"name":"手机","price":999}', //可以是字符串、json对象。 这里传递一个字符串
   			success:function(data){
   				alert(data);
   				console.log(data);
   			}
   		}
   			
   		);
   	}
   	
   	//请求参数是键值对
   	function responseJson(){
   		$.ajax({
   			type:'post',
   			dataType:'json', //默认是html 可以是html xml text json
   			url:'${pageContext.request.contextPath }/jsonTest/responseJson.action',
   			//contentType:'application/json;charset=utf-8', //提交的是默认的键值对类型，不需要定义contentType
   			data:{"name":"电脑","price":888}, //可以是字符串、json对象。 这里传递一个json对象，实际上也就是键值对。也可以写成"name=电脑&price=888"
   			success:function(data){
   				alert(data);
   				console.log(data);
   			}
   		}
   			
   		);
   	}
   </script>
  </head>
  
  <body>
    <input type="button" onclick="reqeustJson()" value="请求参数是json串"/>
    <input type="button" onclick="responseJson()" value="请求参数是键值对"/>
  </body>
</html>
