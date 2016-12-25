function RoundHolder(){
	this.holder=document.getElementById("roundHolder");
	this.img=document.getElementById("roundImg");
	if(this.img.clientWidth>=1000) {
		this.img2=document.getElementById("roundImg2");
		this.img2.outerHTML=this.img.outerHTML;
		this.img2.id="roundImg2";
		this.dir=-1;
	}
	else {
		this.dir=0;
	}
	this.currX=0;
}

var roundHolder;
var roundTimer;

function RoundHolderNext(){
	roundHolder.currX+=roundHolder.dir;
	if(roundHolder.currX+roundHolder.img.clientWidth<0) {
		roundHolder.currX+=roundHolder.img.clientWidth;
	}
	roundHolder.holder.style.left=roundHolder.currX+"px";
}

function stopRound(){
	window.clearInterval(roundTimer);
}

function startRound(){
	roundTimer=window.setInterval(RoundHolderNext,20);
}

function initAd2(){
	roundHolder=new RoundHolder();
	startRound();
};

var ad2View;

function showAd2(id) {
	ad2View=document.getElementById("ad2View");
	var imgsrc="file/image/ad2/round"+id+".jpg";
	if(ad2View.children.length==0) {
		var img=document.createElement("img");
		img.src=imgsrc;
		img.style.height="600px";
		ad2View.appendChild(img);
		
		var imgclose=document.createElement("img");
		imgclose.src="file/image/close.png";
		imgclose.style.position="absolute";
		imgclose.style.right="0px";
		imgclose.style.width="20px";
		//imgclose.onclick=closeAd2;
		
		var aimg=document.createElement("a");
		aimg.href="#";
		aimg.appendChild(imgclose);
		aimg.onclick=closeAd2;
		ad2View.style.overflow="hidden";
		ad2View.style.height="0px";
		startIncreaseHeight();
		ad2View.appendChild(aimg);

	} else if(ad2View.children[0].src.lastIndexOf(imgsrc)!=-1) {
		closeAd2();
	} else {
		ad2View.children[0].src=imgsrc;
	}	
}

function closeAd2(){
	startDecreaseHeight();
	return false;
}

var height=0;
var speed=1;
var timer;

function startIncreaseHeight(){
	height=0;
	speed=1;
	timer=null;
	increaseHeight();
}

function increaseHeight(){
	height+=speed;
	ad2View.style.height=height+"px";
	if(height+speed<300) {
		speed+=1;
	} else {
		speed=(610-height)/10;
	}
	if(height+speed<600) {
		timer=window.setTimeout(increaseHeight, 10);
	} else {
		ad2View.style.height="600px";
		window.clearTimeout(timer);
	}
}

function startDecreaseHeight(){
	speed=1;
	height=parseInt(ad2View.style.height);
	timer=null;
	decreaseHeight();
}

function decreaseHeight() {
	height-=speed;
	ad2View.style.height=height+"px";
	if(height+speed>300) {
		speed+=1;
	} else {
		speed=(10+height)/10;
	}
	
	if(height-speed>=0) {
		timer=window.setTimeout(decreaseHeight, 10);
	} else {
		ad2View.style.height="0px";
		window.clearTimeout(timer);
		while(ad2View.children.length>0) {
			ad2View.removeChild(ad2View.children[0]);
		}
	}
}
