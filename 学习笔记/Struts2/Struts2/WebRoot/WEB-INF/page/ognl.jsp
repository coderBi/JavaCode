<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>ognl.jsp</title>

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
	<h3>使用el表达式直接访问Action里面的内容</h3>
	<p style="color:red;">之所以可以直接用el访问到action类里面的属性，因为struts包装了request对象的getAttribute。使得el表达式可以获取到值stack里面的内容。</p>
	${stringInAction }
	<br>
	<h3>通过s：property标签访问Action里面的属性</h3>
	<s:property value="stringInAction" />
	<br>
	<h3>通过s：property标签访问request里面的属性</h3>
	<s:property value="#request.stirngInRequest" />
	<br>
	<h3>通过s：property标签访问application里面的属性</h3>
	<s:property value="#application.stirngInApplication" />
	<br>
	<h3>通过s：property标签访问session里面的属性</h3>
	<s:property value="#session.stirngInSession" />
	<br>
	<h3>访问创建到context里面的对象</h3>
	<s:set name="createListInContext" value="{'one','tow'}"></s:set>
	<h4>list:</h4>
	<ol>
		<s:iterator value="#createListInContext">
			<li><s:property />
			</li>
		</s:iterator>
	</ol>
	<h4>map:</h4>
	<s:set name="createMapInContext"
		value="#{'one':'value1','tow':'value2'}"></s:set>
	<ol>
		<s:iterator value="#createMapInContext">
			<li><s:property value="key" />=&gt;<s:property value="value" />
			</li>
		</s:iterator>
	</ol>
	<br>
	<h3>访问创建到application里面的对象</h3>
	<s:set name="createListInApplication" value="{'app1','app2'}"
		scope="application"></s:set>
	<h4>list:</h4>
	<ol>
		<s:iterator value="#application.createListInApplication">
			<li><s:property />
			</li>
		</s:iterator>
	</ol>
	<h3>按照筛选条件显示</h3>
	<h4>筛选之后的books:</h4>
	<ol>
		<s:iterator value="books.{?#this.price>98}">
			<li>id=<s:property value="id" />,name=<s:property value="name" />,price=<s:property
					value="price" />
			</li>
		</s:iterator>
	</ol>
	<hr>
	<h2>以下是其他的struts标签使用</h2>
	<h3>使用迭代器里的status属性跟if else 标签实现隔行换色</h3>
	<h4>隔行换色打印books:</h4>
	<ol>
		<s:iterator value="books" status="st">
			<li style=<s:if test="#st.isOdd()">"color:red"</s:if>
				<s:else>"color:blue"</s:else>>id=<s:property value="id" />,name=<s:property
					value="name" />,price=<s:property value="price" /></li>
		</s:iterator>
	</ol>
	<h3>使用url标签</h3>
	<s:set name="myUrl" value="'http://www.welcome.com'"></s:set>
	<a href=<s:url value="#myUrl"></s:url>>没有使用%进行计算的url</a><br>
	<a href=<s:url value="%{#myUrl}"></s:url>><span>使用了%进行计算的url</span></a><br>
	<h3>使用checkboxxlist</h3>
	<s:checkboxlist name="list" list="{'apple','pear','banana'}" value="{'apple'}">
	</s:checkboxlist><br>
	<s:checkboxlist name="map" list="#{1:'apple',2:'pear',3:'banana'}" value="{1,2}">
	</s:checkboxlist><br>
	<s:checkboxlist name="bean" list="books" listKey="id" listValue="name" value="{1,3}">
	</s:checkboxlist><br>
	<h3>使用radio标签</h3>
	<s:radio name="radio1" list="{'apple','pear','banana'}" value="'banana'">
	</s:radio><br>
	<h3>使用select标签</h3>
	<s:select name="radio1" list="{'apple','pear','banana'}" value="'pear'">
	</s:select><br>
</body>
</html>
	