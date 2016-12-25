<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<head>


<link rel="stylesheet" type="text/css" href="css/home.css" />

</head>

<body>
	<div style="clear:left;padding-top:5px;">
		<div class="bodydiv2">
			<div class="bodydiv" style="width: 1000px;">
				<div class="mapHolder">
					<div class="map">
						<div class="fontCap3">
							<a href="${appname }/nav.do?page=home">网站首页</a>
						</div>
					</div>
					<c:forEach items="${navs }" var="nav">
						<div class="map">
							<div class="fontCap3"><a href="${appname }/nav.do?page=content&nav=${nav.navId}">${nav.title }</a></div>
							<ul>
								<c:forEach items="${nav.cats }" var="cat">
									<li><a href="${appname }/nav.do?page=content&nav=${cat.navId}&cat=${cat.catId}">${cat.title }</a></li>
								</c:forEach>
							</ul>
						</div>
					</c:forEach>
					<div class="map">
						<div class="fontCap3"><a href="${appname }/nav.do?page=contact">联系我们</a></div>
					</div>


				</div>

			</div>
		</div>
		<div class="bodydiv3">
			<div class="bodydiv fontText3">
				法律声明 <span style="display:block;float: right;">&copy;2015-2016
					XXXXXXXXXX</span>
			</div>
		</div>
	</div>
</body>
