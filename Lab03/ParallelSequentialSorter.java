
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.lang.*;


public class ParallelSequentialSorter
{
	public static int numThread;
	public static double roundNumber;
	public static int[] initialArray;
	public static double chunkSize;
	
	public static void sort(int[] initArray,int numThreads)
	{	
		int numThread = numThreads;
		int[] initialArray = initArray;
		chunkSize = Math.floor(initArray.length/numThread);
		roundNumber = Math.floor((Math.log(numThread))/(Math.log(2)));
		int[] cloneArray = new int[initialArray.length];
		ArrayList<Thread> ThreadPool = new ArrayList<Thread>();
		
		
		for(int i=0;i<numThread;i++)
		{
			MergeThread mt = new MergeThread(initialArray,(int)Math.floor(chunkSize),(int)Math.floor(roundNumber),i,ThreadPool,cloneArray);
			Thread t = new Thread(mt,"" + i);
			ThreadPool.add(t);
		}
			
		for(int i=0;i<ThreadPool.size();i++)
		{
			ThreadPool.get(i).start();			
		}
		
		try
		{
			ThreadPool.get(0).join();
		}
		catch (InterruptedException e) 
		{
			// ignore
		}	
	}
}

