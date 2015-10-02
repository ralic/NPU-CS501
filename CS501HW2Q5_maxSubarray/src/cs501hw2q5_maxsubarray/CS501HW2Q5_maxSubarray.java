/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw2q5_maxsubarray;

;
import java.util.ArrayList;

/**
 *
 * @author raliclo
 */


public class CS501HW2Q5_maxSubarray {

    protected static ProfitSet buysell = new ProfitSet();
    protected static ArrayList buysells = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int[] price = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97,};
//        int[] price = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        for (int i = 0; i < price.length; i++) {
            for (int j = i + 1; j < price.length; j++) {
                int sumdays = 0;
                int profit = 0;
                for (int k = i; k < j; k++) {
                    sumdays += price[k];
                }
                profit = (price[j] * (j - i) - sumdays);
                buysell.setProfitSet(i, j, profit);
//                buysell.printProfit();//for debug
                buysells.add(buysell);
                buysell = new ProfitSet();
            }
        }
        findMax().printProfit();
    }

    public static ProfitSet findMax() {
        System.out.println("Total methods to get profits: " + buysells.size()); //for debug
        int counts = 0;
        ProfitSet target = (ProfitSet) buysells.get(0);
        ProfitSet best = new ProfitSet();
        int max = target.profit;
        for (int i = 0; i < buysells.size(); i++) {
            target = (ProfitSet) buysells.get(i);
            if (target.profit == max) {
                counts++;
                System.out.println("Methods to get max profit: " + counts);
            }
            if (target.profit > max) {
                max = target.profit;
                best = target;
//                System.out.println("Buy day:" + best.buyday + " Sell day:" + best.sellday + " Profit:" + best.profit);//for debug
            }
        }
        return best;
    }
}


/*
Write a program in Java that implements the brute-force method (not the divide-and- conquer method) for solving the maximum-subarray problem. 
-	Use the concept of stock purchase to maximize profit, as shown in the lecture
-	Try every possible pair of buy & sell dates in which the buy date precedes the sell date 
-	Determine the highest possible profit 
-	Use the following array in your program as a test case  (i.e., day 0 to day 16): 
int[] price = {100,113,110,85,105,102,86,63,81,101, 94,106,101,79,94,90,97};
-	The output should show max profit value and the buy & sell days as follows : 
The maximum profit is [max profit value from program]. 
Buy on day [buy day 0...15] and sell on day [sell day 1...16].

 */
