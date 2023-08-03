/*
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each node contains a single digit. You need to write a function to add the two numbers and return the sum as a linked list.

For example, given the following linked lists:

``````
l1: 2 -> 4 -> 3
l2: 5 -> 6 -> 4
``````
The linked list representing the sum of the two numbers should be:

``````
Result: 7 -> 0 -> 8
``````
Write a function in your preferred programming language that takes two linked lists as input and returns the head of the resulting linked list representing the sum of the two numbers.

Feel free to include helper functions or classes if needed.
 */
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class AddLinkedLists {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }

        return dummyHead.next;
    }

    // Helper method to convert an array to a linked list
    public ListNode arrayToList(int[] nums) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        for (int num : nums) {
            current.next = new ListNode(num);
            current = current.next;
        }
        return dummyHead.next;
    }

    // Helper method to convert a linked list to an array
    public int[] listToArray(ListNode head) {
        ListNode current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        int[] result = new int[size];
        current = head;
        int index = 0;
        while (current != null) {
            result[index] = current.val;
            index++;
            current = current.next;
        }
        return result;
    }

    public static void main(String[] args) {
        AddLinkedLists solution = new AddLinkedLists();
        
        int[] num1 = {9, 4, 3};
        int[] num2 = {5, 6, 4};

        ListNode l1 = solution.arrayToList(num1);
        ListNode l2 = solution.arrayToList(num2);

        ListNode result = solution.addTwoNumbers(l1, l2);
        int[] output = solution.listToArray(result);

        // Output: [7, 0, 8]
        System.out.println(Arrays.toString(output));
    }
}
