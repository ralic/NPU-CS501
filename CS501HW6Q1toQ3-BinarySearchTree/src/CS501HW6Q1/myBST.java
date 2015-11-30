/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW6Q1;

import java.util.TreeMap;

/**
 *
 * @author raliclo
 */
public class myBST {

    {/*
Q2--
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

    myNode root;
    myNode searcher;
    myNode lastaccess;
    Integer elements;   // Store numbers of elements in the BST
    Integer printed;    // Store elements printed

    /*
    * @args : arguments for Binary Search Tree contruction
    **/
    myBST() {
        root = new myNode();
        lastaccess = new myNode();
        elements = 0;
    }

    myBST(int[] args) {
        this.root = new myNode();
        this.lastaccess = new myNode();
        this.elements = 0;
        for (int x : args) {
            myNode newNode = new myNode(x);
//            myBSTinsert_Tail(newNode);
//            System.out.println("hi" + newNode.toString());
            this.myBSTinsert_Root(newNode);
        }
    }

    /**
     * @Goal : Insertion Method from the root
     * @Input : myNode z
     * @Ouput : Add a branch
     * @Status: Completed
     * @Pseudo:
     */
    public void myBSTinsert_Root(myNode z) {
        this.elements++;
        myNode checker = new myNode();
        checker = root;
        if (checker.key == null) {
            root.key = z.key;
            root.parent = z;
            System.out.println("Initialize thse root key & Insertion from root every time");
            System.out.print(" & root.toString" + root.toString());
            System.out.println(" Tree level :" + root.BSTlevel);
        } else {
            myNode branch = new myNode();
            Integer level = 0;
            while (checker.key != null) {
                level++;
                branch.parent = checker;
                if (z.key < checker.key) {
                    System.out.println("Branch to the left");
                    if (checker.left == null) {
                        checker.left = branch;
                        checker = branch;
                    } else {
                        checker = checker.left;
                    }
                } else {
                    System.out.println("Branch to the right");
                    if (checker.right == null) {
                        checker.right = branch;
                        checker = branch;
                    } else {
                        checker = checker.right;
                    }
                }
            }
            branch.key = z.key;
            branch.BSTlevel = level;
            System.out.print(" & branch.toString" + branch.toString());
            System.out.println(" Tree level :" + branch.BSTlevel);
        }
    }

