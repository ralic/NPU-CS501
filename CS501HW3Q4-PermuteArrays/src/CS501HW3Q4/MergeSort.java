/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW3Q4;

/**
 *
 * @author raliclo
 */
public class MergeSort {

    private int[] inputArray;
    private int[] resultArray;
    private int[] tempArray;
    private int length;

    public MergeSort(int[][] array) {
        inputArray = array[0];
        length = array.length;
        resultArray = tempArray = new int[array.length];
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

    public void sort(int[] array) {
        inputArray = array;
        this.length = array.length;
        System.arraycopy(array, 0, resultArray, 0, array.length);
        this.tempArray = new int[length];
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
        // /*for debug
//      System.out.println("tempArray[LeftIndex]=resultArray[leftIndex] from i= leftIndex to rightIndex");
        // */fordebug
        for (int i = leftIndex; i <= rightIndex; i++) {
            // /* for debug
//            System.out.println("tempArray[" + i + "]=" + tempArray[i] + "<= resultArray[" + i + "]=" + resultArray[i]);
            // */ for debug
            tempArray[i] = resultArray[i];
        }
        int i = leftIndex;
        int j = middle + 1;
        int k = leftIndex;
        while (i <= middle && j <= rightIndex) {
            if (tempArray[i] <= tempArray[j]) {
                // /* fordebug
//                System.out.println("Compare tempArray[" + i + "]" + "<=" + "tempArray[" + j + "] is True");
//                System.out.println("tempArray[" + i + "]=" + tempArray[i] + "=> resultArray[" + k + "]=" + resultArray[k]);
                // */ for debug
                resultArray[k] = tempArray[i];
                i++;
            } else {
                // /* fordebug
//                System.out.println("Compare tempArray[" + i + "]" + "<=" + "tempArray[" + j + "] is false");
//                System.out.println("tempArray[" + i + "]=" + tempArray[i] + "=> resultArray[" + k + "]=" + resultArray[k]);
                // */ for debug
                resultArray[k] = tempArray[j];
//                System.out.println("j++ : middlepoint move one more index:" + j + "=>" + (j + 1));
                // */ for debug
                j++;
            }
            k++;
        }

        while (i <= middle) {
            // */ for debug
//            System.out.println("tempArray[" + i + "]=" + tempArray[i] + "=> resultArray[" + k + "]=" + resultArray[k]);
            // */ for debug
            resultArray[k] = tempArray[i];
            k++;
            i++;
        }

    }

    /**
     * @return the inputArray
     */
    public int[] getInputArray() {
        return inputArray;
    }

    /**
     * @return the resultArray
     */
    public int[] getResultArray() {
        return resultArray;
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
