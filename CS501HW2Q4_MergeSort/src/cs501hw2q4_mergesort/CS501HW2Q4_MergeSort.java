/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw2q4_mergesort;

/**
 *
 * @author raliclo
 */
public class CS501HW2Q4_MergeSort {

    public static void main(String a[]) {

        int[] array = {95, 70, 82, 125, 48, 63, 18, 53};
        MyMergeSort mms = new MyMergeSort(array);
        mms.sort(array);
        mms.printSort();
    }

}

/*
Conceptually, a merge sort works as follows:
Divide the unsorted list into n sublists, each containing 1 element
(a list of 1 element is considered sorted).
Repeatedly merge sublists to produce new sorted sublists
until there is only 1 sublist remaining. This will be the sorted list.
 */

/*
 We discussed in class how Merge Sort works.
Write a program that performs the Merge Sort algorithm.
Use the same array in problem 2 as your test case for the program: 
int[] array = {95, 70, 82, 125, 48, 67, 18, 53};
The program should display the output of the result as shown below: 
Merge Sort result is 18, 48, 53, 67, 70, 82, 95, 125
 */
