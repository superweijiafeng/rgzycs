<%@ page language="java" import="java.util.*,com.fjw.domain.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String htmlData=null;
if(request.getAttribute("art")!=null) {
	htmlData=((Art)request.getAttribute("art")).getContent();
} else if(request.getAttribute("cat")!=null) {
	htmlData=((Cat)request.getAttribute("cat")).getContent();
} else if(request.getAttribute("nav")!=null) {
	htmlData=((Nav)request.getAttribute("nav")).getContent();
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>如皋智源财税咨询有限公司</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="css/modify.css">
<link rel="stylesheet" type="text/css" href="css/home.css" />
<link rel="stylesheet" type="text/css" href="css/global.css" />
<jsp:include page="header3.jsp" ></jsp:include>
<script type="text/javascript">
function changeFileEnable(){
	var newRadio=document.getElementById("newRadio");
	var imageFile=document.getElementById("imageFile");
	if(newRadio.checked==true) {
		imageFile.removeAttribute("disabled");
	} else {
		imageFile.setAttribute("disabled",true);
	}
}
</script>
	<link rel="stylesheet" href="${appname }/kindeditor/themes/default/default.css" />
	<link rel="stylesheet" href="${appname }/kindeditor/plugins/code/prettify.css" />
	<script charset="utf-8" src="${appname }/kindeditor/kindeditor-all-min.js"></script>
	<script charset="utf-8" src="${appname }/kindeditor/lang/zh-CN.js"></script>
	<script charset="utf-8" src="${appname }/kindeditor/plugins/code/prettify.js"></script>
	<script>
		KindEditor.ready(function(K) {
			var editor1 = K.create('textarea[name="content"]', {
				cssPath : '${appname }/kindeditor/plugins/code/prettify.css',
				uploadJson : '${appname }/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : '${appname }/kindeditor/jsp/file_manager_json.jsp',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['example'].submit();
					});
				}
			});
			prettyPrint();
		});
	</script>

</head>

<body>
	<jsp:include page="/WEB-INF/header.jsp"></jsp:include>
	<div class="bodydiv">
		<div style="height: 30px; margin-top: 10px; border-bottom: 1px dotted #e0e0e0;font-family: 微软雅黑;font-size: 16px;">现在修改的位置：
    			<span><span class="fontText1"><a href="${appname }/nav.do?page=goModify">首页</a></span></span>
    			<c:if test="${not empty nav }"><span class="fontText1">&gt;&gt; <a href="${appname }/nav.do?page=goModify&nav=${nav.navId }">${nav.title }</a></span></c:if>
    			<c:if test="${not empty cat }"><span class="fontText1">&gt;&gt; <a href="${appname }/nav.do?page=goModify&nav=${nav.navId }&cat=${cat.catId}">${cat.title }</a></span></c:if>
    			<c:if test="${not empty art }"><span class="fontText1">&gt;&gt; <a href="${appname }/nav.do?page=goModify&nav=${nav.navId }&cat=${cat.catId}&art=${art.artId}">${art.title }</a></span></c:if>
    		</div>
    		
    	<c:choose>
    	<c:when test="${not empty art }">
    	<c:set var="setVal" value="${art }"></c:set>
    	</c:when>
    	<c:when test="${not empty cat }">
    	<c:set var="setVal" value="${cat }"></c:set>
    	</c:when>
    	<c:when test="${not empty nav }">
    	<c:set var="setVal" value="${nav }"></c:set>
    	</c:when>
    	</c:choose>
    	
				<form method="POST" enctype="multipart/form-data"
					action="
					    	<c:choose>
					    	<c:when test="${not empty art }">
					    	${appname }/modify.do?nav=${setVal.navId}&cat=${setVal.catId}&art=${setVal.artId}
					    	</c:when>
					    	<c:when test="${not empty cat }">
					    	${appname }/modify.do?nav=${setVal.navId}&cat=${setVal.catId}
					    	</c:when>
					    	<c:when test="${not empty nav }">
					    	${appname }/modify.do?nav=${setVal.navId}
					    	</c:when>
					    	</c:choose>
					"
					class="form">
					<ul>
						<li class="modify1">——标题</li>
						<li><input class="modify2 longInput" type="text" name="title"
							<c:if test="${not empty setVal.title }">value="${setVal.title }"</c:if> /></li>
						<li class="modify1">——图片</li>
						<li class="modify3"><img src="${setVal.image.filename }" /></li>
						<li><input type="radio" name="image" value="old" onchange="changeFileEnable()" checked/>使用原图片 
							<input type="radio" name="image" value="new" onchange="changeFileEnable()" id="newRadio"/>使用新图片
							<input class="modify2" type="file" name="imageFile" id="imageFile" disabled/>
							<input type="radio" name="image" value="del" onchange="changeFileEnable()" />删除图片</li>
						<li class="modify1">——内容</li>
						<li>
						<textarea name="content" cols="100" rows="8" style="width:775px;height:200px;visibility:hidden;">
						<%=htmlspecialchars(htmlData)%>
						</textarea>
					</ul>
					<input type="submit" value="提交修改" style="width:100px"
						class="modify1" />
				</form>
	</div>

	<jsp:include page="/WEB-INF/tailer.jsp"></jsp:include>
</body>
</html>

<%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>
