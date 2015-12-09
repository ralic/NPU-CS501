
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw7q1toq2.rodcuttingproblem;

import java.io.*;
import java.net.*;
import java.security.*;
import java.math.*;
import java.awt.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.lang.*;
import java.time.*;
import java.applet.*;
import java.nio.*;
import java.beans.*;
import java.rmi.*;
import java.util.logging.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.util.function.*;
import java.nio.file.*;
import java.nio.charset.*;
import java.nio.file.spi.*;
import java.nio.file.attribute.*;
import java.nio.channels.*;
import java.nio.channels.spi.*;

/**
 *
 * @author raliclo
 * @Java : java version "1.8.0_65"
 */
public class CS501HW7Q1toQ2_RodCuttingProblemTest {

    void CS501HW7_Info() {
        /*
        1. [40 Pts] Create a class called RodCuttingProblem. 
            This class should have the followings:
            • A method called cutRod that performs the cutting rod algorithm 
            (without memoization) to compute the optimal revenue value
        
            • Methods called memoizedCutRod and memoizedCutRodAux that perform the cutting rod algorithm 
            (using the memoization technique) to compute the optimal revenue value
        
            • Each technique should implement a counter to count how many times 
            the method is called (recursively) to solve a problem of rod length 
        
            • Test these algorithms in the main method, using the pricing table shown below
                
                //length:  int[] i = { 1,2,3,4,5,6,7,8,9,10};
                //price:  int[] Pi = {1,5,8,9,10,17,17,20,24,30};
        
            • Calculate the optimal revenue figures for rods of lengths
                i = 1,2,3,...,10, using both methods
        
            • Your outputs should be a table that shows the following data and results:
                // Output format :
                length i, 
                (A)optimal rev from the cutRod (CR) method,
                number for times the cutRod method is called to solve the problem of length i, 
                (B)optimal rev from the memoizedCutRod (MCR) method,
                number for times the method is called to solve the problem of length i
        
            • The output table should have these columns with headers:
                Length , Rev CR, Num Calls CR, Rev MCR, Num Calls MCR
        
            • Compare the results from the cutRod and the memoizedCutRod methods
        
        2. [10 Pts] Modify the memoizedCutRod method 
            (or implement the extendedBottomUpCutRod method shown in the class) 
            to keep track of the rod length that generates the optimal solution
        
            for each of the rod lengths used in problem 1 above.
            • Add a column to the output table to show the optimal rod cut results 
            – the lengths of the rods (after the cuts) that yield the optimal revenue values
            • The output table should now have these columns with headers:
                Length , Rev CR, Num Calls CR, Rev MCR, Num Calls MCR, Cut Pieces
        
            The output from running the program (after doing problems 1 and 2) 
            should display on the screen as follows:
        
            Length / Rev CR /  Num Calls CR / Rev MCR / Num Calls MCR / Cut Pieces
            1 1 2 1 2 1
            2 5 4 5 4 2
            3 8 8 8 7 3
            4 10 16 10 11 2 2
            5 13 32 13 16 2 3
            [The table shows the entire list of results from lengths 1 to 10]
         */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] di = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};      //length
        int[] pi = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};//price
//        int[] pi = ThreadLocalRandom.current().ints(1, 100).limit(25).toArray(); // 29 numbers -->  No memorization ~ 8500msec

        System.out.print("pi={");
        for (int p : pi) {
            System.out.print(p + ",");
        }
        System.out.println("}");

        /**
         * @Goal : Testing Dynamic programming WITHOUT memoization
         * @Status : Completed , n= 30 , time ~6439msec ,callbacks:1073741823
         */
//        pi = ThreadLocalRandom.current().ints(1, 100).parallel().limit(30).toArray(); // Change limit to test n capability
        RodCuttingProblem q1 = new RodCuttingProblem(pi, di);
        long speedX = System.currentTimeMillis();
        q1.cutRod(pi.length);
        System.out.println("\nM1 Total callbacks:" + q1.callbacks);
        System.out.println("M1 Time spent :" + (System.currentTimeMillis() - speedX) + "msec");
        q1.callbacks = 0;
        /**
         * @Goal : Testing Dynamic programming with memoization by TopDown
         * @Status : n=5000, Time spent :390msec,callbacks:25005001
         * @Status : n=10000 , StackOverflowError
         */
