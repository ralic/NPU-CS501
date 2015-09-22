/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501.InsertionSort;

/**
 *
 * @author raliclo
 */
public class CS501InsertionAlogrithm {

    /**
     * @param args the command line arguments
     */
    public static int[] array = {100, 250, 30, 425, 5, 80, 900, 375};

    public static void main(String[] args) {
        int i, j = 0;
        System.out.println("Before Sort:");
        printarray();
        arrayInsertionSort(array);
        System.out.print("After Sort:\nInsertion Sort result is ");
        printarray();
        // TODO code application logic here
    }

    public static int[] arrayInsertionSort(int[] input) {

        int temp;
        for (int i = 1; i < input.length; i++) {
            for (int j = i; j > 0; j--) {
                if (input[j] < input[j - 1]) {
                    temp = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = temp;
                }
            }
        }
        return input;
    }

    public static void printarray() {
        int i;
        for (i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }

}
