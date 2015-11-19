/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501_combination;

/**
 *
 * @author raliclo
 */
/*
 	Write a program in Java that calculates this number of combinations. Use the following values as your test case (then, do the calculation manually to verify the result from the program):
	int n = 8;
 	int k = 3;

 	The output should say:

 	The number of combinations for 8 items taken 3 at a time is
 	[display the result]
 */
public class CS501_Combination {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        System.out.println(factorial(8));
//        System.out.println(factorial(3));
//        System.out.println(factorial(5));
        System.out.println(combination(8, 3));
    }

    public static int combination(int n, int k) {
        return (factorial(n) / (factorial(k) * factorial(n - k)));
    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return (factorial(n - 1) * n);
        }
    }
}
