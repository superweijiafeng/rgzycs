<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<div class="catCap">
		<div class="fontCap4 catCapInner">${navs[2].title }</div>
	</div>
	<ul style="list-style: none;">
		<c:forEach items="${navs[2].cats }" var="cat">
			<li><a
				href="${appname }/nav.do?page=content&nav=${cat.navId }&cat=${cat.catId }">--${cat.title
					}</a></li>
		</c:forEach>
	</ul>
