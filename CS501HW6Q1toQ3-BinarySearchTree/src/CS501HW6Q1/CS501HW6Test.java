
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW6Q1;

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
 */
public class CS501HW6Test {

    {/*
Q1-- See myNode.java
1. [5 Pts] Create a class called Node. This class should have the followings:
• A data member to hold a key that is an int value
        // See key
• It has data members that point to parent, left, and right nodes
        // see left,right, parent
• A constructor that takes the key value
        // see myNode(int[] args)
    }

    {/*
Q2--> See myBST.java
2. [40 Pts] Create a binary search tree class. This class should have the followings:
• A data member that points to the root node of the tree
• A constructor that takes an int[] array as argument and build the BST from the array (each
node is an object created from class Node in problem 1)
        // See myBST(int[] args)
• An iterativeSearch method that can be used to search for a node with the given key value
        // See myiterativeBSTSearch(my Node starter, Integer k)
• A insert method to insert a node into the tree
        // See myBSTinsert_Root
• A successor method that finds the successor for a given node
        // See myBSTsuccessor
• A minimum method that finds the minimum value in any of the subtrees, given a node x (not
just from the root and not just the minimum of the entire input array)
        // See myBSTmin
• An inorderTreeWalk method that can be used to display the results from the in-order tree
walk, starting from any given node
        // See myinorderTreeWalk
         */
    }

    {/*
--Q3--> See CS501HW6Test.java
3. [5 Pts] Add the main method inside of the binary search tree class to test your codes. Your tests
should do the followings:
• Use the input array A= {10,15,12,7,8,5,6,9,20,18,22}
• Build the binary search tree for this input
• Do the inorderTreeWalk to display the results (this should show the sorted list)
• Use the minimum method to find the minimum, starting from the node with key = 10
• Find the successor to the root node, and display the result on the screen
• Find the successor to node with key = 9, and display the result on the screen
        The output from running the program should display on the screen as follows:
        Inorder tree walk: 5 6 7 8 9 10 12 15 18 20 22
        Minimum from node.key = 10 is [the result]
        Successor to the root node is [the result]
        Successor to the node.key = 9 is [the result]
         */
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * @param speedX to monitor program runtime
         */
        // TODO code application logic here
        int[] A = {10, 15, 12, 7, 8, 5, 6, 9, 20, 18, 22};
//        int[] A = ThreadLocalRandom.current().ints(10, 100).distinct().limit(10).toArray();
        long speedX = System.currentTimeMillis();

        myBST bst1 = new myBST(A);

        System.out.println("# Testing inorderTreeWalk from root :");
        bst1.myinorderTreeWalk(bst1.root);
        System.out.println("\n");

        System.out.println("# Testing myiterativeBSTSearch from root, searching A[3]");
        myNode searched = bst1.myiterativeBSTSearch(bst1.root, A[3]);
        System.out.println("Searched.key:" + searched.key + "\tSearched.BSTlevel:" + searched.BSTlevel);
        System.out.println();

        System.out.print("#Testing myBSTmin from root:");
        System.out.println(bst1.myBSTmin(bst1.root));
        System.out.print("#Testing myBSTmin from A[3]:");
        System.out.println(bst1.myBSTmin(searched).key);

        System.out.print("#Testing myBSTmax from root:");
        System.out.println(bst1.myBSTmax(bst1.root));
        System.out.print("#Testing myBSTmax from A[3]:");
        System.out.println(bst1.myBSTmax(searched).key);
        System.out.println();

        System.out.println("#Testing myBSTmin from node.key=10:");
        System.out.println("Minimum from node.key = 10 is [The result]");
        System.out.println("[The result]:" + bst1.myBSTmin(bst1.myiterativeBSTSearch(bst1.root, 9)).key);
        System.out.println();

        System.out.println("# Testing myBSTnextLevel on root.key ");
        System.out.println("Next Level to the root node is [The result] ");
        bst1.myBSTnextLevel(bst1.root.key);
        System.out.println();

        System.out.println("# Testing myBSTnextLevel on node.key=9");
        System.out.println("Next Level to the node.key = 9 is [The result]");
        bst1.myBSTnextLevel(9);
        System.out.println();
//
//        System.out.println("Testing printTree");
//        bst1.printTree();

        System.out.println("# Testing myBSTsuccessor on root.key ");
        System.out.println("Successor to the root node is [The result] ");
        System.out.println(bst1.myBSTsuccessor(bst1.root.key));

        System.out.println("# Testing myBSTsuccessor on node.key=9");
        System.out.println("Successor to the node.key = 9 is [The result]");
        System.out.println(bst1.myBSTsuccessor(9));

        System.out.println("Time spent :" + (System.currentTimeMillis() - speedX));
    }

    /**
     * @param x for command line
     * @throws java.io.IOException
     */
    public static Object runexec(String x) throws IOException {
        Process p = Runtime.getRuntime().exec(x, null, null);
        Object ans = null;
        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = null;
        try {
            while ((line = input.readLine()) != null) {
                if (line.startsWith("ans =")) {
                    System.out.println(line.split("=")[1].trim());
                    ans = line.split("=")[1].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();

        } catch (InterruptedException ex) {
        }
        return ans;
    }
}
