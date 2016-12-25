function CircleShow(){
	this.showImg=document.getElementsByName("show");
	this.currIndex=0;
	this.currOpacity=1;
	
	if(this.showImg.length==0) {
		console.log("1");
		return;
	} else{
		this.showImg[0].style.opacity=1;
	}
	
	this.next=function(){
		var i=this.currIndex%this.showImg.length;
		var j=(this.currIndex+1)%this.showImg.length;
		this.showImg[i].style.opacity=this.currOpacity;
		this.showImg[j].style.opacity=1-this.currOpacity;
		if(this.currOpacity<=0) {
			this.currIndex++;
		}
	};
}

var circleShow;
var timeoutNext=null;
var timeoutRound=null;

function circleShowNext(){
	if(circleShow.currOpacity>0) {
		circleShow.currOpacity-=0.02;
		circleShow.next();
		if(timeoutNext!=null){
			window.clearTimeout(timeoutNext);
		}
		timeoutNext=window.setTimeout(circleShowNext, 20);
	} else {
		circleShow.currOpacity=1;
	}
}

function circleShowRound(){
	if(timeoutRound!=null) {
		window.clearTimeout(timeoutRound);
	}
	timeoutRound=window.setTimeout(circleShowRound, 5000);
	circleShowNext();
}

function init(){
	circleShow=new CircleShow();
	circleShowRound();
}

