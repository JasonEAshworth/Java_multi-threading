//Web Worker 'class'
'use strict';

var done=0;

self.addEventListener('message', function(e) {
    var w=e.data[0];
    var h=e.data[1];
    var n=w*h*4;
    var ab = new Uint8Array(n);
    
    var maxi = e.data[6];
    var minx = e.data[2];
    var maxx = e.data[3];
    var miny = e.data[4];
    var maxy = e.data[5];
    var dy = e.data[8];
    var dx = e.data[7];
	
    var idx=0;
    for(var yi=e.data[9];yi<e.data[9] + h;++yi)
	{
        var y = miny + yi/(h*4) * dy;
        for(var xi=0;xi<w;++xi)
		{
            var x = minx + xi/w * dx;
            var iter = iterations_to_infinity(x,y,maxi);
            var c = map_color(iter,maxi);
            ab[idx++]=c[0];
            ab[idx++]=c[1];
            ab[idx++]=c[2];
            ab[idx++]=255;
        }
    }
	
		self.postMessage([ab, e.data[9]]);
	
}, false);

function add(a,b)
{
    return [ a[0]+b[0], a[1]+b[1] ];
}


function mul(a,b)
{
    return [ 
        a[0]*b[0]-a[1]*b[1],
        a[0]*b[1]+a[1]*b[0] 
			];
}


function mag2(a)
{
    return a[0]*a[0] + a[1]*a[1];
} 

function iterations_to_infinity(x,y,maxi)
{
    var c = [x,y];
    var z = [0,0];
    for(var k=0;k<maxi;++k)
	{
        z = mul(z,z);
        z = add(z,c);
        if( mag2(z) > 4 )
            return k;
    }
    return maxi;
}

function map_color(k,MAX_ITERS)
{
    //N. Schaller's algorithm to map
    //HSV to RGB values.
    //http://www.cs.rit.edu/~ncs/color/t_convert.html

    var s = 0.8;     //saturation
    var v = 0.95;    //value
    var h = (k*1.0/MAX_ITERS) * 360.0 / 60.0;       //hue
    
    if( k >= MAX_ITERS )
        v=0;
        
    var ipart = Math.floor(h);
    var fpart = h-ipart;
    var A = v*(1-s);
    var B = v*(1-s*fpart);
    var C = v*(1-s*(1-fpart));
    var r,g,b;
    if( ipart == 0 )
	{
        r=v; g=C; b=A;
    }
    else if( ipart == 1)
	{
        r=B; g=v; b=A;
    }
    else if( ipart == 2 )
	{
        r=A; g=v; b=C;
    }
    else if( ipart == 3)
	{
        r=A; g=B; b=v;
    }
    else if( ipart == 4 )
	{
        r=C; g=A; b=v;
    }
    else
	{
        r=v; g=A; b=B;
    }
    var ri,gi,bi;
    ri=Math.floor(r*255);
    gi=Math.floor(g*255);
    bi=Math.floor(b*255);
    return [ri,gi,bi,255];
}








