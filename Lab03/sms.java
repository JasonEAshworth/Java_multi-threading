
import java.io.*;
import java.util.*;

public class sms
{
    private static int[] clone(int[] X)
	{
        int[] Y = new int[X.length];
        for(int i=0;i<X.length;++i)
		{
            Y[i]=X[i];
        }
        return Y;
    }
    
    private static void dump(int[] X)
	{
        for(int i=0;i<X.length;++i){
            System.out.print(X[i]+", ");
        }
        System.out.println();
    }
    public static void main(String[] args)
	{
        Random r = new Random();
        
		
        int incr=1001;
        for(int asize=1000;asize<16000000;asize += incr, incr*=2)
		{
            int n=asize; 
            int[] X = new int[n];
            for(int j=0;j<X.length;++j)
			{
                X[j]=r.nextInt(1000);
            }
            
            int[] R = clone(X);
            int[] S = clone(X);
            int[] P = clone(X);
        
            long st,en;
            st=System.currentTimeMillis();
            java.util.Arrays.sort(X);
            en=System.currentTimeMillis();
            long builtin = en-st;
            
            st=System.currentTimeMillis();
            RecursiveMergeSorter.sort(R);
            en=System.currentTimeMillis();
            long recursive=en-st;
            
            st=System.currentTimeMillis();
            SequentialMergeSorter.sort(S);
            en=System.currentTimeMillis();
            long sequential = en-st;
            
			int[] np = new int[]{2,4,8,16};
			long[] parallel = new long[np.length];
            
			for(int i=0;i<np.length;++i)
			{
                st=System.currentTimeMillis();
                ParallelSequentialSorter.sort(P,np[i]);
                en=System.currentTimeMillis();
                parallel[i]=en-st;
            }          

            for(int i=0;i<np.length;++i)
			{       
				//System.out.println("Time to Complete: " +parallel[i]);
				System.out.println("Speed up with "+np[i]+" threads and "+n+" size array: "+builtin * 1.0 / parallel[i]);
            }
            //System.out.println("Built in: " +builtin+ ", Recursive: " + recursive + ", Sequential: " + sequential);
            
            compare(X,R);
            compare(X,S);
            compare(X,P);
        }
    }
    
    static void compare(int[] X, int[] Y)
	{
        if(X.length != Y.length)
            throw new RuntimeException("Bad 1");
        for(int i=0;i<X.length;++i)
		{
            if( X[i] != Y[i] )
			{
                for(int j=0;j<X.length;++j)
				{
                   System.out.print("X["+j+"]="+X[j]+" and Y["+j+"]="+Y[j]+"\n");
                }
				System.out.println();
                throw new RuntimeException("Bad 2 at "+i);
            }
        }
    }
}

        
