


class RecursiveMergeSorter
{
    public static void sort(int[] X)
	{
        if( X.length < 2)
            return;
        int n = X.length/2;
        int[] B = new int[n];
        for(int i=0;i<n;++i)
		{
            B[i]=X[i];
        }
        int[] C = new int[X.length-n];
        for(int i=n;i<X.length;++i)
		{
            C[i-n]=X[i];
        }
        sort(B);
        sort(C);
        int i=0,j=0,k=0;
        for(; i<B.length && j<C.length;++k)
		{
            if(B[i] <= C[j])
                X[k]=B[i++];
            else
                X[k]=C[j++];
        }
        while(i<B.length)
            X[k++]=B[i++];
        while(j<C.length)
            X[k++]=C[j++];
        return;
    }
}
