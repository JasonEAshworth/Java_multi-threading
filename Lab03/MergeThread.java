import java.io.*;
import java.util.*;

public class MergeThread implements Runnable
{
	public int[] valueArray;
	public int[] cloneAr;
	public int sizeofChunk;
	public int offset;
	public int numberRound;
	public int idNum;
	public ArrayList<Thread> threads;
	
	public MergeThread(int[] array, int chunkSize, int roundNum, int id, ArrayList<Thread> threadPool,int[] clone)
	{
		valueArray = array;
		offset = chunkSize;
		sizeofChunk = chunkSize;
		numberRound = roundNum;
		idNum = id;
		threads = threadPool;
		cloneAr = clone;
	}
	
	public void run()
	{
		int currRound = 0;
		int numThreads = threads.size();
		int last = numThreads-1;
		int[] currentArray = valueArray;
		int[] storeArray = cloneAr;
		while(currRound<numberRound)
		{
			if(currRound == 0)
			{	
				if(idNum == numThreads-1)
				{
					SequentialMergeSorter.sort2(currentArray,(currentArray.length),offset*idNum);
				}
				else
				{
					SequentialMergeSorter.sort2(currentArray,(sizeofChunk+(offset*idNum)), offset*idNum);
				}
			}
			if(currRound < numberRound)
			{
				int quux = (idNum & (1<<currRound));
				if(quux == 0)
				{		
					sizeofChunk*=2;
					Thread otherThread = threads.get((idNum | (1<<currRound)));
					try 
					{
						otherThread.join();
					} catch (InterruptedException e) 
					{
							// ignore
					}											
				}
				else if(quux > 0)
				{
					
					return;
				}
				
			}
			
			currRound++;
			last -= Math.pow(2,(currRound-1));
			if(idNum == last)
			{
				if(currRound == numberRound)
					SequentialMergeSorter.parallelSort(currentArray,offset*idNum,(sizeofChunk),storeArray,true,idNum,currRound);
				else
					SequentialMergeSorter.parallelSort(currentArray,offset*idNum,(sizeofChunk),storeArray,true,idNum, currRound);
			}
			else
			{
				SequentialMergeSorter.parallelSort(currentArray,offset*idNum,sizeofChunk,storeArray,false,idNum,currRound);
			}
			int[] tmp = currentArray;
			currentArray = storeArray;
			storeArray = tmp;
			
		}
		
		if(currentArray == cloneAr)
		{
			for(int i=0;i<valueArray.length;i++)
			{
               valueArray[i]=currentArray[i];
			}
		}
		return;
	}
}