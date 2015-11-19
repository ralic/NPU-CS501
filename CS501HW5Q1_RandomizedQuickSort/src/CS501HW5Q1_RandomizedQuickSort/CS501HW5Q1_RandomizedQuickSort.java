
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW5Q1_RandomizedQuickSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

/**
 *
 * @author raliclo
 * @version Please compile under JDK 8u66 +
 */
public class CS501HW5Q1_RandomizedQuickSort {

    void HW5Q1_content() {
//1. [25 Pts] Write a program in Java that performs the Randomized Quicksort algorithm.
//a) Create the randomizedPartition method. Use the pseudocode shown in the lecture.
//b) Implement the recursive randomizedQuicksort method.
//c) Test your codes by writing a program that uses the following input array:
//      int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
//d) Your output should display the following versions of the array:
//  I. The original input array
//  II. The sub-arrays after each of the randomizedPartition calls has completed
//  III. The final sorted array after performing the Quicksort algorithm
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {

        int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
        int[] array2 = Arrays.copyOf(array, array.length);
        long speedX = System.currentTimeMillis();
        // Print all elemetns as original
        System.out.println("I. The original input array");
        IntStream.of(array).forEach(p -> System.out.print(p + ","));

        // Print all elements after randomizedPartition
        System.out.println("\nTesting swap(array, 0, 4)");
        IntStream.of(swap(array, 0, 4)).forEach(p -> System.out.print(p + ","));
        System.out.println();

        System.out.println("\nTesting partition(array, 7, array.length)");
        System.out.println("Partion Index=" + partition(array, 7, array.length - 1) + " Array after partition:");
        IntStream.of(array).forEach(p -> System.out.print(p + ","));
        System.out.println();

        System.out.println("\nTesting randomizedPartition(array, 0, array.length-1)");
        randomizedPartition(array, 0, array.length - 1);
        IntStream.of(array).forEach(p -> System.out.print(p + ","));
        System.out.println();

        System.out.println("\nTesting myquicksort(array, 0, array.length-1)");
        myquicksort(array, 0, array.length - 1);
        IntStream.of(array).forEach(p -> System.out.print(p + ","));
        System.out.println();

        System.out.println("I. The original input array");
        IntStream.of(array2).forEach(p -> System.out.print(p + ","));

        System.out.println("\nTesting randomizedQuicksort(array2, 0, array.length-1)");
        randomizedQuicksort(array2, 0, array.length - 1);
        System.out.println("Final Sorted Array:");
        IntStream.of(array2).forEach(p -> System.out.print(p + ","));

        System.out.println("\nTime spent :" + (System.currentTimeMillis() - speedX));

    }

    {
        /*
Randomized version of Quicksort is a sorting algorithm of choice for large inputs
In this case, we use the random sampling technique
Instead of using  as the pivot , we randomly select an element from the sub-array 
select an element from the sub-array A[p...r] to swap with A[r]
Thus, by randomly choosing the pivot element, the split of the input array 
is expected to be reasonably well balanced
        
**/
    }

    {/* Pseudo Code
 The pseudocode for the randomized partition: randomizedPartition(A,p,r)
    1 i = random(p,r) 
    2 exchange A[r]with A[i]  
    3 return partition(A,p,r)
 The pseudocode for the randomized Quicksort: randomizedQuicksort(A,p,r)
    1 if p<r   
    2 q = randomizedPartition(A,p,r)
    3 quicksort(A,p,q-1)
    4 quicksort(A,q+1,r)}
**/
    }

    public void pseudo_randomizedPartition() {
        /* randomizedPartition(A,p,r)
            1 i = random(p,r) 
            2 exchange A[r]with A[i]  
            3 return partition(A,p,r)
         */
    }

    public static int randomizedPartition(int[] A, int p, int r) {
        int i = ThreadLocalRandom.current().ints(p, r + 1).limit(1).toArray()[0];
        System.out.println("A[" + i + "]:" + A[i] + " is selected as pivot index");
        A = swap(A, i, r);
        return partition(A, p, r);
    }

    public void pseudo_partiion() {
        /*
        Two cases for an iteration of partition procedure:
            a) If A[j] >x, the only action is to increment j
            b) If A[j] <=x , index  i is incremented,A[i] and A[j] are swapped, 
            and then j is incremented.
         */
 /*
    The pseudocode for the partition procedure:
        partition(A,p,r)
        1 x=A[r]
        2 i= p-1
        3   for j=p to r-1
        4       if A[j]<x  
        5       i=i+1
        6       exchange A[i] with A[j]
        7 exchange A[i+1] with A[r] 	
        8 return i+1
        
     This function rearranges the sub-array  A[p...r] in place
         */
    }

    public static int partition(int[] A, int p, int r) {
        int pivotX = A[r];
        int i = p - 1;
        for (int j = p; j <= r - 1; j++) {
            if (A[j] < pivotX) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, r);
        return i + 1;
        /*
        Two cases for an iteration of partition procedure:
            a) If A[j] >x, the only action is to increment j
            b) If A[j] <=x , index  i is incremented,A[i] and A[j] are swapped, 
            and then j is incremented.
         */
    }

    public static int[] swap(int[] A, int p, int r) {
        int tmp = A[r];
        A[r] = A[p];
        A[p] = tmp;
        return A;
    }

    void pseudo_randomizedQuicksort() {
        //randomizedQuicksort(A,p,r)
        //    1 if p<r   
        //    2 q = randomizedPartition(A,p,r)
        //    3 quicksort(A,p,q-1)
        //    4 quicksort(A,q+1,r)
    }

    public static void randomizedQuicksort(int[] A, int p, int r) {
        System.out.println("*Begin* with leftI=" + p + " rightI=" + r);
        if (p < r) {
            System.out.println("Sub Array :");
            int[] B = Arrays.copyOfRange(A, p, r); // Sub Array Range
            IntStream.of(B).forEach(pp -> System.out.print(pp + ","));
            System.out.println();
            int q = randomizedPartition(A, p, r);
            randomizedQuicksort(A, p, q - 1);
            randomizedQuicksort(A, q + 1, r);
        } else {
            System.out.println("--Do Nothing : left index>=right index ");
        }
    }

    void pseudo_quicksort_info() {/*
    Quicksort algorithm uses the divide-and-conquer technique for sorting an array 
    For sorting an array A[p...r]
    Divide – 
    Partition the array  A[p...r] into 2 sub-arrays
    A[p...q-1] && A[q+1...r]
    -- Determine the index q
    -- Each element of A[p...q-1]<=A[q]
    -- Each element of A[q+1...r]>=A[q]
    Conquer -
    Sort the 2 sub-arrays
    AA[p...q-1] && A[q+1...r]
    by making recursive call to quick sort
    Combine -
    Combine the sorted sub-array to get final result
         */
    }

    void pseudo_quicksort() {
        /*
        The pseudocode for the quicksort algorithm: 
            quicksort(A,p,r)
                1 if p<r
                2 q= partition(A,p,r)
                3 quicksort(A,p,q-1)
                4 quicksort(A,q+1,r)
        So, to sort an entire array , the initial call to quicksort method is
        quicksort(A,1,A.length)
         */
    }

    public static void myquicksort(int[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            myquicksort(A, p, q - 1);
            myquicksort(A, p + 1, r);
        }
    }
}

/*
CS501 – Advanced Structured Programming and Algorithms
Homework 5 (FALL 2015)
Please submit the homework via uploading through your student’s portal for the
course. Include the source code files and the outputs you get after running your
program when applicable.

1. [25 Pts] Write a program in Java that performs the Randomized Quicksort algorithm.
a) Create the randomizedPartition method. Use the pseudocode shown in the lecture.
b) Implement the recursive randomizedQuicksort method.
c) Test your codes by writing a program that uses the following input array:
int[] array = {4, 1, 3, 2, 16, 9, 10, 14, 8, 7};
d) Your output should display the following versions of the array:


I. The original input array
II. The sub-arrays after each of the randomizedPartition calls has completed
III. The final sorted array after performing the Quicksort algorithm
2. [25 Pts] Write a program in Java that performs the Counting Sort algorithm.
a) Create the countingSort method. Use the pseudocode shown in the lecture.
b) Test your codes by writing a program that uses the following input array:
int[] array_A = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2};
c) Your output should display the following versions of the arrays:
I. The original input array A
II. The counting array C after the counting (lines 4-5 in pseudocode) has completed
III. The counting array C after the summing (lines 7-8 in pseudocode) has completed
IV. The final sorted array B after performing the Counting Sort algorithm
 */
