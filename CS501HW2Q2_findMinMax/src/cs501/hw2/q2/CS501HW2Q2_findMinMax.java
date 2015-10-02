/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501.hw2.q2;

/**
 *
 * @author raliclo
 */
public class CS501HW2Q2_findMinMax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] array = {95, 70, 82, 125, 48, 63, 18, 53};
        System.out.println("The smallest number is :" + findMin(array));
        System.out.println("The largest number is " + findMax(array));
    }

    public static int findMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static int findMin(int[] array) {
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}
/*
Write a program in Java that finds the smallest and the largest numbers in an array of  numbers.
Use the following array in your program as a test case: 
    int[] array = {95, 70, 82, 125, 48, 63, 18, 53};
The program should display the output of the result as shown below: 
                The smallest number is 18
		The largest number is 125
 */
