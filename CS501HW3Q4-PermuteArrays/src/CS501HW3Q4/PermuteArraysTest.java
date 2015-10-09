/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW3Q4;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author raliclo
 */
public class PermuteArraysTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        // TODO code application logic here
        int[] inputarray = new int[20];
        int[] outputarray;
        int[] inputarray2 = new int[21];
        for (int i = 0; i < inputarray.length; i++) {
            inputarray[i] = (inputarray.length - i);

        }
        for (int i = 0; i < inputarray2.length; i++) {
            inputarray2[i] = i + 100;;
        }

        PermuteArrays pa = new PermuteArrays();
        pa.setinputkeyArray(inputarray);

        boolean iniFlag = true;
        int option;
        int maxErrorCount = 3;
        for (int count = 0; count <= maxErrorCount; count++) {
            // Selection Menu
            if (iniFlag) {
                System.out.print(
                        "(0)Setup & keyin array manually for 1st array \n"
                        + "(1)Print all elements in array\n"
                        + "(2)Print 1st array elements after using permuteBySorting\n"
                        + "(3)Print 2nd array elements after using randomizeInPlace\n"
                        + "(9)Exit\n"
                        + "Type in options: ");
                iniFlag = false;
            }
            Scanner in = new Scanner(System.in);
            //
            try {
                option = in.nextInt();
                // Provide options by switch&case
                switch (option) {
                    case 0:
                        pa.setListManually();
                        inputarray = null;
                        inputarray = pa.getinputkeyArray();
                        break;
                    case 1:
                        in = null;
                        System.out.print(">>Array Information(1)    : ");
                        for (int i = 0; i < inputarray.length; i++) {
                            System.out.print(inputarray[i] + " ");
                        }
                        System.out.println();
                        System.out.print(">>Array Information(2)    : ");
                        for (int i = 0; i < inputarray2.length; i++) {
                            System.out.print(inputarray2[i] + " ");
                        }
                        count = 0;
                        break;
                    case 2:
                        in = null;
                        pa.permuteBySorting(inputarray);
                        outputarray = new int[inputarray.length];
                        System.arraycopy(pa.getoutputkeyArray(), 0, outputarray, 0, inputarray.length);
                        System.out.print(">>After permuteBySorting(1)   : ");

                        for (int i = 0; i < outputarray.length; i++) {
                            System.out.print(outputarray[i] + " ");
                        }
                        count = 0;
                        break;
                    case 3:
                        in = null;
                        pa.randomizeInPlace(inputarray2);
                        outputarray = new int[inputarray2.length];
                        System.arraycopy(pa.getoutputkeyArray(), 0, outputarray, 0, inputarray2.length);
                        System.out.print(">>After randomizeInPlace(2)   : ");
                        for (int i = 0; i < outputarray.length; i++) {
                            System.out.print(outputarray[i] + " ");
                        }
                        count = 0;
                        break;
                    case 8:
                        pa = new PermuteArrays();
                        break;
                    case 9:
                        count = maxErrorCount;
                        System.out.print("\nThanks for using this program!");
                        break;
                    default:
                        iniFlag = true;
                        if (count == 0) {
                            count++;
                        }
                        System.out.print("#Error option provided, count=" + count);
                        if (count == maxErrorCount) {
                            System.out.print("\n**Too Many Errors !!**");
                            throw (new InputMismatchException());
                        }
                }

            } catch (Exception e) {
                System.out.println("\nWARNING : Input type Error, please enter integer within available option!!\n");
                System.exit(0);
            }
            //Outside switch & try/catch
            System.out.println();
        }
    }
}
