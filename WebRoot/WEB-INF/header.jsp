<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div class="bodydiv">
		<div class="header">
			<div class="headerImage">
				<img src="file/image/head.jpg" />
			</div>
			<div class="headerContact">
				<ul style="list-style: none;">
					<li class="fontCap1">服务热线</li>
					<li class="fontText2">0513-87176068</li>
					<li class="fontText2">13773830048</li>
				</ul>
			</div>
		</div>
		<div class="toolbar fontCap2">
			<ul style="list-style: none;">
				<li><a href="${appname }/">网站首页</a></li>
				<c:forEach items="${navs }" var="nav">
				<li><a href="${appname }/nav.do?page=content&nav=${nav.navId}">${nav.title }</a></li>
				</c:forEach>
				<li><a href="${appname }/nav.do?page=contact">联系我们</a></li>
			</ul>
		</div>
	</div>