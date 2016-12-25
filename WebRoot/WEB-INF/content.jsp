<%@ page language="java" import="java.util.*,com.fjw.domain.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
<title>如皋智源财税咨询有限公司</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<link rel="stylesheet" type="text/css" href="css/global.css" />
	<link rel="stylesheet" type="text/css" href="css/content.css" />
	<jsp:include page="header3.jsp" ></jsp:include>
	<script type="text/javascript" src="js/content.js"></script>
  </head>
  
  <body>
    <jsp:include page="/WEB-INF/header.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/header2.jsp"></jsp:include>
    
    <div class="bodydiv">
    
    <!-- 侧边栏开始 -->
    <div class="catDiv fontText4">
    	<c:if test="${not empty nav.cats}">
    		
    			<div class="catCap">
				<div class="fontCap4 catCapInner">${nav.title }</div>
				</div>
   				<ul style="list-style: none;">
   					<c:forEach items="${nav.cats }" var="cat">
   					<li><a href="${appname }/nav.do?page=content&nav=${nav.navId }&cat=${cat.catId }">--${cat.title }</a></li>
   					</c:forEach>
   				</ul>
    		
    	</c:if>
    	<c:if test="${nav.navId!=3 }">
    	<jsp:include page="/WEB-INF/lefter1.jsp"></jsp:include>
    	</c:if>
    	<jsp:include page="/WEB-INF/lefter2.jsp"></jsp:include>
    	</div>
    	

    	
    	<div class="contentDiv">
    		
    		  <div style="height: 30px; margin-top: 10px; border-bottom: 1px dotted #e0e0e0;font-family: 微软雅黑;font-size: 16px;">你现在的位置：
    			<span><span class="fontText1"><a href="${appname }/nav.do?page=home">首页</a></span></span>
    			<c:if test="${not empty nav }"><span class="fontText1">&gt;&gt; <a href="${appname }/nav.do?page=content&nav=${nav.navId }">${nav.title }</a></span></c:if>
    			<c:if test="${not empty cat }"><span class="fontText1">&gt;&gt; <a href="${appname }/nav.do?page=content&nav=${nav.navId }&cat=${cat.catId}">${cat.title }</a></span></c:if>
    			<c:if test="${not empty art }"><span class="fontText1">&gt;&gt; <a href="${appname }/nav.do?page=content&nav=${nav.navId }&cat=${cat.catId}&art=${art.artId}">${art.title }</a></span></c:if>
    			</div>
    		
    		<c:choose>
    		<c:when test="${empty arts }">
    		<span class="contentDivTitle">${title }</span><br/>
    		<span class="contentContent">${content }</span>
    		<div class="contentTime fontText1"><span style="text-align: right;">发布时间：${timestamp }</span></div>
    		</c:when>
    		<c:otherwise>
    			<c:forEach items="${arts }" var="art">
    			<ul style="list-style: none;">
    			<li>
    				<div class="artDiv">
    					<div>
    					<a href="${appname }/nav.do?page=content&nav=${art.navId }&cat=${art.catId}&art=${art.artId}">
    						<img src="
    						<c:choose>
    						<c:when test="${not empty art.image.filename}">
    						${art.image.filename }
    						</c:when>
    						<c:when test="${not empty cat.image.filename}">
    						${cat.image.filename }
    						</c:when>
    						<c:when test="${not empty nav.image.filename}">
    						${nav.image.filename }
    						</c:when>
    						<c:otherwise>
    						file/image/about.jpg
    						</c:otherwise>
    						</c:choose>
    						">
    					</a>
    					<p><a href="${appname }/nav.do?page=content&nav=${art.navId }&cat=${art.catId}&art=${art.artId}">${art.title }</a></p>
    					</div>
    				</div>
    				
    			</li>
    			</ul>
    			</c:forEach>
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
    			<a href="${appname }/nav.do?page=content&nav=${cat.navId }&cat=${cat.catId}&p=${startPage}">&lt;&lt;&nbsp;</a>
    			</c:if>
    			
    			
    			<c:forEach begin="${startPage }"  end="${endPage }" var="pp">
    			<c:choose>
    			<c:when test="${pp!=currPage }">
    				<a href="${appname }/nav.do?page=content&nav=${cat.navId }&cat=${cat.catId}&p=${pp}">[${pp }]</a>&nbsp;
    			</c:when>
    			<c:otherwise>
    				<span style="color:black">${pp }&nbsp;</span>
    			</c:otherwise>
    			</c:choose>
    			</c:forEach>
    			
    			
    			<c:if test="${endPage!=nPage }">
    			<a href="${appname }/nav.do?page=content&nav=${cat.navId }&cat=${cat.catId}&p=${endPage}">&gt;&gt;&nbsp;</a>
    			</c:if>
    			<span style="color:black">&nbsp;第${currPage }页/共${nPage }页</span>
    			</div>
    			<span style="color:black;display: inline-block; float: right;">
    				跳转到：
    				<input type="text" id="jump"/>&nbsp;
    				<input type="hidden" id="allPages" value="${nPage }" />
    				<input type="hidden" id="appname" value="${appname }" />
    				<input type="hidden" id="navId" value="${cat.navId }" />
    				<input type="hidden" id="catId" value="${cat.catId }" />
    				<input type="button" value="跳" style="font-size: 16px;" onclick="return jump()"/>
    			</span>
    		</c:otherwise>
    		</c:choose>
    		
    		
    	</div>
    </div>
    
	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/righter.jsp"></jsp:include>
  </body>
</html>
