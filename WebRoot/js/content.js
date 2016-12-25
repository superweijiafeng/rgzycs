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
				"/nav.do?page=content&nav="+
				document.getElementById("navId").value+
				"&cat="+
				document.getElementById("catId").value+
				"&p="+toPage;
	window.open(uri,"_self");
	return true;
}