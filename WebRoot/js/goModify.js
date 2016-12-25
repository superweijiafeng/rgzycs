function confirmDelArt(title){
	return window.confirm("确认删除该文章？该文章标题为："+title);
};

function confirmDelCat(title,arts) {
	var hint="确认删除该栏目？栏目的标题为："+title+"\n这将删除该栏目下的所有文章";
	return window.confirm(hint);
};

function jump(){
	var allPages=parseInt(document.getElementById("allPages").value);
	var toPage=document.getElementById("jump").value;
	if(toPage.length==0 || isNaN(toPage)==true) {
		window.alert("页数必须为数字");
		return false;
	}
	toPage=parseInt(toPage);
	if(toPage<0 || toPage>allPages) {
		window.alert("页数超出可选范围：1-"+allPages);
		return false;
	}
	var uri=document.getElementById("appname").value+
				"/nav.do?page=goModify&nav="+
				document.getElementById("navId").value+
				"&cat="+
				document.getElementById("catId").value+
				"&p="+toPage;
	window.open(uri,"_self");
	return true;
}