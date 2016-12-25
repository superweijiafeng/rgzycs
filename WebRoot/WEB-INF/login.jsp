<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<title>如皋智源财税咨询有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<jsp:include page="header3.jsp" ></jsp:include>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript">
function refreshCode(){
	document.getElementById("checkcode").src="${appname }/checkcode?t="+new Date().getTime();
}
</script>

</head>

<body>

	<jsp:include page="/WEB-INF/header.jsp"></jsp:include>

	<div class="bodydiv">
		<div style="padding: 40px 0px;margin:auto;width: 450px;">
			<form action="${appname }/login.do" method="POST">
				<table style="font-family: 微软雅黑; font-size: 20px;line-height: 40px;">
					<tr>
						<td>用户名 :</td>
						<td><input type="text" name="username" <c:if test="${not empty username }">value='${username }'</c:if>  /></td>
						<td class="error"><c:out value="${errorUsername }" default=""></c:out></td>
					</tr>
					<tr>
						<td>密码 :</td>
						<td><input type="password" name="password" <c:if test="${not empty password }">value='${password }'</c:if>/></td>
						<td class="error"><c:out value="${errorPassword }" default=""></c:out></td>
					</tr>
					<tr>
						<td>验证码 :</td>
						<td><input type="text" name="checkcode" /></td>
						<td class="error"><img src="${appname }/checkcode" id="checkcode"
							onclick="refreshCode()" /></td>
						<td class="error"><c:out value="${errorCheckCode }" default=""></c:out></td>
					</tr>
					<tr>
						<td>保存密码 :</td>
						<td><input type="checkbox" name="isKeepPass" value="true" <c:if test="${not empty isKeepPass and isKeepPass=='true' }">checked</c:if>/></td>
						<td></td>
					</tr>
				</table>
				<html:image src="file/image/loginButton.jpg" style="width:100px;"></html:image>
			</form>
		</div>
	</div>


	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
</body>
</html>
