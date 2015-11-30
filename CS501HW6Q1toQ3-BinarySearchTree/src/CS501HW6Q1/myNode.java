/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW6Q1;

/**
 *
 * @author raliclo
 */
public class myNode {

    {/*
Q1--
1. [5 Pts] Create a class called Node. This class should have the followings:
• A data member to hold a key that is an int value
        // See key
• It has data members that point to parent, left, and right nodes
        // see left,right, parent
• A constructor that takes the key value
        // see myNode(int[] args)
         */
    }

    Integer key;    // key value
    myNode parent;  // parent node
    myNode left;    // left node
    myNode right;   // right node
    Object value;   // Object stored for sorting
    Integer BSTlevel; // distance to root.

    myNode() {
        this.key = null;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    myNode(int x) {
        this.key = x;
        this.parent = null;
        this.left = null;
        this.right = null;
    }

    void setparent(myNode x) {
        this.parent = x;
    }

    void setleft(myNode x) {
        this.left = x;
    }

    void setright(myNode x) {
        this.right = x;
    }

    myNode getparent(myNode x) {
        return this.parent;
    }

    myNode getleft(myNode x) {
        return this.left;
    }

    myNode getright(myNode x) {
        return this.right;
    }

    @Override
    public String toString() {
        return "\tKey :" + this.key
                + "\t parent key: " + this.parent.key
                + "\t left   :" + this.left
                + "\t right  :" + this.right;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

}
