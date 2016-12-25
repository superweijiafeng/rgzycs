<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>
<head>

<title>如皋智源财税咨询有限公司</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="js/home.js"></script>
<script type="text/javascript" src="js/homeAd2.js"></script>
<jsp:include page="header3.jsp" ></jsp:include>
</head>

<body onload="init();initAd2()">
	<div class="bodydiv">
		<jsp:include page="/WEB-INF/header.jsp" ></jsp:include>
		<div class="ad">
			<ul style="list-style: none;">
				<li><img src="file/image/show1.jpg" name="show" /></li>
				<li><img src="file/image/show2.jpg" name="show" /></li>
			</ul>
		</div>
		<div class="ad2" id="ad2">
			<div id="roundHolder" class="ad2Holder" >
			<img src="file/image/ad2/all.jpg" height="100px" usemap="#ad2Map" onmouseover="stopRound()" onmouseout="startRound()" id="roundImg"/><img id="roundImg2" />
			</div>
			

			<map id="ad2Map" name="ad2Map">
				<area href="javascript:void(0);" shape="rect" coords="0,0,133,100" onclick="showAd2('1')" alt="1" onmouseover="stopRound()" onmouseout="startRound()"/>
				<area href="javascript:void(0);" shape="rect" coords="155,0,228,100" onclick="showAd2('2')" alt="2" onmouseover="stopRound()" onmouseout="startRound()"/>
				<area href="javascript:void(0);" shape="rect" coords="250,0,355,100" onclick="showAd2('3')" alt="3" onmouseover="stopRound()" onmouseout="startRound()"/>
			</map>
		</div>
		
		<div class="ad2View" id="ad2View">
		</div>
		
		<div class="leftFrame">
			<div class="innerCap">
				<div class="innerCapBody">公司介绍</div>
			</div>
			<div class="fontText1 innerText">
				<img class="rightImg" src="file/image/about.jpg" />
				
				如皋智源财税是一家以公司注册代理、会计代理记账为主的财务公司， 公司成立以来，已成功为数千家企业成功代理，在客户中享有良好的口碑。
				公司拥有一支专业的团队，以高效率、低收费、精业务赢得客户好评。 如皋城区、各个乡镇、如皋港开发区均可办理，上门服务。
				<span style="display: block;float: right; color: blue"><a href="${appname }/nav.do?page=content&nav=1">[详细]</a></span>
			</div>
			<div class="innerCap">
				<div class="innerCapBody">联系我们</div>
			</div>
			<div class="fontText1">
				<ul style="list-style-type: none;">
					<li>地址：如皋市大润发对面，江苏银行北隔壁。</li>
					<li>常驻办事机构：如皋市民服务中心。</li>
					<li>咨询电话：0513-87176068 13773830048</li>
					<li>咨询QQ：469146212</li>
					<li>联系人：许先生
					<span style="display: block;float: right; color: blue"><a href="${appname }/nav.do?page=contact">[详细]</a></span>
					</li>
				</ul>
			</div>
		</div>

		<div class="middleFrame">
			<div class="innerCap">
				<div class="innerCapBody">经营范围</div>

			</div>
			<c:forEach items="${navs[2].cats }" var="cat">
							<div class="serviceDiv">
				<a href="${appname }/nav.do?page=content&nav=3&cat=${cat.catId }"><img src="${cat.image.filename }" /></a> <span
					class="serviceCap fontCap2"><a href="${appname }/nav.do?page=content&nav=2&cat=${cat.catId }">${cat.title }</a></span>
			</div>
			</c:forEach>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/tailer.jsp" ></jsp:include>
	<jsp:include page="/WEB-INF/righter.jsp" ></jsp:include>

</body>
</html>
