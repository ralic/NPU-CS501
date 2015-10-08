/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw2q5_maxsubarray.Uncompleted;

import cs501hw2q5_maxsubarray.ProfitSet;
import java.util.ArrayList;

/**
 *
 * @author raliclo
 */
public class Test {

    protected static ProfitSet buysell = new ProfitSet();
    protected static ArrayList buysells;
    protected static int[] normalized;
    protected static int beginIndex;
    protected static int endIndex;
    protected static int maxSum;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] price = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};
//        int[] price = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        KeyValueInArray maxprice, minprice;
//        buysells.removeAll(buysells);
//        buysells.add(buysell);
        setNormalized(price);
        maxprice = findMax(normalized);
        minprice = findMin(normalized);
        int avgPrice = findAvg(price);
        int prices = maxSubArray(normalized);

        System.out.println("\nMin Price :" + minprice.getValue() + "@index=" + minprice.getIndex()
                + " MaxPrice :" + maxprice.getValue() + "@index=" + maxprice.getIndex());

        System.out.println("The maximum profit is [" + prices + " from program]. ");
//        System.out.println("Buy on day [buy day " + buydays + "] "
//                + "and sell on day [sell day" + selldays + "].");
    }

    private static int maxSubArray(int[] A) {
        KeyValueInArray a = new KeyValueInArray();
        KeyValueInArray b = new KeyValueInArray();
        a.setIndex(0);
        a.setValue(A[0]);
        int newsum = a.getValue();
        int maxsum = a.getValue();
        for (int i = 1; i < A.length; i++) {
            b.setIndex(i);
            b.setValue(A[i]);
            newsum = myMax(a.getValue() + b.getValue(), b.getValue());
            maxsum = myMax(maxsum, newsum);
        }
        return maxsum;
    }

    private static KeyValueInArray myMax(KeyValueInArray a, KeyValueInArray b) {
        return null;
        //        buysells.add(buysell);
    }

    public static int myMax(int a, int b) {
        if (a >= b) {
            maxSum = a;
        } else {
            maxSum = b;
        }
        return maxSum;
    }

    private static void setNormalized(int[] array) {
        int avgPrice = findAvg(array);
        normalized = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            normalized[i] = array[i] - avgPrice;
            System.out.print(normalized[i] + ",");
        }
    }

    private static int findAvg(int[] array) {
        int sum = 0;
        for (int i = 1; i < array.length; i++) {
            sum += array[i];
        }
        return sum / array.length;
    }

    private static KeyValueInArray findMax(int[] array) {
        KeyValueInArray max = new KeyValueInArray();
        max.setValue(array[0]);
        max.setIndex(0);
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max.getValue()) {
                max.setValue(array[i]);
                max.setIndex(i);
            }
        }
        return max;
    }

    private static KeyValueInArray findMin(int[] array) {
        KeyValueInArray min = new KeyValueInArray();
        min.setValue(array[0]);
        min.setIndex(0);
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min.getValue()) {
                min.setValue(array[i]);
                min.setIndex(i);
            }
        }
        return min;

    }
}
