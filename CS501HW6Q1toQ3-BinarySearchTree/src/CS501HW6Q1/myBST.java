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
     * @Ouput : Add a branch and build the binary search tree
     * @Status: Completed
     * @Pseudo:
     */
    public void myBSTinsert_Root(myNode z) {
        if (z != null) {
            this.elements++;
            myNode checker = new myNode();
            checker = root;
            if (checker.key == null) {
                root.key = z.key;
                root.parent = z;
//            System.out.println("Initialize thse root key & Insertion from root every time");
//            System.out.print(" & root.toString" + root.toString());
//            System.out.println(" Tree level :" + root.BSTlevel);
            } else {
                myNode branch = new myNode();
                Integer level = 0;
                while (checker.key != null) {
                    level++;
                    branch.parent = checker;
                    if (z.key < checker.key) {
//                    System.out.println("Branch to the left");
                        if (checker.left == null) {
                            checker.left = branch;
                            checker = branch;
                        } else {
                            checker = checker.left;
                        }
                    } else //                    System.out.println("Branch to the right");
                     if (checker.right == null) {
                            checker.right = branch;
                            checker = branch;
                        } else {
                            checker = checker.right;
                        }
                }
                branch.key = z.key;
                branch.left = z.left;
                branch.right = z.right;
                branch.BSTlevel = level;
//            System.out.print(" & branch.toString" + branch.toString());
//            System.out.println(" Tree level :" + branch.BSTlevel);
            }
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
     * @Goal : Show child elements of an Integer array value
     * @Input : Integer k
     * @Ouput : print the child
     * @Status:
     */
    public void myBSTnextLevel(Integer k) {
        System.out.println("Searching key :" + k);
        myNode searched = this.myiterativeBSTSearch(this.root, k);
        int z = 0; // switching
        z = (searched.left == null) ? (z + 1) : z;
        z = (searched.right == null) ? (z + 2) : z;
        switch (z) {
            case 0:
                System.out.println("[The result]:Both Right & Left successor found");
                System.out.println("-Left child : " + searched.left.key + " @BSTlevel" + searched.left.BSTlevel);
                System.out.println("-Right child: " + searched.right.key + " @BSTlevel" + searched.right.BSTlevel);
                break;
            case 1:
                System.out.println("[the result]:Only Right successor found");
                System.out.println("-Right child: " + searched.right.key + " @BSTlevel" + searched.right.BSTlevel);
                break;
            case 2:
                System.out.println("[The result]:Only Left successor found");
                System.out.println("-Left child: " + searched.left.key + " @BSTlevel" + searched.left.BSTlevel);
                break;
            case 3:
                System.out.println("[The result]:No successor found");
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
    public myNode myBSTmin(myNode x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    /**
     * @Goal : myBSTmax(), with an input node, it will return min of the branch
     * @Input : myNode x
     * @Ouput : myNode
     * @Status :
     */
    public myNode myBSTmax(myNode x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public void myinorderTreeWalk(myNode x) {
        if (x == root) {
            System.out.print("In Order Tree Walk : ");
        }
        if (x != null) {
            if (x.left != null) {
                myinorderTreeWalk(x.left);
            }
//            System.out.println("X.key=" + x.key);
            System.out.print(x.key + " ");
            if (x.right != null) {
                myinorderTreeWalk(x.right);
            }
        }
    }

    public void mypreorderTreeWalk() {
    }

    /**
     * @Goal :
     * @Input :
     * @Ouput :
     * @Status :
     * @Pseudo:
     */
    public void mypostorderTreeWalk() {
    }

    /**
     * @Goal : implementation of BST Tree Search iteratively
     * @Input : the starter of the node, search the value
     * @Ouput :
     * @Status :
     */
    public myNode myiterativeBSTSearch(myNode starter, Integer k) {
        if (starter == null || starter.key == k) {
            return starter;
        } else if (k < starter.key) {
            return myiterativeBSTSearch(starter.left, k); // Search the smaller on the left.
        } else {
            return myiterativeBSTSearch(starter.right, k);// Search the larger on the right.
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
            System.out.println("\nSetting printer at root ...");
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
     * @Goal : Delete a node from the tree
     * @Input : Integer x
     * @Ouput : New BST tree
     * @Status: Under Development
     */
    public void myBSTdelete(Integer i) {
        myNode current = myiterativeBSTSearch(root, i);
        myNode successor = myBSTsuccessor(i);
        if (current == root) {
            System.out.println("!! Not allow to delete root-- ");
            System.exit(99);
        }
        if (current != null) {
            // store current node in temp just in case.
//        myNode temp = new myNode();
//        temp.parent = current.parent;
//        temp.right = current.right;
//        temp.left = current.left;
//        temp.key = current.key;
            System.out.println("Deleting... value =" + i);
            System.out.println(current.toString());
            if (successor == null) {
                if (current.parent.right == current) {
                    current.parent.right = successor;
                }
                if (current.parent.left == current) {
                    current.parent.left = successor;
                }
                elements--;
            }
            if (successor != null) {
                System.out.println(successor.toString());
                // Replace current by successor
                // successor link to parent.
                successor.parent.left = null; // cut off successor from his parent
                successor.parent = current.parent; // connecting to new parent
                // modified parent's configures.
                if (current.parent.right == current) {
                    current.parent.right = successor;
                }
                if (current.parent.left == current) {
                    current.parent.left = successor;
                }
                elements--;
            }
            //insert current.left and insert current.right
            if (current.left != successor) {
                myBSTinsert_Root(current.left);
                elements--;
            }
            if (current.right != successor) {
                myBSTinsert_Root(current.right);
                elements--;
            }
        }
    }

    /**
     * @Goal : Re-balancing BST tree
     * @Input : N/A
     * @Ouput : New BST tree
     * @Status: Under Development
     */
    public void myBSTrebalancing() {
    }

    /**
     * @Goal : Reverse the tree, i.e larger to the left, smaller to the right
     * @Input :
     * @Ouput : revserseBST tree
     * @Status: Under Development
     */
    public void myBSTreverse() {
    }

    /**
     * @Goal : Find the successor of a value
     * @Input : Integer i
     * @Ouput : The node represents the successor
     * @Status: Completed
     * @Pseudo: if the right child is not null, the minimum is the child.
     */
    public myNode myBSTsuccessor(Integer i) {
        myNode x = myiterativeBSTSearch(root, i);
        if (x != null) {
            if (x.right == null) {
                return null;
            } else {
                return myBSTmin(x.right);
            }
        }
        return x;
    }
}
