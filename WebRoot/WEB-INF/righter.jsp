<%@ page language="java" import="java.util.*,com.fjw.domain.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="wechatHolder">
		<div class="fontCap3" style="text-align: left;">联系客服</div>
		<img src="file/image/wechat-1.png" class="wechatImg" /> <img
			src="file/image/wechat-2.png" class="wechatImg" /> <a target="_blank"
			href="http://wpa.qq.com/msgrd?v=3&uin=469146212&site=qq&menu=yes"><img
			 src="file/image/qq1.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"
			class="qqImg" /></a> <a target="_blank"
			href="http://wpa.qq.com/msgrd?v=3&uin=295986584&site=qq&menu=yes"><img
			 src="file/image/qq2.jpg" alt="点击这里给我发消息" title="点击这里给我发消息"
			class="qqImg" /></a>

		<!-- http://wpa.qq.com/pa?p=2:295986584:51 -->

		<div class="fontCap3" style="margin-top: 30px;">
		<c:choose>
		<c:when test="${empty user }"><a href="${appname }/nav.do?page=login">管理员登录</a></c:when>
		<c:otherwise>
		欢迎：${user.username }<br/>
		<ul class="fontText5" style="list-style: none">
			<li><a href="${appname }/nav.do?page=goModify<c:if test="${not empty nav }">&nav=${nav.navId }</c:if><c:if test="${not empty cat }">&cat=${cat.catId }</c:if><c:if test="${not empty art }">&art=${art.artId }</c:if><c:if test="${not empty currPage }">&p=${currPage }</c:if>  " target="_blank">增删改查</a></li>
			<li><a href="${appname }/nav.do?page=changePassword" target="_blank">修改密码</a></li>
			<li><a href="${appname }/InitServlet">刷新网页</a></li>
			<li><a href="${appname }/nav.do?page=goBackupAndRestore">备份恢复</a></li>
			<li><a href="${appname }/nav.do?page=logout">安全退出</a></li>
		</ul>
		</c:otherwise>
		</c:choose>
		
		</div>
	</div>
</div>
