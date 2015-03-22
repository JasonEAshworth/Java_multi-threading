//Lab02
//Jason Ashworth

import java.util.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;

public class RayTracer
{
	// Input/Output
	public FileInputStream in;
	public Scanner ins;
	public int numbofThreads;	
	// Storage
	public ArrayList<Sphere> spheres;
	public ArrayList<Thread> ThreadPool;
	public String fn;
	public int Height;
	public int Width;
	public int i;
	public long startTime;
	public long endTime;
	
	public RayTracer(String fname, int h, int w, int numThreads)
	{
		fn = fname;
		Height = h;
		Width = w;
		spheres = new ArrayList<Sphere>();
		ThreadPool = new ArrayList<Thread>();
		numbofThreads = numThreads;
		SetUp();
	}
	
	public void SetUp()
	{
		try
		{
			FileInputStream in = new FileInputStream(fn);
			Scanner ins = new Scanner(in);
			while(ins.hasNext())
			{
				if(ins.hasNext("sphere"))
				{
					ins.next();
				}
				// Get the center of sphere
				Vec3 sCenter = Vec.read(ins);				
				// get radius of sphere
				Double rad = ins.nextDouble();				
				// get color of sphere
				Vec3 color = Vec.read(ins);				
				// create sphere and place it into array
				spheres.add(new Sphere(sCenter,rad,color));
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File could not be found");
		}
		catch(Exception e)
		{
			System.out.println("It was something else...");
		}
	}
	public void trace()
	{
		BufferedImage bi = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);
		for(i=0;i<numbofThreads;i++)
		{
			int offsetH = (i * Height/numbofThreads);
			int cycleNum = ((i+1) * Height/numbofThreads);
			RayThread w = new RayThread(offsetH,cycleNum,bi,spheres,Height,Width);
			Thread t = new Thread(w,"" + (i+1));
			t.start();
			ThreadPool.add(t);
		}
		try
		{
			for(i=0;i<numbofThreads;i++)
			{
				
				ThreadPool.get(i).join();
			}
		}
		catch(InterruptedException e)
		{
			System.out.println("Interrupted Exception");
		}
		
		File f = new File("rtImage.png");
		try
		{
			ImageIO.write(bi,"png",f);
		}
		catch(IOException e)
		{
			System.out.println("Error Writing the file.");
		}
		
	}
	public static void main(String[] args)
	{
		
		
		if (args.length < 1) {
			System.out.println("Error, Not enough input parameters!");
		}
		
		RayTracer rt = new RayTracer(args[0],512,512,Integer.parseInt(args[1]));
		long startTime = System.currentTimeMillis();
		rt.trace();
		long endTime = System.currentTimeMillis();
		System.out.println("Process completed in " + (endTime - startTime) + " milliseconds");
		
		PrintWriter pw = null;

		 try {
		    File file = new File("out.txt");
		    FileWriter fw = new FileWriter(file, true);
		    pw = new PrintWriter(fw);
		    pw.println("File: " + args[0] + " Threads: "+ args[1] + "  Time: " + (endTime - startTime) + " milliseconds");
		 } 
		 catch (IOException e) {
		    e.printStackTrace();
		 } 
		 finally {
		    if (pw != null) {
		    	    pw.close();
		    }
		 }
		
		
		
	}
}