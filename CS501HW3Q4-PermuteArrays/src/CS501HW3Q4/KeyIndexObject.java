/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CS501HW3Q4;

import java.util.Comparator;

/**
 *
 * @author raliclo
 */
public class KeyIndexObject
        //        extends ArrayList
        implements Comparable<KeyIndexObject>, Comparator<KeyIndexObject> {

    private int key;
    private int index;

    public KeyIndexObject(int k, int v) {
        key = k;
        index = v;
    }

    @Override // Comparable abstract
    public int compareTo(KeyIndexObject o) {
        // Ascending sort
        if (o.index < this.index) {
            return 1; // true for smaller
        } else if (o.index > this.index) {
            return -1; // false for larger
        } else {
            return 0;
        }
    }

    @Override // Comparator abstract
    public int compare(KeyIndexObject o1, KeyIndexObject o2) {
        return o1.compareTo(o2);
    }

    /**
     * @return the key
     */
    public int getKey() {
        return key;
    }

}
