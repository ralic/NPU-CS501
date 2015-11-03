/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw2q4_mergesort;

import java.util.Arrays;

/**
 *
 * @author raliclo
 */
public class MyMergeSort {

    private static int[] inputArray;
    private static int[] resultArray;
    private int[] tempArray;
    private int length;

    public MyMergeSort(int[] array) {
        inputArray = Arrays.copyOf(array, array.length);
        resultArray = array;
        tempArray = new int[array.length];
        length = 0;
    }

    public void printSort() {
        System.out.print("Original Array is    :");
        for (int i : inputArray) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println("\n");
        System.out.print("Merge Sort result is :");
        for (int i : resultArray) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println();
    }

    public void sort(int[] inputArr) {
        this.resultArray = inputArr;
        this.length = inputArr.length;
        spliter(0, length - 1);
    }

    private void spliter(int leftIndex, int rightIndex) {
//        System.out.println("> LeftIndex=" + leftIndex + ",RightIndex=" + rightIndex); // for debug
        if (leftIndex < rightIndex) {
            int middle = leftIndex + (rightIndex - leftIndex) / 2;
//            System.out.println(" > middle=" + middle);
            // Below step sorts the left side of the array
//            System.out.println(" > Spliting " + leftIndex + " ~ " + middle + "to smaller array");
            spliter(leftIndex, middle);
            // Below step sorts the right side of the array
//            System.out.println(" > Spliting " + (middle + 1) + " ~ " + rightIndex + "to smaller array");
            spliter(middle + 1, rightIndex);
            // Now merge both sides
            merger(leftIndex, middle, rightIndex);
        }
    }

    private void merger(int leftIndex, int middle, int rightIndex) {
//        System.out.println("tempArray[LeftIndex]=resultArray[leftIndex] from i= leftIndex to rightIndex");
        for (int i = leftIndex; i <= rightIndex; i++) {
//            System.out.println("tempArray[" + i + "]=" + tempArray[i] + "<= resultArray[" + i + "]=" + resultArray[i]);
            tempArray[i] = resultArray[i];
        }
        int i = leftIndex;
        int j = middle + 1;
        int k = leftIndex;
        while (i <= middle && j <= rightIndex) {
            if (tempArray[i] <= tempArray[j]) {
//                System.out.println("Compare tempArray[" + i + "]" + "<=" + "tempArray[" + j + "] is True");
//                System.out.println("tempArray[" + i + "]=" + tempArray[i] + "=> resultArray[" + k + "]=" + resultArray[k]);
                resultArray[k] = tempArray[i];
                i++;
            } else {
//                System.out.println("Compare tempArray[" + i + "]" + "<=" + "tempArray[" + j + "] is false");
//                System.out.println("tempArray[" + i + "]=" + tempArray[i] + "=> resultArray[" + k + "]=" + resultArray[k]);
                resultArray[k] = tempArray[j];
//                System.out.println("j++ : middlepoint move one more index:" + j + "=>" + (j + 1));
                j++;
            }
            k++;
        }

        while (i <= middle) {
//            System.out.println("tempArray[" + i + "]=" + tempArray[i] + "=> resultArray[" + k + "]=" + resultArray[k]);
            resultArray[k] = tempArray[i];
            k++;
            i++;
        }
    }
}
