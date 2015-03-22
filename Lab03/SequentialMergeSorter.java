import java.util.Arrays; 

class SequentialMergeSorter
{
    
    private static int min(int a, int b)
	{
        return (a<b)?a:b;
    }
    
    private static void dump(int[] A)
	{
        for(int i=0;i<A.length;++i)
		{
            System.out.print(A[i]+" ");
        }
        System.out.println();
    } 
    
    public static void sort(int[] A)
	{
        int[] B = new int[A.length];
        int[] s=A;
        int[] d=B;
        int cs=1;
        
        while( cs < A.length )
		{
            for(int s1=0;s1<A.length;s1+=cs*2)
			{
                int i=s1;
                int e1 = min(s1+cs,A.length);
                int j=e1;
                int e2 = min(j+cs,A.length);
                int k=s1;
                
                while( i<e1 && j<e2)
				{
                    if( s[i] <= s[j] )
                        d[k++] = s[i++];
                    else
                        d[k++] = s[j++];
                }
                
                while( i<e1 )
                    d[k++] = s[i++];
                while( j<e2 )
                    d[k++] = s[j++];
            }
            
            int[] tmp = s;
            s=d;
            d=tmp;
            cs *= 2;
        }
        
        if( s == B )
		{
            for(int i=0;i<s.length;++i)
                A[i]=s[i];
        }
    }
	public static void sort2(int[] A, int chunkS, int offset)
	{
		Arrays.sort(A,offset,chunkS);
	}
	
	public static void parallelSort(int[] A,int offset,int sizeChunk,int[] clone,boolean endOfArray, int id, int r)
	{
		int size,rightSide;
		double e2;
		if(endOfArray)
		{
			size = A.length;
			rightSide = (A.length-offset)+1;
		}
		else
		{
			size = sizeChunk+offset;
			rightSide = sizeChunk;
		}
        for(int s1=offset;s1<size;s1+=rightSide)
		{
            int i=s1;
            int e1 = min(s1+sizeChunk/2,A.length);
            int j=e1;
			if(endOfArray)
				e2 = A.length;
			else
				e2 = min((int)(Math.ceil(j+rightSide/2.0)),A.length);
            int k=s1;
            
            while( i<e1 && j<e2)
			{
                if( A[i] <= A[j] )
				{
					clone[k++] = A[i++];
				}					
                else
				{
					clone[k++] = A[j++];
				}
					
            }
            while( i<e1 )
			{
                clone[k++] = A[i++];
			}
            while( j<e2 )
			{
                clone[k++] = A[j++];
			}
		}
            
    }
}

