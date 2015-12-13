/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs501hw7q1toq2.rodcuttingproblem;

import static java.lang.Integer.max;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *
 * @author raliclo
 */
public class RodCuttingProblem {

    HashMap<Integer, Integer> ktv;
    public int[] answers;
    public int callbacks;
    public int localmin;
    public int[] pi;
    public int[] di;
    public int[] cutpieces;

    /**
     * @Goal : Get the optimal revenue from cutting rod
     * @Input : n is rod length , distance di is the rod split, n = sigma (i) ,
     * pi is price index of each i
     * @Ouput : r is revenue
     * @Status : Under developing
     * @Pseudo : cutRod( p, n) { if n==0 return 0 q= neg infinity for i =1 to n
     * q= max(q,p[i]+cutRod(p,n-i) return q}
     */
    RodCuttingProblem(int[] pi, int[] di) {
        this.pi = pi;
        this.di = di;
        callbacks = 0;
        answers = new int[pi.length + 1];
        ktv = new HashMap();
        if (pi.length == di.length) {
            int cuts = pi.length;
            ktv.put(0, 0);
            for (int i = 0; i < cuts; i++) {
                this.ktv.put(pi[i], di[i]);
            }
        }
    }

    // M1
    int cutRod(int n) {
        callbacks++;
        int profit = Integer.MIN_VALUE; // local mininum
        if (n >= 1 && n <= pi.length) {
            for (int i = 1; i <= n; i++) {
                profit = max(profit, (pi[i - 1] + cutRod(n - i)));
                callbacks++;
            }
            return profit; // return the total profit 
        }
        return 0;
    }

    // M2
    int memoizedCutRod_TopDown(int n) { // TOP DOWN
        callbacks++;
        if (answers[n] > 0) {
            callbacks++;
            return answers[n];
        }
// STANDARD JAVA 
        int profit = Integer.MIN_VALUE; // local mininum
        if (n >= 1 && n <= pi.length) {
            for (int i = 1; i <= n; i++) {
                profit = max(profit, (pi[i - 1] + memoizedCutRod_TopDown(n - i)));
                answers[n] = profit; // Save the answer array for resue.
                callbacks++;
            }
            return profit;
        }
        return 0;
    }

    //M3
    int memoizedCutRod_BottomUp(int n) {
        callbacks++;
        if (answers[n] > 0) {
            return answers[n];
        }
        int profit = Integer.MIN_VALUE; // local mininum
        for (int i = 1; i <= n; i++) {
            profit = max(profit, (pi[i - 1] + answers[n - i]));
            answers[n] = profit;
            callbacks++;
        }
        return answers[n];
    }

    // M4
    int memoizedCutRod_BottomUp_Extension(int n) {
        cutpieces = new int[n + 1];
        callbacks++;
        if (answers[n] > 0) {
            return answers[n];
        }
        int i, j;
        int profit = Integer.MIN_VALUE; // local mininum
        for (j = 1; j <= n; j++) {
            for (i = 1; i <= j; i++) {
                if (profit < pi[i - 1] + answers[j - i]) {
                    profit = max(profit, (pi[i - 1] + answers[j - i]));
                    cutpieces[j] = i; // Store solution Index.
                }
                answers[j] = profit;
                callbacks++;
            }
        }
        return 0;
    }

    void printResults() {
        for (int i = 0, n = answers.length; i < n; i++) {
            System.out.print(answers[i] + " ");
        }
    }

    String printCutpieces(int n) {
        String x = "";
        while (n > 0) {
            x += cutpieces[n] + " ";
            n = n - cutpieces[n];
        }
        return x;
    }
}

//        int[] di = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};      //length
//        int[] pi = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};//price

/*
Map<Integer, Integer> ktv = new Map() {
        int n;
        ArrayList<Integer> keys;
        ArrayList<Integer> values;
        ArrayList<Integer> kMatch; // store searched key index;
        ArrayList<Integer> vMatch; // store searched value index;
        int kn; // store searched & matched key numbers if not unique;
        int vn; // store searched & matched value numbers if not unique;

        @Override
        public int size() {
            return this.n;
        }

        @Override
        public boolean isEmpty() {
            if (this.n == 0) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean containsKey(Object key) {
            kMatch = new ArrayList();
            int x = 0; // numbers of matched
            int nn = 0; // counts index
            for (Integer k : this.keys) {
                if (key == k) {
                    x++;
                    kMatch.add(nn);
                }
            }
            this.kn = x;
            return x != 0;
        }

        @Override
        public boolean containsValue(Object value) {
            this.vMatch = new ArrayList();
            int x = 0; // numbers of matched
            int nn = 0; // counts index
            for (Integer k : this.values) {
                if (value == k) {
                    x++;
                    vMatch.add(nn);
                }
            }
            this.vn = x;
            return x != 0;
        }

        @Override
        public Object get(Object key) {
            return ktv.containsKey(key) ? kMatch.toString() : false;
        }

        @Override
        public Object put(Object key, Object value) {
            keys.add((Integer) key);
            values.add((Integer) value);
            return keys.size() == values.size();
        }

        @Override
        public Object remove(Object key) {
            return ktv.containsKey(key) ? keys.remove((Integer) key) : false;
        }

        @Override
        public void putAll(Map m) {
            
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Set keySet() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Collection values() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Set entrySet() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };
 */
