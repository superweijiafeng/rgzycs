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

</head>

<body>

	<jsp:include page="/WEB-INF/header.jsp"></jsp:include>

	<div class="bodydiv">
		<div style="padding: 40px 0px;margin:auto;width: 450px;">
				<span style="font-family: 微软雅黑; font-size: 20px;line-height: 40px;">
					${info }<br/>
					<a href="#" onclick="javascript:window.close();">关闭该页面</a>
				</span>
		</div>
	</div>


	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
</body>
</html>