    public void myBSTinsert_Root2(myNode z) {
        this.elements++;
        myNode y = null;
        myNode x = this.root;
        while (x.key != null) {
            System.out.println("hihi1");
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.parent = y;
        if (y == null) {
            System.out.println("hihi2");
            this.root = z;
        } else if (z.key < y.key) {
            System.out.println("hihi3");
            y.left = z;
        } else {
            System.out.println("hihi4");
            y.right = z;
        }
    }

    public myNode myBSTinsert_Tail(myNode x) {
        this.elements++;
//        System.out.println("Receiving newNode:" + x.toString());
        int xx;// case for switch
        xx = (root.key == null) ? 0 : 5;
        xx = (lastaccess.left == null && lastaccess.right == null) ? xx + 1 : xx;
        xx = (lastaccess.left == null && lastaccess.right != null) ? xx + 2 : xx;
        xx = (lastaccess.left != null && lastaccess.right == null) ? xx + 3 : xx;
        switch (xx) {
            case 1:  // no root node no current node
//                System.out.println("@Creating Root-node, Set Current Node on Root-node");
                root = x;
                root.parent = x;
                lastaccess = root;
                break;
            case 6:  // root exist, no left & right
                if (x.key != lastaccess.key) {
                    if (x.key > lastaccess.key) {
//                        System.out.println("R@Creating right branch because newNode.key > current.key");
                        x.parent = lastaccess;
                        lastaccess.right = x;
                        lastaccess = x;
                    } else {
//                        System.out.println("L@Creating left branch because newNode.key < current.key");
                        x.parent = lastaccess;
                        lastaccess.left = x;
                        lastaccess = x;
                    }
                } else {
                    this.elements--;
                    System.out.println("!!! BST does not allow duplicated int values, Skipped:" + x.key);
                }
                break;
            default:
                System.out.println("Error: Not in consideration..");
        }
        return lastaccess;
    }

    /**
     * @Goal :
     * @Input :
     * @Ouput :
     * @Status :
     */
    public void myBSTsuccessor(Integer k) {
        System.out.println("Searching key :" + k);
        myNode searched = this.myiterativeBSTSearch(this.root, k);
        int z = 0; // switching
        z = (searched.left == null) ? (z + 1) : z;
        z = (searched.right == null) ? (z + 2) : z;
        switch (z) {
            case 0:
                System.out.println("[The result]:Both Right & Left successor founded");
                System.out.println("-Left Successor: " + searched.left.key + " @BSTlevel" + searched.left.BSTlevel);
                System.out.println("-Right Successor: " + searched.right.key + " @BSTlevel" + searched.right.BSTlevel);
                break;
            case 1:
                System.out.println("[the result]:Only Right successor founded");
                System.out.println("-Right Successor: " + searched.right.key + " @BSTlevel" + searched.right.BSTlevel);
                break;
            case 2:
                System.out.println("[The result]:Only Left successor founded");
                System.out.println("-Left Successor: " + searched.left.key + " @BSTlevel" + searched.left.BSTlevel);
                break;
            case 3:
                System.out.println("[The result]:No successor founded");
                break;
            default:
                System.out.println("Not in consideration...");
        }
    }

    /**
     * @Goal : myBSTmin(), with an input node, it will return min of the branch
     * @Input : myNode x
     * @Ouput : myNode
     * @Status :
     */
    public Integer myBSTmin(myNode x) {
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    /**
     * @Goal : myBSTmax(), with an input node, it will return min of the branch
     * @Input : myNode x
     * @Ouput : myNode
     * @Status :
     */
    public Integer myBSTmax(myNode x) {
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    public void myinorderTreeWalk(myNode x) {
        if (x == root) {
            System.out.print("In Order Tree Walk : ");
        }
        if (x != null) {
            myinorderTreeWalk(x.left);
//            System.out.println("X.key=" + x.key);
            System.out.print(x.key + " ");
            myinorderTreeWalk(x.right);
        }
    }

    public void mypreorderTreeWalk() {
    }

    /**
     * @Goal : Implentation of
     * @Input :
     * @Ouput :
     * @Status :
     * @Pseudo:
     */
    public void mypostorderTreeWalk() {
    }

    /**
     * @Goal : implementation of BST Tree Search iteratively
     * @Input :
     * @Ouput :
     * @Status :
     */
    public myNode myiterativeBSTSearch(myNode starter, Integer k) {
        searcher = starter;
        if (searcher == null || searcher.key == k) {
            return searcher;
        } else if (k < searcher.key) {
            return myiterativeBSTSearch(searcher.left, k);
        } else {
            return myiterativeBSTSearch(searcher.right, k);
        }
    }

    /**
     * @Goal : Print the whole tree visually from root
     * @Input : N/A
     * @Ouput : The whole tree structure
     * @Status: Under Development
     */
    public void printTree() {
        myNode printer;
        if (root != null) {
            printer = root;
            System.out.println("Setting printer at root ...");
            printed = 0;
            printTree(printer);
        }
        System.out.println("Total   elements :" + elements);
        System.out.println("Printed elements :" + printed);
        printed = 0;
    }

    public void printTree(myNode x) {
        myNode printer = x;
        printed++;
        int xx = 0;// case for switch
        xx = (printer.left == null && printer.right == null) ? xx + 1 : xx;
        xx = (printer.left == null && printer.right != null) ? xx + 2 : xx;
        xx = (printer.left != null && printer.right == null) ? xx + 3 : xx;
        xx = (printer.left != null && printer.right != null) ? xx + 4 : xx;
        myNode ans = null;
        switch (xx) {
            case 1:
                System.out.println("C1 : Printer.key :" + printer.key + " Printer.BSTlevel :" + printer.BSTlevel);
                System.out.println("@ No Left or Right...");
                break;
            case 2:
                System.out.println("C2 : Printer.key :" + printer.key + " Printer.BSTlevel :" + printer.BSTlevel);
                System.out.println("@ No more on the left... ");
                System.out.println("--Going Right");
                printTree(x.right);
                break;
            case 3:
                System.out.println("C3: Printer.key :" + printer.key + " Printer.BSTlevel :" + printer.BSTlevel);
                System.out.println("@ No more on the right...");
                System.out.println("--Going Left");
                printTree(x.left);
                break;
            case 4:
                System.out.println("C4 : Printer.key :" + printer.key + " Printer.BSTlevel :" + printer.BSTlevel);
                System.out.println("@ There are left and right branch...");
                System.out.println("--Going Left");
                printTree(x.left);
                System.out.println("--Going Right");
                printer = printer.right;
                printTree(x.right);
                break;
            default:
                System.out.println("Error: Not in consideration..");
        }

    }

    /**
     * @Goal : Re-balancing the whole tree
     * @Input : N/A
     * @Ouput : New BST tree
     * @Status: Under Development
     */
    public void myBSTrebalancing() {
    }

    /**
     * @Goal : Reverse the whole tree
     * @Input :
     * @Ouput : New BST tree
     * @Status: Under Development
     */
    public void myBSTreverse() {
    }

}