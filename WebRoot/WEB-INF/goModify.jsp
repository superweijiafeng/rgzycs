<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="css/goModify.css">
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="js/goModify.js"></script>
<jsp:include page="header3.jsp" ></jsp:include>
</head>

<body>
	
	<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
	<div class="bodydiv">
		<div
			style="height: 30px; margin-top: 10px; border-bottom: 1px dotted #e0e0e0;font-family: 微软雅黑;font-size: 16px;">
			现在修改的位置： <span><span class="fontText1"><a
					href="${appname }/nav.do?page=goModify">首页</a></span></span>
			<c:if test="${not empty nav }">
				<span class="fontText1">&gt;&gt; <a
					href="${appname }/nav.do?page=goModify&nav=${nav.navId }">${nav.title
						}</a></span>
			</c:if>
			<c:if test="${not empty cat }">
				<span class="fontText1">&gt;&gt; <a
					href="${appname }/nav.do?page=goModify&nav=${nav.navId }&cat=${cat.catId}">${cat.title
						}</a></span>
			</c:if>
			<c:if test="${not empty art }">
				<span class="fontText1">&gt;&gt; <a
					href="${appname }/nav.do?page=goModify&nav=${nav.navId }&cat=${cat.catId}&art=${art.artId}">${art.title
						}</a></span>
			</c:if>
		</div>
		<table class="goModifyTable">
			<c:choose>
				<c:when test="${not empty cat }">
					<tr>
						<th>导航</th>
						<th>栏目</th>
						<th>文章</th>
						<th colspan=2>操作</th>
					</tr>
					<c:forEach items="${cat.arts }" var="art">
						<tr>
							<td>${nav.title }</td>
							<td>${cat.title }</td>
							<td>${art.title }</td>
							<td><a
								href="${appname }/nav.do?page=modify&nav=${art.navId }&cat=${art.catId }&art=${art.artId }">修改</a></td>
							<td><a
								href="${appname }/del.do?nav=${art.navId }&cat=${art.catId }&art=${art.artId }"
								onclick="return confirmDelArt('${art.title}')">删除文章</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=5><a
							href="${appname }/nav.do?page=goAdd&nav=${cat.navId}&cat=${cat.catId} ">添加新文章</a></td>
					</tr>
					<td colspan=5><a
							href="${appname }/nav.do?page=modify&nav=${cat.navId}&cat=${cat.catId} ">修改本栏目</a></td>
					</tr>
				</c:when>
				<c:when test="${not empty nav }">
					<tr>
						<th>导航</th>
						<th>栏目</th>
						<th colspan=3>操作</th>
					</tr>
					<c:forEach items="${nav.cats }" var="cat">
						<tr>
							<td>${nav.title }</td>
							<td>${cat.title }</td>
							<td><a
								href="${appname }/nav.do?page=goModify&nav=${cat.navId }&cat=${cat.catId }">进入</a></td>
							<td><a
								href="${appname }/nav.do?page=modify&nav=${cat.navId }&cat=${cat.catId }">修改</a></td>
							<td><a
								href="${appname }/del.do?nav=${cat.navId }&cat=${cat.catId }"
								onclick="return confirmDelCat('${cat.title}')" >删除栏目</a></td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=5><a
							href="${appname }/nav.do?page=goAdd&nav=${nav.navId}">添加新栏目</a></td>
					</tr>
					<td colspan=5><a
							href="${appname }/nav.do?page=modify&nav=${nav.navId}">修改本导航</a></td>
					</tr>
				</c:when>
				<c:when test="${not empty navs }">
					<tr>
						<th>导航</th>
						<th colspan=2>操作</th>
					</tr>
					<c:forEach items="${navs }" var="nav">
						<tr>
							<td>${nav.title }</td>
							<td><a
								href="${appname }/nav.do?page=goModify&nav=${nav.navId }">进入</a></td>
							<td><a
								href="${appname }/nav.do?page=modify&nav=${nav.navId }">修改</a></td>
						</tr>
					</c:forEach>
				</c:when>

			</c:choose>
		</table>
	</div>
	<c:if test="${not empty nPage }">
	
	<div style="clear: both;text-align: center;" class="fontText5">
    			<c:choose>
    					<c:when test="${currPage-4>=1 }">
    						<c:set var="startPage" value="${currPage-4 }"></c:set>
    					</c:when>
    					<c:otherwise>
    						<c:set var="startPage" value="1"></c:set>
    					</c:otherwise>
    			</c:choose>
    			<c:choose>
    					<c:when test="${currPage+4<=nPage }">
    						<c:set var="endPage" value="${currPage+4 }"></c:set>
    					</c:when>
    					<c:otherwise>
    						<c:set var="endPage" value="${nPage }"></c:set>
    					</c:otherwise>
    			</c:choose>
    			
    			
    			<c:if test="${startPage!=1 }">
    			<a href="${appname }/nav.do?page=goModify&nav=${cat.navId }&cat=${cat.catId}&p=${startPage}">&lt;&lt;&nbsp;</a>
    			</c:if>
    			
    			
    			<c:forEach begin="${startPage }"  end="${endPage }" var="pp">
    			<c:choose>
    			<c:when test="${pp!=currPage }">
    				<a href="${appname }/nav.do?page=goModify&nav=${cat.navId }&cat=${cat.catId}&p=${pp}">[${pp }]</a>&nbsp;
    			</c:when>
    			<c:otherwise>
    				<span style="color:black">${pp }&nbsp;</span>
    			</c:otherwise>
    			</c:choose>
    			</c:forEach>
    			
    			
    			<c:if test="${endPage!=nPage }">
    			<a href="${appname }/nav.do?page=goModify&nav=${cat.navId }&cat=${cat.catId}&p=${endPage}">&gt;&gt;&nbsp;</a>
    			</c:if>
    			<span style="color:black">&nbsp;第${currPage }页/共${nPage }页</span>
    			    			<span style="color:black;">
    				跳转到：
    				<input type="text" id="jump"/>&nbsp;
    				<input type="hidden" id="allPages" value="${nPage }" />
    				<input type="hidden" id="appname" value="${appname }" />
    				<input type="hidden" id="navId" value="${cat.navId }" />
    				<input type="hidden" id="catId" value="${cat.catId }" />
    				<input type="button" value="跳" style="font-size: 16px;" onclick="return jump()"/>
    			</span>
    			</div>
	
	</c:if>

	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
</body>
</html>
