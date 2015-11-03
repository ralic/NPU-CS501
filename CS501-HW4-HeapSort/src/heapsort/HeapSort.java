/**
 *
 * @author raliclo
 */
package heapsort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        // Before sort
//        int[] array = {10, 15, 8, 12, 5, 2, 20, 7, 18};
        int[] array = {12, 8, 22, 10, 17, 15, 32, 24};
//        int[] array = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1, 5};
//        int[] array = {16, 14, 10, 8, 7, 9, 3, 20, 4, 1, 5};
        System.out.print("Original Array :");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        // After sort
        heapsort(array);
        int[] array2 = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1, 5};
        // After heapIncreaseKey
        heapIncreaseKey(array2, 8, 20);
        // After maxHeapInsert

        int[] array3 = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1, 5};
        maxHeapInsert(array3, 12);

        System.out.println();
    }

    private static int[] a;
    private static int n; // size of array
    private static int counter = 0; // counting steps
    private static int left;
    private static int right;
    private static int cursorIndex;

    public static void heapsort(int[] a0) {
        a = a0;
        System.out.println("\nStarting to buildMaxHeap");
        buildMaxHeap(a);
        System.out.print("\nAfter buildMaxHeap Array :");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\nStarting HeapSort");

        for (int i = n; i > 0; i--) {
            exchange(0, i);
            n = n - 1;
            maxheap(a, 0);
        }

    }

    public static void buildMaxHeap(int[] a) {
        n = a.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
        counter = 0;
    }

    public static void maxheap(int[] a, int i) {
        // Forms a pair of index
        left = 2 * i;
        right = 2 * i + 1;

        // Print each step in maxheap construction.
        System.out.print("Step:" + counter + "  :");
        counter++;
        for (int j = 0; j < a.length; j++) {
            System.out.print(a[j] + " ");
        }
        System.out.println();
        // Compare left to the right.
        if (left <= n && a[left] > a[i]) {
            cursorIndex = left;
        } else {
            cursorIndex = i;
        }
        // Exchange smaller to the right
        if (right <= n && a[right] > a[cursorIndex]) {
            cursorIndex = right;
        }
        if (cursorIndex != i) {
            exchange(i, cursorIndex);
            maxheap(a, cursorIndex);
        }
    }

    public static void exchange(int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void heapIncreaseKey(int[] b, int index, int newKey) {
        counter = 0;
        a = b;
        System.out.println("\nQ3: heapIncreaseKey \nChange Index:" + index + " Priority from " + a[index - 1] + " to " + newKey);
        a[index] = newKey;
        // Reconstruct maxheap.
        System.out.println("Then Rebuild MaxHeap Array");
        buildMaxHeap(b);
        System.out.print("After buildMaxHeap Array :");
        //Print all elements.
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static void maxHeapInsert(int[] b, int newKey) {

        System.out.println("\nQ4:maxHeapInsert Key:" + newKey);
        System.out.println("Then Rebuild MaxHeap Array");
        counter = 0;
        a = Arrays.copyOf(b, (b.length + 1));
        a[a.length - 1] = newKey;

        // Reconstruct maxheap.
        buildMaxHeap(a);
        System.out.print("After buildMaxHeap Array :");

        //Print all elements.
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

}
/*
1. [5 Pts] For the heap binary tree structure, the examples in the textbook use index 1 to represent
 the root node. Then, the parent node is said to be at  [i/2], left-child at 2i, and right-child at
 2i + 1.

In actual programing implementation, the first array element has index 0.
a) Show that the formulas are not valid (need some adjustment) when the root node index is 0

ANS >> The last element n elements would have index as "n-1" and cause out of bound.

b) What are the new formulas for finding the array index for the parent, the left-child, and the
right-child for a node at position  in the array

ANS >> Use  n = array.length-1 before we use left-child and right-child.

2.	[25 Pts] Write a program in Java that performs the Heapsort algorithm.
a) Create the maxHeapify method. Use the pseudocode shown in the lecture and the adjustment to the formulas that you figured out in problem 1.
b) Implement the buildMaxHeap method and the heapSort method.
c) Test your program by using the following array:
int[] array = {10,15, 8, 12, 5, 2, 20, 7, 18};
d) Your output should display the following 3 versions of the array:
i. The original input array
ii. The array after buildMaxHeap has completed
iii. The final sorted array after performing the Heapsort algorithm

3.	[10 Pts] Show what the array would look like at each step
when the heapIncreaseKey operation (increase the key value of the 8th node from 2 to 20)
is performed on the max-heap array shown below:
heap = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1}

4.	 [10 Pts] Show what the array would look like at each step
when the maxHeapInsert operation to insert a new key with value 12
is performed on the max-heap array shown below:
heap = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1, 5}

Note: For problems 4 and 5 above, the heapIncreaseKey and
the maxHeapInsert operations were discussed in the Priority Queue section of the lecture.
Only show what the arrays look like after each step of rearranging the nodes.
No need to write a program or draw the binary trees.

Note: The answers for problems 4 and 5 would look something like this:
Step 0: heap = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1}
Step 1: heap = {[the sequence of numbers after step 1]}
Step 2: heap = {[the sequence of numbers after step 2]} . . .
Finally: heap = {[the sequence of numbers after completion]}
 */
