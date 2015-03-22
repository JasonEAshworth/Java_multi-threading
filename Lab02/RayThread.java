//Lab02
//Jason Ashworth

import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.lang.*;

public class RayThread implements Runnable
{
	public int cycleW;
	public int cycleH;
	public int offsHe;
	public int offsW;
	public int Height;
	public int Width;
	public BufferedImage biIm;
	public ArrayList<Sphere> sphereList;
	public int currThread;
	

	public int i;
	public int y;
	public int z;javac
	
	public RayThread(int offsH,int cyclesH, BufferedImage biMag, ArrayList<Sphere> spheres,int h, int w)
	{
		cycleH = cyclesH;
		offsHe = offsH;
		biIm = biMag;
		sphereList = spheres;
		Height = h;
		Width = w;
	}

	public void run()
	{
		Vec3 Lpos = new Vec3(0,0,0);
		Vec3 eye = new Vec3(0,0,0);
		
		for(y=offsHe;y<cycleH;y++)
		{
			double yv = (-2.0/(Height-1.0))*y+1.0;
			for(i=0;i<Width;i++)
			{
				double xv = (2.0/(Width-1.0))*i-1.0;
				Vec3 v = new Vec3(xv,yv,-1);
				double t = 1000;
				int index = -1;
				for(z=0;z<sphereList.size();z++)
				{
					double temp = sphereList.get(z).intersection(eye,v);
					if(temp > 0 && temp < t)
					{	
						t = temp;
						index = z;
					}
				}
				if(index > -1)
				{
					Vec3 ip = Vec.mul(t,v);
					Vec3 N = Vec.sub(ip,sphereList.get(index).c);
					Vec3 L = Vec.sub(Lpos,ip);
					N = Vec.normalize(N);
					L = Vec.normalize(L);
					double dp = Vec.dot(L,N);
					if(dp <0) dp = 0;
					Vec3 col = Vec.mul(dp,sphereList.get(index).color);
					Vec3 colFin = Vec.mul(255,col);
					if(colFin.x() > 255) colFin.x_ = 255.0;
					if(colFin.y() > 255) colFin.y_ = 255.0;
					if(colFin.z() > 255) colFin.z_ = 255.0;
					int daColor = 0xff000000 + (int)colFin.z() + ((int)colFin.x() << 16) + ((int)colFin.y() << 8);
					biIm.setRGB(i,y,daColor);
				}
				else
				{
					int color = 0;					
					biIm.setRGB(i,y,color);
				}
			}
		}
	}
}