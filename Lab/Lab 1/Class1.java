import java.util.*;

public class Class1{
    static int S = 10;
    static long keycomp = 0;
    static int Lsize = 10000;   //size of input array
    public static ArrayList<Integer> mergeSort(ArrayList<Integer> L)
    {
        int n = L.size();

        if (n<=1)
        {
            return L;
        }
        else if (n<=S)
        {
            return insertionSort(L);
        }
        else
        {
            int mid = n/2;
            ArrayList<Integer> unsortedLeft = new ArrayList<Integer>(L.subList(0,mid));
            ArrayList<Integer> unsortedRight = new ArrayList<Integer>(L.subList(mid+1,n));

            ArrayList<Integer> sortedLeft = mergeSort(unsortedLeft); 
            ArrayList<Integer> sortedRight = mergeSort(unsortedRight);

            ArrayList<Integer> result = merge(sortedLeft, sortedRight);
            return result;
        }
    }

    public static ArrayList<Integer> merge(ArrayList<Integer> Left, ArrayList<Integer> Right)
    {
        ArrayList<Integer> R = new ArrayList<Integer>();
        int leftN = Left.size();
        int rightN = Right.size();
        int posL = 0;
        int posR = 0;

        while(posL<leftN & posR<rightN)
        {
            if(Left.get(posL) < Right.get(posR))
            {
                keycomp++;
                R.add(Left.get(posL));
                posL++;
            }
            else if(Left.get(posL) == Right.get(posR))
            {
                keycomp++;
                R.add(Left.get(posL));
                posL++;
                R.add(Right.get(posR));
                posR++;
            }
            else
            {
                keycomp++;
                R.add(Right.get(posR));
                posR++;
            }
        }

        if (posL==leftN & posR==rightN) return R;
        else if (posL == leftN) 
        {
            R.addAll(Right.subList(posR, rightN));
        }
        else
        {
            R.addAll(Left.subList(posL, leftN));
        }
        return R;
    }

    public static ArrayList<Integer> insertionSort(ArrayList<Integer> L)
    {
        int n = L.size();
        for(int i=0; i<n; i++)
        {
            for (int j=i; j>0; j--)
            {
                keycomp++;
                if (L.get(j) < L.get(j-1))
                {
                    Collections.swap(L, j, j-1);
                }
                else break;
            }
        }
        return L;
    }

    public static void main(String[] args)
    {
        // ArrayList<Integer> L = new ArrayList<Integer>(
        //     Arrays.asList(3,5,1,2,8,7,6,4));

            ArrayList<Integer> L = new ArrayList<Integer>(Lsize);
            
            Random rand = new Random();
            for (int i=0; i<Lsize; i++)
            {
                 L.add(rand.nextInt(Lsize - 0));   //generated numbers in array is from 0 to Lsize
            }

            // //create array for worst case (decreasing order)
            // for (int i=Lsize; i>0; i--)
            // {
            //     L.add(i);
            // }

        //System.out.println("Before sorting: ");
        //System.out.println("List :\n" + L.toString() + "\n");

        long startTime = System.nanoTime();     //start timer

        L = mergeSort(L);

        long endTime   = System.nanoTime();    //end timer
        long totalTime = endTime - startTime;  //duration of code execution

        //System.out.println("After sorting: ");
        //System.out.println("List :\n" + L.toString());

        System.out.println("Number of key comparisons: " + keycomp);
        System.out.println("Time taken for sorting: " + totalTime + "ns");
    }
}