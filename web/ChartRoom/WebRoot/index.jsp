<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html >
<head>
<title>聊天室</title>
<link href="CSS/style.css" rel="stylesheet">
<script type="text/javascript">
	function check(){
		if(document.getElementById("form1").username.value==""){
			alert("请输入用户名！");
			document.getElementById("form1").username.focus();
			return false;
		}
		
		if(document.getElementById("form1").password.value==""){
			alert("请输入密码！");
			document.getElementById("form1").password.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<br>
	<h3 style="color:red;" align="center">${ msg }</h3>
	<form id="form1" name="form1" method="post" action="${pageContext.request.contextPath }/user" onSubmit="return check()">
		<!-- 传入当前请求执行的控制器方法 -->
		<input type="hidden" name="method" value="login">
		<table style="width: 371px; height: 230px;border: 0;" align="center" cellpadding="0" cellspacing="0" background="images/login.jpg">
			<tr>
				<td height="120" colspan="3" class="word_dark">&nbsp;</td>
			</tr>
			<tr>
				<td width="53" align="center" valign="top" class="word_dark">&nbsp;</td>
				<td width="216" align="center" valign="top" class="word_dark">
				<table style="width: 100%; height: 100%">
						<tr>
							<td>用户名：</td>
							<td><input type="text" name="username" class="login"></td>
						</tr>
					</table>
				</td>
				<td width="94" valign="top" class="word_dark"></td>
			</tr>
			
			<tr>
				<td width="53" align="center" valign="top" class="word_dark">&nbsp;</td>
				<td width="216" align="center" valign="top" class="word_dark">
					<table style="width: 100%; height: 100%">
						<tr>
							<td>密&nbsp;&nbsp;码：</td>
							<td><input type="password" name="password" class="login"></td>
						</tr>
					</table>
				</td>
				<td width="94" valign="top" class="word_dark"></td>
			</tr>
			<tr>
				<td width="53" align="center" valign="top" class="word_dark">&nbsp;</td>
				<td width="216" align="center" valign="top" class="word_dark">
				<input name="Submit" type="submit" class="btn_bg" value="进 入">
				</td>
				<td width="94" valign="top" class="word_dark">
				</td>
			</tr>
			

		</table>
	</form>
</body>
</html>
