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
<link rel="stylesheet" type="text/css" href="css/backupAndRestore.css">
<script type="text/javascript" src="js/backupAndRestore.js"></script>
<jsp:include page="header3.jsp" ></jsp:include>
</head>

<body>

	<jsp:include page="/WEB-INF/header.jsp"></jsp:include>

	<div class="bodydiv" style="text-align: center;">
	<div class="backup">
	<a href="${appname }/backupFile.do">备份网站文件</a><br/>
	</div>
	<div class="backup">
	<a href="${appname }/backupDatabase.do">备份网站数据库</a>
	</div>
	<div class="backup">
	恢复网站文件<br/>
	<form action="${appname }/restoreFile.do" method="post" enctype="multipart/form-data">
	<input type="file" name="file" /><input type="submit" value="恢复" onclick="return warnFile()"/>
	</form>
	<span style="color:red;font-size: 12px;">这将恢复网站数据，同名文件将被覆盖</span>
	</div>
	<div class="backup">
	恢复网站数据库<br/>
	<form action="${appname }/restoreDatabase.do" method="post" enctype="multipart/form-data">
	<input type="file" name="file" /><input type="submit" value="恢复" onclick="return warnDatabase()"/>
	</form>
	<span style="color:red;font-size: 12px;">这将清空数据库，并恢复成文件中的数据内容</span>
	</div>
	<div class="backup">
	<a href="${appname }/restoreDatabaseStructure.do" onclick="return warnDatabaseStructure()">重建数据库结构</a><br/>
	<span style="color:red;font-size: 12px;">这将清空并重建数据库，数据将丢失，请确认已先备份数据库</span>
	</div>
	</div>
	
	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
</body>
</html>
