<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="css/map.css">
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<script type="text/javascript" src="http://api.map.baidu.com/api?key=&v=1.1&services=true"></script>
<script type="text/javascript" src="js/map.js"></script>
<jsp:include page="header3.jsp" ></jsp:include>
</head>

<body 
	onload='initMap("dituContent",120.577424,32.387279,"如皋智源财税咨询有限公司","如皋市大润发对面,江苏银行北隔壁");
		initMap("dituContent2",120.577824,32.386929,"行政服务中心","")'>
	<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
	<div class="bodydiv">
		<jsp:include page="/WEB-INF/header2.jsp"></jsp:include>
		<div class="innerCap">
			<div class="innerCapBody">联系我们</div>
		</div>
		<div id="dituContent"></div>
		<div class="dituInfo">
		<ul style="list-style: none;">
		<li class="fontMapHead">如皋总公司</li>
		<li>地址：如皋市大润发对面江苏银行北隔壁</li>
		<li>电话：0513-87176068/13773830048</li>
		<li>　　　13912213956</li>
		<li>邮箱：469146212@qq.com</li>
		<li>联系QQ：469146212</li>
		<li>　　　　295986584</li>
		<li>联系人：许先生/冯女士</li>
		</ul>
		</div>
		
		<div style="clear: both;height: 2px;background-color: #e0e0e0;"></div>
		<div id="dituContent2"></div>
		<div class="dituInfo">
		<ul style="list-style: none;">
		<li class="fontMapHead">行政服务中心</li>
		<li>地址：</li>
		</ul>
		</div>
		
		
		
	</div>
	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/righter.jsp"></jsp:include>

</body>
</html>
