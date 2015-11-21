
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication37;

import java.lang.*;
import java.util.stream.*;

/**
 *
 * @author raliclo
 */
public class CS501HW5Q2_CountingSort {

    void CS501HW5Q2_context() {
//    2. [25 Pts] Write a program in Java that performs the Counting Sort algorithm.
//a) Create the countingSort method. Use the pseudocode shown in the lecture.
//b) Test your codes by writing a program that uses the following input array:
//int[] array_A = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2};
//c) Your output should display the following versions of the arrays:
//I. The original input array A
//II. The counting array C after the counting (lines 4-5 in pseudocode) has completed
//III. The counting array C after the summing (lines 7-8 in pseudocode) has completed
//IV. The final sorted array B after performing the Counting Sort algorithm
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * @param speedX to monitor program runtime
         */
        // TODO code application logic here
        // for performance comparision
//            int n = 9;
//            int[] A = ThreadLocalRandom.current().ints(0, n).limit(99999999).toArray();
        int[] A = {6, 0, 2, 0, 1, 3, 4, 6, 1, 3, 2};
        int[] B = new int[A.length];
        int k = IntStream.of(A).parallel().max().getAsInt();
//            int k = IntStream.of(A).max().getAsInt();// for performance comparision
        long speedX = System.currentTimeMillis();

        System.out.print("\nA[]=");
        IntStream.of(A).forEach(p -> System.out.print(p + ","));
        System.out.println("\nMax number in A[] is :" + k);
//            System.out.println("A is of size :" + A.length);
        B = countingSort(A, B, k);
        System.out.println("\nAfter coutingSort...");
        System.out.print("B[]=");
        IntStream.of(B).forEach(p -> System.out.print(p + ","));
        System.out.println();

        System.out.println("Time spent :" + (System.currentTimeMillis() - speedX));

    }

    void pseudo_countingSortInfo() {
        /*
          Counting sort assumes that each of the input elements is 
          an integer in the range 0 to k (a small range)
          When k = O(n), the sort runs in Theta(n) time.
          For each element x, it determines the number of elements less than x
        ▪ The information is used to place element  directly into
        its position in the output array
        ▪ If 17 elements are less than , the  is put in position 18
        ▪ Special handling for elements having the same value
        
        Assume that the input is 
            A[1...n] (where n = A.length)
        
        The algorithm requires 2 other arrays:
            B[1...n] holds the sorted output
            C[0...k] provide temporary working/counting storage
         */
    }

    void pseudo_countingSort() {
        /*
        
        The for loop of lines 2-3 initializes  to all zeroes
        The for loop of lines 4-5 inspects each input element
        
        If the value of an input element is , increment by 1
        Thus, after line 5, C[i] holds the number of elements that
        have value =i for each integer i= 0, 1...k 
        
        Lines 7-8 determine for each   0, 1,…,  how many elements 
        are <=i by keeping a running sum of the array C 
        
        Finally, the for loop of lines 10-12 places each element into 
        its correct sorted position in the output array B 
         */

 /*     countingSort(A, B,k) 
        1 let C[0...k] be a new array
        2 for i=0 to k 
        3   C[i]=0
        4 for i=1 to A.length
        5   C[A[j]] = C[A[j]]+1
        6 // C[i] now contains the number of elements =i
        7 for i= 1 to k
        8    C[i] = C[i] + C[i-1]
        9 // C[i] now contains the number of elements <=i 
        10 for j= A.length down to 1
        11   B[C[A[j]]]= A[j]
        12   C[A[j]]=C[A[j]]-1
        
         */
    }

    public static int[] countingSort(int[] A, int[] B, int k) {
        int[] C = new int[k + 1];
        System.out.print("C[]=");
        IntStream.of(C).forEach(p -> System.out.print(p + ","));

        System.out.print("\nStart Counting...");
        IntStream.of(A).parallel().forEach(p -> {
//            System.out.print(p);
            C[p]++;
        });
        System.out.print("\nC[]=");
        IntStream.of(C).forEach(p -> System.out.print(p + ","));
        System.out.print("\nStart Accumulating...");
        IntStream.range(0, k + 1).forEach(p -> {
            if (p > 0) {
                C[p] = C[p] + C[p - 1];
            }
        });
        System.out.print("\nC[]=");
        IntStream.of(C).forEach(p -> System.out.print(p + ","));
        for (int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }
        return B;
    }

}
