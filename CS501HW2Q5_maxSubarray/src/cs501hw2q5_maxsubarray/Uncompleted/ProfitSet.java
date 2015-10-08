/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw2q5_maxsubarray.Uncompleted;

import cs501hw2q5_maxsubarray.*;

/**
 *
 * @author raliclo
 */
public class ProfitSet {

    public int buyday;
    public int sellday;
    public int profit;

    public ProfitSet() {
    }

    public void setProfitSet(int a, int b, int c) {
        buyday = a;
        sellday = b;
        profit = c;
    }

    public void printProfit() {

        System.out.println("The maximum profit is [" + profit + "] from program. ");
        System.out.println("Buy on day [buy day " + buyday + "] "
                + "and sell on day [sell day " + sellday + "].\n");
    }

}
