/**
 * 
 * 
You are given a queue containing integer elements. Implement a function to sort the elements of the queue in non-decreasing order using only the basic queue operations (enqueue, dequeue, isEmpty). You are not allowed to use any other data structures (arrays, lists, etc.) for sorting.

Write a function void sortQueue(Queue<Integer> queue) in your preferred programming language to solve this problem. The function should take the input queue and sort its elements in-place, without using any additional data structures.

For example, if the input queue is:

Input Queue: 5, 2, 7, 3, 1
After calling sortQueue(queue), the queue should be sorted as follows:

Sorted Queue: 1, 2, 3, 5, 7
Constraints:
*. The number of elements in the queue will be at most 1000.
*. The values of elements in the queue will be integers in the range [-1000, 1000].
*. You should not use recursion for solving this problem. The sorting process should be done iteratively using queue operations.
 */

import java.util.LinkedList;
import java.util.Queue;

public class QueueSorting {

    public static void sortQueue(Queue<Integer> queue) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            int minIndex = findMinIndex(queue, size - i);
            insertMinToRear(queue, minIndex);
        }
    }

    private static int findMinIndex(Queue<Integer> queue, int sortRange) {
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < sortRange; i++) {
            int current = queue.poll();
            if (current < minValue) {
                minValue = current;
                minIndex = i;
            }
            queue.add(current);
        }
        return minIndex;
    }

    private static void insertMinToRear(Queue<Integer> queue, int minIndex) {
        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < queue.size(); i++) {
            int current = queue.poll();
            if (i != minIndex) {
                queue.add(current);
            } else {
                minValue = current;
            }
        }
        queue.add(minValue);
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(5);
        queue.add(2);
        queue.add(7);
        queue.add(3);
        queue.add(1);

        System.out.println("Input Queue: " + queue);
        sortQueue(queue);
        System.out.println("Sorted Queue: " + queue);
    }
}