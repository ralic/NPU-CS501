/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW3Q4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author raliclo
 */

/*
4.	In the class, we discussed 2 techniques for randomly permuting arrays –
Permute by Sorting and Randomize in Place.
Create a Java class/program called PermuteArrays which contains 2 methods:

1)[15 Pts]
permuteBySorting method that
a.	 Has this header
public static void permuteBySorting(int[] list)
b.	It takes an array of integers as argument and permutes the elements in the array by
applying the Permute by Sorting algorithm
c.	In the Permute Arrays class main method, test the permuteBySorting method
i.	Generate an array of integers with values from 1 to 20
ii.	Display the original array on the screen
iii.	Call the permuteBySorting method and pass this array as the argument
iv.	Display the array again after it got randomized

2)[15 Pts]
randomizeInPlace method that has this header
public static void randomizeInPlace(int[] list)
a.	It takes an array of integers as argument and permutes the elements in the array by applying the Randomize in Place algorithm
b.	In the Permute Arrays class main method, test the randomizeInPlace method
a.	Generate another array of integers with values from 100 to 120
b.	Display the original array on the screen
c.	Call the randomizeInPlace method and pass this array as the argument
d.	Display the array again after it got randomized
 */
public class PermuteArrays {

    private static int[] inputkeyArray;
    private static int[] outputkeyArray;
    private static int[] indexArray;
    private static List<KeyIndexObject> keyIndexedList;
    private List<Integer> newlist = new ArrayList();

    PermuteArrays() {
    }

    public static void permuteBySorting(int[] list) {
        indexArray = new int[list.length];
        outputkeyArray = new int[list.length];
        inputkeyArray = list;
        keyIndexedList = new ArrayList();

        for (int i = 0; i < list.length; i++) {
            if (i == 0) {
                System.out.print(">>Original  Array             : ");
            }
            System.out.print(inputkeyArray[i] + " ");
            //indexArray Randomizer
            indexArray[i] = (int) (Math.pow((list.length), 3) * Math.random());
            // Build up ArrayList with in the list.length
            KeyIndexObject insertObj = new KeyIndexObject(inputkeyArray[i], indexArray[i]);
            keyIndexedList.add(insertObj);

            //Exit if i reaches the max Index.
            if (i == list.length - 1) {
                System.out.println();
                break;
            }
        }
        Collections.sort(keyIndexedList);
        for (int i = 0; i < list.length; i++) {
            if (i == 0) {
                System.out.print(">>Randomized&Sorted  Array    : ");
            }

            //Update outputkeyArray
            outputkeyArray[i] = keyIndexedList.get(i).getKey();
            System.out.print(keyIndexedList.get(i).getKey() + " ");

            if (i == list.length - 1) {
                System.out.println();
                break;
            }

        }
    }

    public static void randomizeInPlace(int[] list) {
//        keyArray = null;
//        System.gc();
        outputkeyArray = new int[list.length];
        System.arraycopy(list, 0, outputkeyArray, 0, list.length);
        int temp;
        int swapIndex;
        for (int i = 0; i < outputkeyArray.length; i++) {
            temp = outputkeyArray[i];
            swapIndex = (int) (Math.random() * outputkeyArray.length);
            outputkeyArray[i] = outputkeyArray[swapIndex];
            outputkeyArray[swapIndex] = temp;
        }
        //Update outputkeyArray
    }

    public void setListManually() {
        if (newlist.isEmpty()) {
            System.out.println("Choose to set int[] array manually (Enter '.' will stop your entry): ");
        }
        String input = null;
        try {
            for (int i = 0; i < (newlist.size() + 1); i++) {
                Scanner in = new Scanner(System.in);
                input = in.next();
                newlist.add(Integer.parseInt(input));
            }
        } catch (Exception e) {
            if (input.contentEquals(".")) {
                // Setup new int array if it is "."
                this.inputkeyArray = null;
                System.gc();
                this.inputkeyArray = new int[newlist.size()];
                for (int i = 0; i < newlist.size(); i++) {
//                    System.out.print(newlist.get(i) + " ");
                    this.inputkeyArray[i] = (int) newlist.get(i);
                }
                System.out.println("New array setup completed.");
            } else {
                // Ignore Input & Enter again if not '.'
                System.out.println("Ignored input :" + input + " as " + input.getClass());
                setListManually();
            }
        }
    }

    public int[] getinputkeyArray() {
        return this.inputkeyArray;
    }

    public void setinputkeyArray(int[] array) {
        this.inputkeyArray = array;
    }

    public int[] getoutputkeyArray() {
        return this.outputkeyArray;
    }

}
