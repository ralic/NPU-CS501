/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw2q5_maxsubarray.newpackage;

/**
 *
 * @author raliclo
 */
public class KeyValueInArray {

    private int index;
    private int value;

    public KeyValueInArray() {
    }

    public KeyValueInArray getThisObject() {
        return this;
    }

    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

}
