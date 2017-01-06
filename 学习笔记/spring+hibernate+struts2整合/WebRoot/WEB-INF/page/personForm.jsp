<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ page import="cn.ss2h.domain.Gender"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>添加用户</title>
<script type="text/javascript" src="/ss2h/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="/ss2h/js/person_validate.js"></script>
<script type="text/javascript" src="/ss2h/js/BeanTools.js"></script>
<script type="text/javascript">
	function fillGender(msg) { //向服务器请求到gender信息，然后填充到页面的相应位置,这个函数不单独使用，用来处理BeanTools.js里面的gender_operations.getAll的回调
		var gendersJson = msg;
		console.log(gendersJson);
		var genderNode = $("#person_gender");
		var str = "";
		for ( var gender in gendersJson) {
			if (gendersJson[gender].id != genderNode.attr("value"))
				str += "<label><input type='radio' class='person_gender_id' name='person.gender.id' value='"+gendersJson[gender].id+"'/>"
						+ gendersJson[gender].name + "</label>";
			else
				str += "<label><input type='radio' class='person_gender_id' name='person.gender.id' value='"+gendersJson[gender].id+"' checked='checked'/>"
						+ gendersJson[gender].name + "</label>";
		}
		genderNode.html(genderNode.html() + str);

		$(".person_gender_id").click(function() {
			$("#person_gender_errorMsg").remove();
		});
	}

	$(function() {
		gender_operations.getAll(fillGender);
		$("#person_form").submit(
				function(event) {
					person_validate(person_validate.Field.PERSON_NAME
							| person_validate.Field.PERSON_GENDER_ID); //提交前检查所有的字段
					if ("true" != $("#person_name").attr("validate") //用户名还没有校验通过
							|| "true" != $("#person_gender").attr("validate") //gender还没有校验通过
					)
						event.preventDefault();
				});
	});
</script>
</head>
<body>
	<s:form action="add" namespace="/person/manage" method="post"
		id="person_form">
		<table>
			<tr>
				<td colspan="2">请输入您要添加的用户信息(<span style="color:red;">*</span>为<span
					style="color:red;">必填</span>项)</td>
			</tr>
			<tr>
				<td><span style="color:red;">*</span>name:</td>
				<td><input type="text" name="person.name" id="person_name"
					value="${person.name }"
					onblur="JavaScript:person_validate(person_validate.Field.PERSON_NAME);if('true' == $(this).attr('validate'))person_validate(person_validate.Field.PERSON_GENDER_ID);"
					onchange="JavaScript:$(this).removeAttr('validate')"></td>
			</tr>
			<tr>
				<td><span style="color:red;">*</span>gender:</td>
				<td id='person_gender' value="${person.gender.id }"></td>
			</tr>
			<tr>
				<td colspan="2" align="left"><input type="submit" value="添加" />
				</td>
			</tr>
		</table>
	</s:form>


	<s:form action="add" namespace="/person/manage" method="post">
		<br>
 		name:<s:textfield name="person.name" value="%{#request.person.name}" />
		<br>
 		gender:<s:radio list="#request.genders" listKey="id" listValue="name"
			name="person.gender.id" value="%{#request.person.gender.id}" />
		<br>
		<s:submit />
	</s:form>
</body>
</html>