//        pi = ThreadLocalRandom.current().ints(1, 100).parallel().limit(5000).toArray(); // for maxN testing.
        RodCuttingProblem q2 = new RodCuttingProblem(pi, di);
        long speedY = System.currentTimeMillis();
//        }
        q2.memoizedCutRod_TopDown(pi.length);
//        q2.printResults(); // Print answer array
        System.out.println("\nM2 Total callbacks:" + q2.callbacks);
        System.out.println("M2 Time spent :" + (System.currentTimeMillis() - speedY) + "msec");
        q2.callbacks = 0;
        /**
         * @Goal : Testing Dynamic programming with memoization by BottomUp
         * @Status : n=5000,Time spent :570,callbacks:87654322
         * @Status : n=10000,Time spent :55msec,callbacks:7654321
         */
//        pi = ThreadLocalRandom.current().ints(1, 100).parallel().limit(87654321).toArray();// for maxN testing.
        RodCuttingProblem q3 = new RodCuttingProblem(pi, di);
        long speedZ = System.currentTimeMillis();
//        for (int i = 1, n = pi.length; i <= n; i++) {
//            System.out.println("n=" + i + "=>" + q3.memoizedCutRod_BottomUp(i));
//            System.out.println("Total callbacks:" + q3.callbacks);
//        }
        q3.memoizedCutRod_BottomUp(pi.length);
//        q3.printResults(); // Print answer array
        System.out.println("\nM3 Total callbacks:" + q3.callbacks);
        System.out.println("M3 Time spent :" + (System.currentTimeMillis() - speedZ) + "msec");
        q3.callbacks = 0;
        /**
         * @Goal : Print Final result as Homework requested.
         * @info : q4 : M1 (no memo) , q5, M2 (with memo_topdn) , q6, M3 (with
         * memo_btmup), Q7, M4 (store answer)
         * @Status : Competed
         */
//        pi = ThreadLocalRandom.current().ints(1, 100).parallel().limit(30).toArray(); // Change limit to test n capability
        RodCuttingProblem q4 = new RodCuttingProblem(pi, di);
        RodCuttingProblem q5 = new RodCuttingProblem(pi, di);
        RodCuttingProblem q6 = new RodCuttingProblem(pi, di);

        RodCuttingProblem q7 = new RodCuttingProblem(pi, di);
        q7.memoizedCutRod_BottomUp_Extension(pi.length);
//        q7.printCutpieces(8);
        System.out.println();

        int dashes = 101;
        IntStream.range(0, dashes).forEach(p -> System.out.print("-"));
        System.out.printf("\n|%-6s|%-8s|%-13s|%-8s|%-13s|%-10s|%-16s|%-18s|\n",
                "Length",
                "Rev CR",
                "Num Calls CR",
                "Rev MCR",
                "Num Calls MCR",
                "MCR-Btmup",
                "Num Calls Btmup",
                "Cut Pieces");
        IntStream.range(0, dashes).forEach(p -> System.out.print("-"));
        for (int i = 1, n = pi.length; i <= n; i++) {
            String cut2 = " ";
            System.out.printf("\n|%-6s|%-8s|%-13s|%-8s|%-13s|%-10s|%-16s|%-18s|\n",
                    i,
                    q4.cutRod(i),
                    q4.callbacks,
                    q5.memoizedCutRod_TopDown(i),
                    q5.callbacks,
                    q6.memoizedCutRod_BottomUp(i),
                    q6.callbacks,
                    q7.printCutpieces(i)
            );
            q4.callbacks = 0;
            q5.callbacks = 0;
            q6.callbacks = 0;
            IntStream.range(0, dashes).forEach(p -> System.out.print("-"));
        }
        System.out.println();
    }
}
