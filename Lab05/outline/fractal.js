"use strict";

var cenX=0;
var cenY=0;
var halfSize=2;
var wbWorkers = [];
var ab;
var done=0;

function main(){
	done = 0;
    var cvs = document.getElementById("cvs");
    var ctx = cvs.getContext("2d");
	ab = new Uint8Array(cvs.width * cvs.height * 4);
    
    cvs.addEventListener("mousedown",function(evt)
	{
        var x = evt.clientX - cvs.offsetLeft;
        var y = evt.clientY - cvs.offsetTop;
        var percentX = x / cvs.width;
        var percentY = y / cvs.height;
        var minX = cenX-halfSize;
        var maxX = cenX+halfSize;
        var minY = cenY-halfSize;
        var maxY = cenY+halfSize;
        cenX = minX + percentX * (maxX-minX);
        cenY = minY + percentY * (maxY-minY);
        if( evt.button === 0 )
            halfSize = 0.8 * halfSize;
        else
            halfSize = 1.25 * halfSize;
        evt.preventDefault();
        compute();
        return false;
    });
    cvs.addEventListener("contextmenu",function(ev)
	{
        ev.preventDefault();
        return false;
    });
	
	for(var i = 0; i < 4; i++)
	{
		wbWorkers[i] = new Worker('worker.js');
		wbWorkers[i].addEventListener('message', function(e) 
		{
			done++;						
			ab.set(e.data[0], e.data[1] *cvs.width * 4);
			if(done ==4)
				showImage(cvs, ctx, ab);			
		}, false);
	}	
    compute();
}

function showImage(cvs, ctx, image)
{
	var imageData = ctx.createImageData(cvs.width, cvs.height);
	for(var i = 0; i<image.length; i++)
		imageData.data[i] = image[i];
	ctx.putImageData(imageData, 0,0);
	done = 0;
}

function compute(){
	var cvs = document.getElementById("cvs");
	var ctx = cvs.getContext("2d");

    var w=cvs.width;
    var h=cvs.height;
    var n=w*h*4;
    
    var maxi = parseInt(document.getElementById("maxiter").value);
    var minX = cenX-halfSize;
    var maxX = cenX+halfSize;
    var minY = cenY-halfSize;
    var maxY = cenY+halfSize;
    var dy = maxY-minY;
    var dx = maxX-minX;
	
	for (var i = 0; i < 4; i++)
	{
		wbWorkers[i].postMessage([w, h/4, minX, maxX, minY, maxY, maxi, dx, dy, (i * (h/4))]);
	}
}




